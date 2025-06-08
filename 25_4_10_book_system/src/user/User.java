package user;

import book.Book;
import book.BookShelf;
import operations.IOperation;

public abstract class User {
    private String name;
    protected IOperation[] operations; // 不要初始化

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int menu();

    public void doSomething(int choice, BookShelf bs) {
        IOperation op = operations[choice];
        op.work(bs);
    }
}
