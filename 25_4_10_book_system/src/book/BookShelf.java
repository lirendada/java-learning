package book;

public class BookShelf {
    private static final int DEFAULT_SIZE = 16;
    private Book[] books = new Book[DEFAULT_SIZE];
    private int size;

    public BookShelf() {
        this.books[0] = new Book("UNIX环境高级编程", "人邮", 13.14, "Program");
        this.books[1] = new Book("STL源码剖析", "侯捷", 66.6, "Program");
        this.books[2] = new Book("C++实战笔记", "罗剑锋", 52.0, "Coding");
        this.size = 3;
    }

    public Book getBook(int i) {
        return books[i];
    }

    public void setBook(Book book, int i) {
        books[i] = book;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
