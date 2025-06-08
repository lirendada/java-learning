package PriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

class Card {
    public int rank; // 数值
    public String suit; // 花⾊
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
}

class CardRankComp implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.rank - o2.rank;
    }
}

class CardSuitComp implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.suit.compareTo(o2.suit);
    }
}

public class demo2 {
    public static void main(String[] args) {
        PriorityQueue<Card> pq = new PriorityQueue<>(new CardRankComp());
        pq.offer(new Card(4, "Club"));
        pq.offer(new Card(1, "Ace"));
        pq.offer(new Card(3, "Diamond"));
        pq.offer(new Card(2, "Heart"));
        while (!pq.isEmpty()) {
            Card card = pq.poll();
            System.out.println(card.rank + " of " + card.suit);
        }

        PriorityQueue<Card> pq2 = new PriorityQueue<>(new CardSuitComp());
        pq2.offer(new Card(4, "Club"));
        pq2.offer(new Card(1, "Ace"));
        pq2.offer(new Card(3, "Diamond"));
        pq2.offer(new Card(2, "Heart"));
        while (!pq2.isEmpty()) {
            Card card = pq2.poll();
            System.out.println(card.rank + " of " + card.suit);
        }
    }
}
