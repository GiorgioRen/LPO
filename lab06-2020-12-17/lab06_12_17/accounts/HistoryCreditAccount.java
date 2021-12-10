package lab06_12_17.accounts;

import lab06_12_17.accounts.CreditAccount;

public class HistoryCreditAccount extends CreditAccount implements HistoryAccount {
	private int history; // positive->deposit, negative->withdraw, zero->no operation

        // private auxiliary method to be used in undo() and redo() methods
	private int operation(int amount) {
		if (amount >= 0)
			return this.deposit(amount);
		return this.withdraw(-amount);
	}

        // check to be used in undo() and redo() methods
	protected int requireNonZeroHistory() {
		if (this.history == 0)
			throw new IllegalStateException("Operation history is empty");
		return this.history;
	}

	protected HistoryCreditAccount(int limit, int balance, Client owner) {
		// to be completed
		super(limit, balance, owner);
	}

	protected HistoryCreditAccount(int balance, Client owner) {
		// to be completed
		super(balance, owner);
	}

        // factory methods for the corresponding constructors
    
	public static HistoryCreditAccount newOfLimitBalance(int limit, int balance, Client owner) {
		// to be completed
		return new HistoryCreditAccount(limit, balance, owner);
	}

	public static HistoryCreditAccount newOfBalance(int balance, Client owner) {
		// to be completed
		return new HistoryCreditAccount(balance, owner);
	}

        // public instance methods

	@Override
	public int deposit(int amount) {
		// to be completed
		return this.history += amount;
	}

	@Override
	public int withdraw(int amount) {
		// to be completed
		return this.history -= amount;
	}

	@Override
	public long undo() {
		// to be completed
		long tmp = operation(-requireNonZeroHistory());
		this.history = 0;
		return tmp;
	}

	@Override
	public long redo() {
		// to be completed
		long tmp = operation(requireNonZeroHistory());
		this.history = 0;
		return tmp;
	}
}
