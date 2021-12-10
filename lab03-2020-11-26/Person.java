import javax.management.RuntimeErrorException;

public class Person {
	private String name;
	private String surname;
	private long socialSN;
	private static long nextSSN;
	private Person spouse;

	// Deafult constructor:
	public Person(String name, String surname){
		Person.checkName(name);
		Person.checkSurname(surname);
		this.name = name;
		this.surname = surname;
		this.socialSN = Person.getNextSSN();
		this.spouse = null;
	}
	private static long getNextSSN(){
		if (Person.nextSSN < 0)
			throw new RuntimeException("No more ids!");
		return Person.nextSSN++;
	}

	// Metodi per validare i dati:
	private static void checkName(String name){
		if (name==null) 
			throw new IllegalArgumentException("Name should not be 'null'!");
		if (!name.matches("[A-Z][a-z]+( [A-Z][a-z]+)*"))
			throw new IllegalArgumentException("Name does not match regex!");
	}
	private static void checkSurname(String surname){
		if (surname==null) 
			throw new IllegalArgumentException("Surname should not be 'null'!");
		if (!surname.matches("[A-Z][a-z]+( [A-Z][a-z]+)*"))
			throw new IllegalArgumentException("Surname does not match regex!");
	}
	private static void areSingles(Person p1, Person p2){
		if (p1.spouse != null | p2.spouse != null) throw new RuntimeException();
	}
	private static void areMarried(Person p1, Person p2){
		if (p1.spouse != p2 | p2.spouse != p1) throw new RuntimeException();
	}

	// Metodi da specifiche in README.md:
	public static void join(Person p1, Person p2){
		Person.areSingles(p1, p2);
		p1.spouse = p2;
		p2.spouse = p1;
	}
	public static void divorce(Person p1, Person p2){
		Person.areMarried(p1, p2);
		p1.spouse = null;
		p2.spouse = null;
	}

	//getter
	public String name(){
		return this.name;
	}
	public String surname(){
		return this.surname;
	}
	public long socialSN(){
		return this.socialSN;
	}
	public Person spouse(){
		return this.spouse;
	}
	public Boolean isSingle(){
		return (this.spouse == null);
	}

}
