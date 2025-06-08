package PriorityQueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class lesscmp implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}

class greatercmp implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}

public class demo1 {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new lesscmp());
        pq.add(10);
        pq.add(20);
        pq.add(15);
        while(!pq.isEmpty()) {
            System.out.println(pq.poll());
        };

        PriorityQueue<Integer> pq1 = new PriorityQueue<>(new greatercmp());
        pq1.add(10);
        pq1.add(20);
        pq1.add(15);
        while(!pq1.isEmpty()) {
            System.out.println(pq1.poll());
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(10);

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Integer::compareTo);
        pq2.addAll(pq1);
        while(!pq2.isEmpty()) {
            System.out.println(pq2.poll());
        }
    }
}
