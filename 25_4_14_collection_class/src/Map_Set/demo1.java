package Map_Set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class demo1 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 20);
        map.put("orange", 30);
        map.put("banana", 40);

        // 遍历方式一：直接输出（内部重写了toString方法）
        System.out.println(map);

        // 遍历方式二：通过entrySet()方法获取map的迭代器
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            System.out.println(it.next().getKey() + " " + it.next().getValue());
        }

    }
}
