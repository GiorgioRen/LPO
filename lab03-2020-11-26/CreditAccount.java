public class CreditAccount {
	private int limit;
	private int balance;
	private Person owner;
	private long id;
	private static long nextId;
	private static int default_limit = 0;

	// Deafult constructor:
	public CreditAccount(Person p){
		this.limit = CreditAccount.default_limit;
		this.balance = 0;
		this.owner = p;
		this.id = CreditAccount.getNextId();
	}
	private static long getNextId() {
		if (CreditAccount.nextId < 0) throw new RuntimeException("No more ids!");
		return CreditAccount.nextId++; //TODO: this doesn't work :(
	}

	// Metodi per validare i dati:
	private static void checkBalance(int balance, int limit){
		if (balance < limit) throw new RuntimeException("Not enough money!");
	}
	private static void checkAmount(int amount){
		if (amount <= 0) throw new IllegalArgumentException("Amount should be > 0!");
	}

	// Metodi da specifiche in README.md:
	public int deposit(int amount){
		CreditAccount.checkAmount(amount);
		this.balance += amount;
		return this.balance;
	}
	public int withdraw(int amount){
		CreditAccount.checkAmount(amount);
		CreditAccount.checkBalance(this.balance-amount, this.limit);
		this.balance -= amount;
		return this.balance;
	}
	public void setLimit(int limit){
		CreditAccount.checkBalance(this.balance, limit);
		this.limit = limit;
	}

	public CreditAccount newOfLimitBalanceOwner(int limit, int balance, Person owner){
		CreditAccount.checkBalance(balance, limit);
		CreditAccount nuovoAccount = new CreditAccount(owner);
		nuovoAccount.balance = balance;
		nuovoAccount.limit = limit;
		return nuovoAccount;
	}
	public CreditAccount newOfBalanceOwner(int balance, Person owner){
		CreditAccount.checkBalance(balance, this.limit);
		CreditAccount nuovoAccount = new CreditAccount(owner);
		nuovoAccount.balance = balance;
		nuovoAccount.limit = 0;
		return nuovoAccount;
	}

	//getter
	public Person owner(){
		return this.owner;
	}
	public long id(){
		return this.id;
	}
	public int balance(){
		return this.balance;
	}
	public int limit(){
		return this.limit;
	}

}
