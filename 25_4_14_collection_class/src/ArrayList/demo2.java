package ArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class demo2 {
    private static String[] colors =  {"♠", "♦", "♥", "♣"};
    private static String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public static void main(String[] args) {
        // 得到一副牌
        List<Card> card = getCard();
        System.out.println(card);

        // 洗牌
        shuffle(card);
        System.out.println(card);

        // 发牌
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < 3; ++i) {
            players.add(new Player());
        }

        for(int i = 0; i < 5; ++i) {
            for(int j = 1; j <= 3; ++j) {
                players.get(j-1).addCard(card.get(i*3+j-1));
            }
        }

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < players.get(i).getCards().size(); ++j) {
                System.out.print(players.get(i).getCards().get(j) + " ");
            }
            System.out.println();
        }
    }

    private static List<Card> getCard() {
        List<Card> list = new ArrayList<>();
        for(String color : colors) {
            for(String number : numbers) {
                list.add(new Card(color, number));
            }
        }
        return list;
    }

    private static void shuffle(List<Card> list) {
        Random random = new Random();
        for(int i = 0; i < list.size(); i++) {
            int index = random.nextInt(list.size()); // 获取随机下标
            // 交换元素
            Card tmp = new Card(list.get(i).getSuit(), list.get(i).getValue());
            list.set(i, list.get(index));
            list.set(index, tmp);
        }
    }
}
