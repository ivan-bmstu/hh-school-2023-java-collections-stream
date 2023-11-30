package tasks;

import common.Person;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {
    static Person person1;
    static Person person2;
    static Person person3;
    static Person person4;
    static Person person5;
    static Person person6;
    static Person person7;
    static Person person8;


    @BeforeAll
   public static void personsDataGenerate(){
       Instant instant = Instant.now();
       person1 = new Person(1, "Ivan", "Ivanov", "Ivanovich", instant);
       person2 = new Person(2, "Petr", "Petrov", "Petrovich", instant);
       person3 = new Person(3, "Alexandr", "Alexandrov", "Alexandrovich", instant);
       person4 = new Person(4, "Andrey", "Andreyev", "Andreyevich", instant);
       person5 = new Person(5, "Alex", "Alexeev", "Alexeevich", instant);
       person6 = new Person(2, "Petr", null, "Petrovich", instant);
       person7 = new Person(3, null, "Ivanov", null, instant);
       person8 = new Person(5, "Alex", "Alexeev", "Alexeevich", instant);
    }

    @ParameterizedTest
    @MethodSource("getNamesDataGenerate")
    void getNamesShouldGiveCorrectListPersonsName(List<Person> testList){
        Task8 task = new Task8();
        assertEquals(testList.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList()), task.getNames(testList));
    }

    @ParameterizedTest
    @MethodSource("getNamesDataGenerate")
    void getDifferentNamesShouldGiveCorrectSetPersonsName(List<Person> testList){
        Task8 task = new Task8();
        assertEquals(testList.stream().skip(1).map(Person::getFirstName).collect(Collectors.toSet()), task.getDifferentNames(testList));
    }

    @ParameterizedTest
    @MethodSource("convertPersonToStringDataGenerate")
    void convertPersonToStringShouldGiveAvailableFullName(Person person, String input){
        Task8 task = new Task8();
        assertEquals(input, task.convertPersonToString(person));
    }

    @ParameterizedTest
    @MethodSource("getNamesDataGenerate")
    void getPersonNamesShouldGiveMapIdFullName(List<Person> testList){
        Task8 task = new Task8();
        Map<Integer, String> personMapValid = testList.stream()
                .collect(Collectors.toMap(Person::getId, task::convertPersonToString, (p1, p2) -> p1));
        assertEquals(personMapValid, task.getPersonNames(testList));
    }

    @ParameterizedTest
    @MethodSource("hasSamePersonsDataGenerate")
    void hasSamePersonsShouldGiveBooleanResult(Collection<Person> persons1, Collection<Person> persons2, boolean isDisjoint){
        Task8 task = new Task8();
        assertEquals(task.hasSamePersons(persons1, persons2), isDisjoint);
    }

    @ParameterizedTest
    @MethodSource("countEvenShouldGiveCountOfEvenNumbers")
    void countEvenShouldGive(Stream<Integer> intStream, int evenCount){
        Task8 task = new Task8();
        assertEquals(evenCount, task.countEven(intStream));
    }

    private static Stream<Arguments> getNamesDataGenerate(){
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(person1, person2, person3, person4, person5)),
                Arguments.of(List.of(person1, person2, person1, person2, person5))
        );
    }

    private static Stream<Arguments> convertPersonToStringDataGenerate(){
        return Stream.of(
                Arguments.of(person1, "Ivanov Ivan Ivanovich"),
                Arguments.of(person6, "Petr Petrovich"),
                Arguments.of(person7, "Ivanov")
        );
    }

    private static Stream<Arguments> hasSamePersonsDataGenerate(){
        Collection<Person> emptyColletcion = Collections.emptyList();
        return Stream.of(
                Arguments.of(Collections.emptyList(), Collections.emptyList(), false),
                Arguments.of(emptyColletcion, emptyColletcion, false),
                Arguments.of(List.of(person1, person2, person3), emptyColletcion, false),
                Arguments.of(List.of(person1, person2, person3), List.of(person1, person2), true),
                Arguments.of(List.of(person1, person2, person3), List.of(person3, person4), true),
                Arguments.of(List.of(person1, person2, person3), List.of(person1, person2, person3), true),
                Arguments.of(List.of(person1, person2, person3), List.of(person5), false)
                );
    }

    private static Stream<Arguments> countEvenShouldGiveCountOfEvenNumbers(){
        return Stream.of(
                Arguments.of(Stream.of(), 0),
                Arguments.of(Stream.of(2), 1),
                Arguments.of(Stream.of(1, 2), 1),
                Arguments.of(Stream.of(1, 2, 1, 2, 1, 2, 1, 2, 2, 10), 6),
                Arguments.of(Stream.of(1, 1, 1, 1, 1, 1, 101, 1, 1, 11), 0),
                Arguments.of(Stream.of(1, 2, 1, 2, 1, 2, 1, 2, 2, 10).parallel(), 6)
                );
    }
}
