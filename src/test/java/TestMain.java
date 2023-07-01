import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class TestMain {

    public static Stream<Arguments> multiplyParams() {
        Collection<Person> persons = new ArrayList<>();

        persons.add(new Person(
                "Jack",
                "Evans",
                20,
                Sex.MAN,
                Education.HIGHER)
        );
        persons.add(new Person(
                "Connor",
                "Young",
                17,
                Sex.MAN,
                Education.ELEMENTARY)
        );
        persons.add(new Person(
                "George",
                "Wilson",
                59,
                Sex.WOMAN,
                Education.HIGHER)
        );
        return Stream.of(
                Arguments.of(persons)
        );
    }

    @ParameterizedTest
    @MethodSource("multiplyParams")
    public void testCountYounger18(Collection<Person> persons) {
        long result = Main.countYounger18(persons);
        long expected = 1;
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("multiplyParams")
    public void testMaySolders(Collection<Person> persons) {
        List<String> result = Main.maySolders(persons);
        List<String> expected = List.of("Evans");
        Assertions.assertEquals(expected, result);
    }
    @ParameterizedTest
    @MethodSource("multiplyParams")
    public void testWorkingAge(List<Person> persons) {
        List<Person> result = Main.workingAge(persons);
        List<Person> expected = List.of(persons.get(0), persons.get(2));
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testGenerateRandomCollection() {
        Assertions.assertEquals(100, Main.generateRandomCollection().toArray().length);
    }
}
