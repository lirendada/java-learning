package Queue;

import java.util.*;

public class demo1 {
    public static void main(String[] args) {
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(1);
        q1.add(2);
        q1.offer(3);
        q1.offer(4);
        q1.offer(5);
        System.out.println(q1.peek()); // 1
        System.out.println(q1.poll()); // 1
        System.out.println(q1.poll()); // 2
        System.out.println(q1.peek()); // 3
        System.out.println(q1.poll()); // 3
        System.out.println(q1.poll()); // 4
        System.out.println(q1.poll()); // 5
        System.out.println(q1.peek()); // null

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int[] index = new int[10];

    }
}
