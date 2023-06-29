import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static List<String> NAMES = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    public static List<String> FAMILIES = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    public static Collection<Person> persons = new ArrayList<>();

    public static void main(String[] args) {

        persons = generateRandomCollection(NAMES, FAMILIES);

        System.out.println(countYounger18(persons));
        System.out.println(maySolders(persons));
        System.out.println(workingAge(persons));
    }

    public static Collection<Person> generateRandomCollection(List<String> names, List<String> families) {
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }

    public static long countYounger18 (Collection<Person> persons) {
        Stream<Person> stream = persons.stream();
        return stream.filter(x -> x.getAge() < 18).count();
    }

    public static List<String> maySolders(Collection<Person> persons) {
        Stream<Person> stream = persons.stream();
        return stream
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
    }

    public static List<Person> workingAge(Collection<Person> persons) {
        Stream<Person> stream = persons.stream();
        return stream
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> (x.getSex() == Sex.MAN && x.getAge() < 65 || x.getSex() == Sex.WOMAN && x.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}