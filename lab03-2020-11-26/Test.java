public class Test {
	public static void main(String[] args){

		Person mario = new Person("Mario", "Rossi");
		Person anna = new Person("Anna", "Bianchi");

		assert mario.name() != null;
		assert mario.surname() != null;
		assert mario.socialSN() >= 0;

		assert anna.name() != null;
		assert anna.surname() != null;
		assert mario.socialSN() >= 0;

		assert mario != anna;
		assert mario.socialSN() != anna.socialSN();

		assert mario.isSingle() == true;
		assert anna.isSingle() == true;

		Person.join(mario, anna);

		assert mario.isSingle() == false;
		assert anna.isSingle() == false;

		assert mario.spouse() == anna;
		assert anna.spouse() == mario;

		Person.divorce(anna, mario);

		assert mario.isSingle() == true;
		assert anna.isSingle() == true;

		System.out.println(">> Tutto OK per Person.java!");

		CreditAccount contoMario = new CreditAccount(mario);
		CreditAccount contoAnna = new CreditAccount(anna);

		assert contoMario != contoAnna;
		assert contoMario.owner() == mario;
		assert contoAnna.owner() == anna;
		assert contoMario.id() != contoAnna.id();
		assert contoMario.limit() == 0;
		assert contoAnna.limit() == 0;
		assert contoMario.balance() == 0;
		assert contoAnna.balance() == 0;

		contoMario.deposit(10000);
		contoAnna.deposit(50000);

		assert contoMario.balance() == 10000;
		assert contoAnna.balance() == 50000;

		contoMario.withdraw(5000);
		contoAnna.withdraw(10000);

		assert contoMario.balance() == 10000-5000;
		assert contoAnna.balance() == 50000-10000;

		contoMario.setLimit(-10000);
		contoMario.withdraw(5000);
		assert contoMario.balance() == 0;

		System.out.println(">> Tutto OK per CreditAccount.java!");
	}
}
