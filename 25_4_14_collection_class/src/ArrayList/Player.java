package ArrayList;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards;

    public Player() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
