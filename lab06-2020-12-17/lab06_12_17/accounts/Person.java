package lab06_12_17.accounts;

/* invariant
 * firstName!=null && secondName!=null && spouse!=this
 * p1.spouse!=p2 || p2.spouse==p1 && (p1==p2) == (p1.socialSN==p2.socialSN)
 */

public class Person implements Client {
	private static long nextSocialSN;
	private final static String validName = "[A-Z][a-z]+( [A-Z][a-z]+)*";
	public final String name;
	public final String surname;
	public final long socialSN; // social security number
	private Person spouse; // optional :)

	// private auxiliary static methods for validation and security social number
	// generation

	private static Object requireNonNull(Object o) {
		if (o == null)
			throw new NullPointerException();
		return o;
	}

	private static String requireValidName(String name) {
		if (!name.matches(Person.validName))
			throw new IllegalArgumentException("Illegal name: " + name);
		return name;
	}

	private static long nextSocialSN() {
		if (Person.nextSocialSN < 0)
			throw new RuntimeException("No more available numbers");
		return Person.nextSocialSN++;
	}

	// public static methods to change the civil status of couples

	public static void join(Person p1, Person p2) {
		if (p1.spouse != null || p2.spouse != null || p1 == p2)
			throw new IllegalArgumentException();
		p1.spouse = p2;
		p2.spouse = p1;
	}

	public static void divorce(Person p1, Person p2) {
		Person.requireNonNull(p1);
		if (p1 != p2.spouse)
			throw new IllegalArgumentException();
		p1.spouse = null;
		p2.spouse = null;
	}

	// constructor

	public Person(String name, String surname) {
		this.name = Person.requireValidName(name);
		this.surname = Person.requireValidName(surname);
		this.socialSN = Person.nextSocialSN();
	}

	// instance methods

	@Override
	public Person getSpouse() {
		return this.spouse;
	}

	@Override
	public boolean isSingle() {
		return this.spouse == null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSurname() {
		return this.surname;
	}

	@Override
	public long getSocialSN() {
		return this.socialSN;
	}

}
