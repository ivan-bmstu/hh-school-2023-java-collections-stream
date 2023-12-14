package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  /*
   * -убрал первую фейковую персону непосредственно в stream, меньше кода, лучше читаемость
   *
   * -немного подправил code style в stream, перенес действия построчно
   *
   * -проверка на пустоту избыточна, т.к. collect(Collectors.toList() никогда не возвращает null, а лишь пустую
   * коллекцию
   * */
  public List<String> getNames(List<Person> persons) {
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  /*
   * -вызов метода stream.distinct() избыточен, т.к. Set возвращает только уникальные значения.
   * */
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  /*
   * -переписал код на stream. Это удобнее: легче менять и расширять код (например последовательность, или добавить
   * дополнительные поля)
   * */
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(),
                    person.getFirstName(),
                    person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  /*
   * -переделал метод через stream, стало меньше кода, читаемее
   * */
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString, (p1, p2) -> p1));
  }

  /*
   * -воспользовался функционалом Collections
   * */
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return !Collections.disjoint(new HashSet<>(persons1), new HashSet<>(persons2));
  }

  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }
  /*
  * убрал поле count, оно здесь избыточно
  * */
}
