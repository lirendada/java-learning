package Lambda;

import java.util.ArrayList;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("java");
        list.add("programming");

        list.forEach(e -> System.out.println(e));

        list.sort((x, y) -> x.compareTo(y));
        System.out.println(list);
    }
}
