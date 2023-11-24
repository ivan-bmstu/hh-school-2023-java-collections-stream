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
  public List<String> getNamesExceptFirst(List<Person> persons) {
    if (persons.isEmpty()) {
      return Collections.emptyList();
    }
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }
  /*
  * -изменил имя метода, теперь оно лучше отображает его функциональность. Но тут
  * нужно обсуждать этот момент с командой: во-первых, этот метод мог вызываться во многих местах,
  * и заменить имя его может быть проблемно. Более того, возможно так и планировалось оставить часть функционала скрытой
  * от пользователей, чтобы не были ясны некоторые "нужные" рабочие моменты. Это изменение обсуждаемо
  *
  * -убрал первую фейковую персону непосредственно в stream, меньше кода, лучше читаемость
  *
  * -немного подправил code style в stream, перенес действия построчно
  *
  * -я бы удалил комментарий перед методом, но сначала обсудил бы с командой (иначе можно и забыть в будущем,
  * зачем убирать первую персону в принципе)
  *
  * -заменил persons.size() == 0 на persons.isEmpty(), что будет эффективнее, если persons LinkedList
  * */

  public Set<String> getDifferentNamesExceptFirst(List<Person> persons) {
    return getNamesExceptFirst(persons).stream().collect(Collectors.toSet());
  }
  /*
  * -убрал комментарий перед методом, имя метода полностью отображает его функциональность
  *
  * -вызов метода stream.distinct() избыточен, т.к. Set возвращает только уникальные значения.
  * p.s. еле удержал себя, чтобы не изменить код: сделать все как в методе getNamesExceptFirst,
  * но вернуть не List, а Set. Это небольшая оптимизация (не надо сворачивать стрим а потом разворачивать обратно),
  * но тут будет повторение кода (проверка на пустоту)
  *
  * -имя метода поменял, причина и описание как и выше в методе getNamesExceptFirst
  *
  * p.p.s. так и не понял, есть ли преимущества по производительности, если здесь использовать не stream, а конструктор
  * new HashSet<>(getNamesExceptFirst(persons))
  * */

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(),
                    person.getFirstName(),
                    person.getSecondName()) //TODO тут должно быть отчество
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }
  /*
  * -переписал код на stream. Это удобнее: легче менять и расширять код (например последовательность, или добавить
  * дополнительные поля)
  *
  * -выводится не ФИО, а ФИФ - тут непонятно, так и должно быть, или нужно править, поэтому обсуждаем с командой. Сейчас
  * оставил исходный вариант
  *
  * */

  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString, (p1, p2) -> p1));
  }
  /*
  * -переделал метод через stream, стало меньше кода, читаемее
  * */

  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return !Collections.disjoint(persons1, persons2);
  }
  /*
  * -воспользовался функционалом Collections
  * */

  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }
  /*
  * убрал поле count, оно здесь избыточно
  * */
}
