import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Sex {Male, Female}	// enumerovany typ, podobne ako v C++

class Person {
    String name;			// meno osoby
    Sex sex;				// pohlavie
    List<Person> parents;	// zoznam rodicov
	public Person(String name, Sex sex, List<Person> parents) {
		super();
		this.name = name;
		this.sex = sex;
		this.parents = parents;
	}
    @Override
	public boolean equals(Object other) {    // porovnavanie Person podla mena
	  return (other instanceof Person)?name.equals(((Person)other).name):false;
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	// Predpokladajte pre zjednodusenie, ze cely rodokmen rodiny je pristupny cez 
	// staticku premennu RodinkaUzasnych.people 
	
    // this is parent of y
	public boolean isParent(Person y) {
	   return y != null && y.parents != null && y.parents.contains(this); 
	}
    // this is father of y
	public boolean isFather(Person y) {
	    return sex.equals(Sex.Male) && isParent(y);
	}
    // this is mother of y
	public boolean isMother(Person y) {
	    return sex.equals(Sex.Female) && isParent(y);
	}
    // this is an orphan (sirota)
	public boolean isOrphan() {
	    return parents == null || parents.size() == 0;
	}
    // have a child with this and it's a man
	public List<Person> fathersOfMyChild() {
		return
		RodinkaUzasnych.people
			.stream()
			.filter(child -> child.parents != null && child.parents.contains(this))
			.flatMap(child -> child.parents.stream())
			.filter(e -> e.sex == Sex.Male)
			.filter(e -> !e.equals(this))
			.distinct()
			.collect(Collectors.toList());
    }
    // have a child with this and it's a woman
	public List<Person> mothersOfMyChild() {
		return
		RodinkaUzasnych.people
			.stream()
			.filter(child -> child.parents != null && child.parents.contains(this))
			.flatMap(child -> child.parents.stream())
			.filter(e -> e.sex == Sex.Female)
			.filter(e -> !e.equals(this))
			.distinct()
			.collect(Collectors.toList());
    }
    // this is a sib of y - surodenec
	// this a y su surodenci, maju aspon jedneho spolocneho rodica
    public boolean isSib(Person y) {
    	return false; // dodefinujte
    }
    // this is a brother of y 
    public boolean isBrother(Person y) {
    	return false; // dodefinujte
    }
    // this is a sister of y 
    public boolean isSister(Person y) {
    	return false; // dodefinujte
    }
    // all they have a baby with this
    public List<Person> partners() {
    	return null; // dodefinujte
    }
    // all svokra of this
    public List<Person> motherInLaw() {
    	return null; // dodefinujte
    }
    public List<Person> fatherInLaw() {
    	return null; // dodefinujte
    }
    // all predecessors of this
    public Set<Person> predecessors() {
    	return null; // dodefinujte
    }
    // all predecessors of this at level n
    public Set<Person> predecessors(int n) {
    	return null; // dodefinujte
    }
    // all grandparents of this
    public Set<Person> grandParents() {
    	return null; // dodefinujte
    }
    // this and y have the same predecessor with respect the level
    public static boolean sameGen(Person y) {
    	return false; // dodefinujte
    }
    public List<Person> children() {
    	return null; // dodefinujte
    }
    // all grandchildren of this
    public List<Person> grandChildren() {
    	return null; // dodefinujte
    }
}
public class RodinkaUzasnych {
	  final static Person adam = new Person("Adam", Sex.Male, null);
	  final static Person eva  = new Person("Eva", Sex.Female, null);
	  // 1st gen
	  final static Person jozo = new Person("Jozo", Sex.Male, Arrays.asList(adam, eva));
	  final static Person sona = new Person("Sona", Sex.Female, Arrays.asList(adam, eva));
	  final static Person pavel = new Person("Pavel", Sex.Male, Arrays.asList(adam, eva));
	  final static Person paella = new Person("Paella", Sex.Female, Arrays.asList(adam, eva));
	  // 2nd gen
	  final static Person fero = new Person("Fero", Sex.Male, Arrays.asList(jozo, sona));
	  final static Person simona = new Person("Simona", Sex.Female, Arrays.asList(sona));
	  // 3rd gen
	  final static Person filomena = new Person("Filomena", Sex.Female, Arrays.asList(jozo, simona));  // incest: jozo so svojou dcerou simonou
	  public static final List<Person> people = Arrays.asList( adam, eva, jozo, fero, simona, filomena  );
}
