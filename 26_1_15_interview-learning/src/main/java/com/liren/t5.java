package com.liren;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class t5 {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");

//        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
//        while(iterator.hasNext()) {
//            Map.Entry<Integer, String> next = iterator.next();
//            System.out.println(next.getKey());
//            System.out.println(next.getValue());
//        }

        map.entrySet().stream().forEach(e -> {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        });
    }
}
