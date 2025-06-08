package LinkedList;

public class MySingleList implements IList {
    @Override
    public void addFirst(int data) {
        Node newnode = new Node(data);
        newnode.next = head;
        head = newnode;
        listsize++;
    }

    @Override
    public void addLast(int data) {
        Node newnode = new Node(data);
        if(head == null) {
            head = newnode;
        } else {
            Node cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newnode;
        }
    }

    @Override
    public void addIndex(int index, int data) {
        if(index < 0 || index > listsize) {
            throw new IndexOutOfBoundsException("index越界了！index=" + index);
        }

        if(index == 0) {
            addFirst(data);
            return;
        }
        if(index == listsize) {
            addLast(data);
            return;
        }

        Node newnode = new Node(data);
        Node cur = head;
        for(int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        newnode.next = cur.next;
        cur.next = newnode;
        listsize++;
    }

    @Override
    public boolean contains(int key) {
        Node cur = head;
        while(cur != null) {
            if(cur.value == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public void remove(int key) {
        Node pre = null, cur = head;
        while (cur != null) {
            if (cur.value == key) {
                if(pre == null) {
                    head = cur.next;
                } else {
                    pre.next = cur.next;
                }
                listsize--;
                return;
            }
            pre = cur;
            cur = cur.next;
        }
        throw new IllegalArgumentException("没有找到要删除的元素！");
    }

    @Override
    public void removeAllKey(int key) {
        Node pre = null, cur = head;
        while(cur != null) {
            if(cur.value == key) {
                if(pre == null) {
                    head = cur.next;
                } else {
                    pre.next = cur.next;
                }
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
    }

    @Override
    public int size() {
        return listsize;
    }

    @Override
    public void clear() {
        head = null;
        listsize = 0;
    }

    @Override
    public void display() {
        Node cur = head;
        while(cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        public int getValue() {
            return value;
        }
    }

    private Node head; // 头节点
    private int listsize; // 链表长度

    public void createList() {

        Node node1 = new Node(12);

        Node node2 = new Node(23);
        Node node3 = new Node(34);
        Node node4 = new Node(45);
        Node node5 = new Node(56);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        this.head = node1;
    }

    public MySingleList() {
        this.listsize = 0;
        this.head = null;
    }
}
