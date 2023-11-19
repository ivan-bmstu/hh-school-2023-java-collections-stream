package tasks;

import common.Person;
import common.PersonService;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    if(persons.isEmpty())
      return Collections.emptyList();
    AtomicInteger orderedIdIndex = new AtomicInteger(0);
    Map<Integer, Integer> personsIdOrderedMap = personIds.stream()
            .collect(Collectors.toMap(
                    Function.identity(),
                    (id) -> orderedIdIndex.getAndIncrement()
            ));
    List<Person> orderedPersons = getNullArrayList(personIds.size());
    persons.forEach((person -> orderedPersons.set(personsIdOrderedMap.get(person.getId()), person)));
    return orderedPersons;
  }

  private List<Person> getNullArrayList(int size){
    List<Person> nullArrayList = new ArrayList<>();
    for (int i = 0; i < size; i++){
      nullArrayList.add(null);
    }
    return nullArrayList;
  }
}
