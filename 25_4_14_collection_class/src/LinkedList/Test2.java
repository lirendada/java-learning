package LinkedList;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // 遍历方式一：使用自带的重写的toString()方法
        System.out.println(list);

        // 遍历方式二：for-each循环
        for (int e : list) {
            System.out.print(e + " ");
        }
        System.out.println();

        // 遍历方式三：普适的迭代器
        Iterator<Integer> it1 = list.iterator();
        while(it1.hasNext()) {
            System.out.print(it1.next() + " ");
        }
        System.out.println();

        // 遍历方式四：使用LinkedList专用的迭代器
        ListIterator<Integer> it2 = list.listIterator();
        while(it2.hasNext()) {
            System.out.print(it2.next() + " ");
        }
        System.out.println();

        // 遍历方式五：反向迭代器
        ListIterator<Integer> rit = list.listIterator(list.size());
        while(rit.hasPrevious()) {
            System.out.print(rit.previous() + " ");
        }
        System.out.println();
    }
}
