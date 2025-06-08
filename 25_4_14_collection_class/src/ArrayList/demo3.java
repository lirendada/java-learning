package ArrayList;

import java.util.ArrayList;
import java.util.List;

public class demo3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list);

        List<Integer> cp = List.copyOf(list);
        System.out.println(cp);

        cp.set(2, 40); // 会抛异常，因为List.copyOf()方法返回的List是不可变的
        System.out.println(list);
        System.out.println(cp);
    }
}
