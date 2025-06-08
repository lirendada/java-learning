package ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class demo1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        // 1. 下标遍历（推荐）
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 2. for-each遍历（推荐）
        for(Integer e: list) {
            System.out.println(e);
        }

        // 3. 迭代器遍历：使用Iterator的hasNext()和next()方法
        Iterator<Integer> iter = list.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
