package resource.Map;

import java.util.*;

/**
 * Created by wang on 2017/8/6.
 */
public class Parcel11 {

    public static void main(String[] args) {

        //作业：使用map嵌套map，并遍历打印

        Map<Person, Map<Person, Person>> personMapMap = new HashMap<>();

        Map<Person, Person> personMap = new HashMap<>();

        personMap.put(new Person("sub恽涵1"), new Person("subsub恽涵1"));
        personMap.put(new Person("sub恽涵2"), new Person("subsub恽涵2"));
        personMap.put(new Person("sub恽涵3"), new Person("subsub恽涵3"));

        personMapMap.put(new Person("恽涵1"), personMap);
        personMapMap.put(new Person("恽涵2"), personMap);
        personMapMap.put(new Person("恽涵3"), personMap);

        for (Person key :
                personMapMap.keySet()) {
            System.out.println(key.getName() + " : ");

            for (Person subkey :
                    personMapMap.get(key).keySet()) {
                System.out.println(subkey.getName() + " : " + personMap.get(subkey).getName());
            }
        }

    }
}

