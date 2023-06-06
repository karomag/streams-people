import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
//        Stream<Person> stream1 = persons.stream();
//        System.out.println(stream1.filter(x -> x.getAge() < 18).count());
//        Stream<Person> stream2 = persons.stream();
//        System.out.println(stream2
//                .filter(x -> x.getAge() >= 18)
//                .filter(x -> x.getAge() < 27)
//                .map(Person::getFamily)
//                .collect(Collectors.toList()));
        Stream<Person> stream3 = persons.stream();
        System.out.println(stream3
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> {
                    if (x.getSex() == Sex.MAN && x.getAge() < 65 || x.getSex() == Sex.WOMAN && x.getAge() < 60) {
                        return true;
                    }
                    return false;
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList()));
    }
}