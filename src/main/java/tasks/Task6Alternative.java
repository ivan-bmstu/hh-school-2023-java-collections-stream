package tasks;

import common.Area;
import common.Person;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task6Alternative {

    public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                    Map<Integer, Set<Integer>> personAreaIds,
                                                    Collection<Area> areas,
                                                    BiFunction<Person, Area, String> personDescription) {
        Map<Integer, Area> idAreaMap = areas.stream().collect(Collectors.toMap(Area::getId, Function.identity()));
        return persons.stream()
                .flatMap(person -> personAreaIds.get(person.getId()).stream()
                        .map(id -> personDescription.apply(person, idAreaMap.get(id))))
                .collect(Collectors.toSet());
    }
}
