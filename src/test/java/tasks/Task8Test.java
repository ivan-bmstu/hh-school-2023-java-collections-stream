package tasks;

import common.Person;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {

    @Test
    void test1(){
        Task8 task = new Task8();
        Instant instant = Instant.now();
        Person person1 = new Person(1, "Ivan", "Ivanov", "Ivanovich", instant);
        Person person2 = new Person(2, "Petr", "Petrov", "Petrovich", instant);
        Person person3 = new Person(3, "Alexandr", "Alexandrov", "Alexandrovich", instant);
        Person person4 = new Person(4, "Andrey", "Andreyev", "Andreyevich", instant);
        Person person5 = new Person(5, "Alex", "Alexeev", "Alexeevich", instant);
        List<Person> getNames0 = Collections.emptyList();
        List<Person> getNames1 = new ArrayList<>(Arrays.asList(person1, person2, person3, person4, person5));
        List<Person> getNames2 = new ArrayList<>(Arrays.asList(person1, person2, person1, person2, person5));
        assertEquals(Collections.emptyList(), task.getNames(getNames0));
        assertEquals(getNames1.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList()), task.getNames(getNames1));
        assertEquals(getNames2.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList()), task.getNames(getNames2));
    }

    @Test
    void test2(){
        Task8 task = new Task8();
        Instant instant = Instant.now();
        Person person1 = new Person(1, "Ivan", "Ivanov", "Ivanovich", instant);
        Person person2 = new Person(2, "Petr", "Petrov", "Petrovich", instant);
        Person person3 = new Person(3, "Alexandr", "Alexandrov", "Alexandrovich", instant);
        Person person4 = new Person(4, "Andrey", "Andreyev", "Andreyevich", instant);
        Person person5 = new Person(5, "Alex", "Alexeev", "Alexeevich", instant);
        List<Person> personEmptyList = Collections.emptyList();
        List<Person> personTest1List = new ArrayList<>(Arrays.asList(person1, person2, person3, person4, person5));
        List<Person> personTest2List = new ArrayList<>(Arrays.asList(person1, person2, person1, person2, person5));
        assertEquals(Collections.emptySet(), task.getDifferentNames(personEmptyList));
        assertEquals(personTest1List.stream().skip(1).map(Person::getFirstName).collect(Collectors.toSet()), task.getDifferentNames(personTest1List));
        assertEquals(personTest2List.stream().skip(1).map(Person::getFirstName).collect(Collectors.toSet()), task.getDifferentNames(personTest2List));
    }

    @Test
    void test3(){
        Task8 task = new Task8();
        Instant instant = Instant.now();
        Person person1 = new Person(1, "Ivan", "Ivanov", "Ivanovich", instant);
        Person person2 = new Person(2, "Petr", null, "Petrovich", instant);
        Person person3 = new Person(3, null, "Ivanov", null, instant);
        assertEquals("Ivanov Ivan Ivanovich", task.convertPersonToString(person1));
        assertEquals("Petr Petrovich", task.convertPersonToString(person2));
        assertEquals("Ivanov", task.convertPersonToString(person3));
    }

    @Test
    void test4(){
        Task8 task = new Task8();
        Instant instant = Instant.now();
        Person person1 = new Person(1, "Ivan", "Ivanov", "Ivanovich", instant);
        Person person2 = new Person(2, "Petr", "Petrov", "Petrovich", instant);
        Person person3 = new Person(3, "Alexandr", "Alexandrov", "Alexandrovich", instant);
        Person person4 = new Person(4, "Andrey", "Andreyev", "Andreyevich", instant);
        Person person5 = new Person(5, "Alex", "Alexeev", "Alexeevich", instant);
        Collection<Person> persons0 = Collections.emptyList();
        Collection<Person> persons1 = Stream.of(person1, person2, person3, person4, person5).toList();
        Collection<Person> persons2 = Stream.of(person1, person2, person1, person2, person5).toList();
        Map<Integer, String> personEmptyMap0 = Collections.emptyMap();

        Map<Integer, String> personEmptyMap1 = persons1.stream()
                .collect(Collectors.toMap(Person::getId, task::convertPersonToString, (p1, p2) -> p1));

        Map<Integer, String> personEmptyMap2 = persons2.stream()
                .collect(Collectors.toMap(Person::getId, task::convertPersonToString, (p1, p2) -> p1));

        assertEquals(personEmptyMap0, task.getPersonNames(persons0));
        assertEquals(personEmptyMap1, task.getPersonNames(persons1));
        assertEquals(personEmptyMap2, task.getPersonNames(persons2));
    }

    @Test
    void test5(){
        Task8 task = new Task8();
        Instant instant = Instant.now();
        Person person1 = new Person(1, "Ivan", "Ivanov", "Ivanovich", instant);
        Person person2 = new Person(2, "Petr", "Petrov", "Petrovich", instant);
        Person person3 = new Person(3, "Alexandr", "Alexandrov", "Alexandrovich", instant);
        Person person4 = new Person(4, "Andrey", "Andreyev", "Andreyevich", instant);
        Person person5 = new Person(5, "Alex", "Alexeev", "Alexeevich", instant);
        Collection<Person> persons0 = Collections.emptyList();
        Collection<Person> persons00 = Collections.emptyList();
        Collection<Person> persons1 = Stream.of(person1, person2, person3).toList();
        Collection<Person> persons2 = Stream.of(person1, person2).toList();
        Collection<Person> persons3 = Stream.of(person3, person4).toList();
        Collection<Person> persons4 = Stream.of(person5).toList();
        assertFalse(task.hasSamePersons(persons0, persons00));
        assertFalse(task.hasSamePersons(persons0, persons0));
        assertFalse(task.hasSamePersons(persons1, persons0));
        assertTrue(task.hasSamePersons(persons1, persons2));
        assertTrue(task.hasSamePersons(persons1, persons3));
        assertTrue(task.hasSamePersons(persons1, persons1));
        assertFalse(task.hasSamePersons(persons1, persons4));
    }

    @Test
    void test6(){
        Task8 task = new Task8();
        assertEquals(0, task.countEven(Stream.of()));
        assertEquals(1, task.countEven(Stream.of(2)));
        assertEquals(1, task.countEven(Stream.of(1, 2)));
        assertEquals(6, task.countEven(Stream.of(1, 2, 1, 2, 1, 2, 1, 2, 2, 10)));
        assertEquals(0, task.countEven(Stream.of(1, 1, 1, 1, 1, 1, 101, 1, 1, 11)));

    }
}
