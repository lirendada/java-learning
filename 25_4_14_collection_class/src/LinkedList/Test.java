package LinkedList;

public class Test {
    public static void main(String[] args) {
        MySingleList list = new MySingleList();
        list.createList();
        list.display();
        list.addFirst(10);
        list.display();
        list.addLast(20);
        list.display();
//        list.clear();
//        list.display();

        list.addIndex(0, 10);
        list.addIndex(1, 20);
        list.addIndex(2, 30);
        list.addIndex(3, 40);
        list.addIndex(list.size(), 660);
        list.display();

//        list.remove(10);
//        list.display();
        list.remove(20);
        list.display();
//        list.remove(666);
//        list.display();

        list.removeAllKey(10);
        list.display();
        list.removeAllKey(20);
        list.display();
    }
}
