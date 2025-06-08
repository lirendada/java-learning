package com.liren.thread_advanced;

import java.util.concurrent.ConcurrentHashMap;

public class demo6 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("A", "Apple");
        map.put("B", "Banana");

        for(String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));

            if(key.equals("A")) {
                map.remove("B");
                map.put("C", "Cherry");
            }
        }
        System.out.println("Final map: " + map);
    }
}
