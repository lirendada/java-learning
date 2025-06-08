package Map_Set;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
public class demo2 {
    public static void main(String[] args) {
        Person p1 = new Person("John");
        Person p2 = new Person("John");
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode() == p2.hashCode());

        Map<Person, String> map = new HashMap<>();
        map.put(p1, "John");
        map.put(p2, "John");
        System.out.println(map.size());
        System.out.println(map.containsKey(p1));
        System.out.println(map.containsValue(p1.name));


    }
}
