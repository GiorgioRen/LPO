package lab06_12_17.accounts;

public class CreditAccount implements Account {
	private static long nextId;
	public static final int default_limit = 0; // in cents

	private int limit; // in cents
	private int balance; // in cents
	public final Client owner;
	public final long id;

	// private auxiliary static methods for validation and identifier generation

	protected static Client requireNonNull(Client c) {
		if (c == null)
			throw new NullPointerException();
		return c;
	}

	protected static int requirePositive(int amount) {
		if (amount <= 0)
			throw new IllegalArgumentException();
		return amount;
	}

	protected static int requireLimitBelowBalance(int limit, int balance) {
		if (limit > balance)
			throw new IllegalArgumentException();
		return limit;
	}

	private static long nextId() {
		if (CreditAccount.nextId < 0)
			throw new RuntimeException("No more available ids");
		return CreditAccount.nextId++;
	}

	// constructors

	protected CreditAccount(int limit, int balance, Client owner) {
		this.balance = CreditAccount.requirePositive(balance);
		this.limit = CreditAccount.requireLimitBelowBalance(limit, balance);
		this.owner = CreditAccount.requireNonNull(owner);
		this.id = CreditAccount.nextId();
	}

	protected CreditAccount(int balance, Client owner) {
		this(CreditAccount.default_limit, balance, owner);
	}

	// static factory methods

	public static CreditAccount newOfLimitBalanceOwner(int limit, int balance, Person owner) {
		return new CreditAccount(limit, balance, owner);
	}

	public static CreditAccount newOfBalanceOwner(int balance, Person owner) {
		return new CreditAccount(balance, owner);
	}

	// instance methods
	@Override
	public int deposit(int amount) { // amount in cents
		return this.balance += CreditAccount.requirePositive(amount);
	}

	@Override
	public int withdraw(int amount) { // amount in cents
		// balance can be negative, overflow is possible!
		int newBalance = Math.subtractExact(this.balance, CreditAccount.requirePositive(amount));
		CreditAccount.requireLimitBelowBalance(this.limit, newBalance);
		return this.balance = newBalance;
	}

	@Override
	public int getBalance() {
		return this.balance;
	}

	@Override
	public int getLimit() {
		return this.limit;
	}

	@Override
	public void setLimit(int limit) { // setter method
		this.limit = CreditAccount.requireLimitBelowBalance(limit, this.balance);
	}

	@Override
	public Client getOwner() {
		return this.owner;
	}

	@Override
	public long getId() {
		return this.id;
	}

}
