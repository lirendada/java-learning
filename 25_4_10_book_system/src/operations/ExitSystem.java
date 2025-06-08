package operations;

import book.BookShelf;

public class ExitSystem implements IOperation {
    @Override
    public void work(BookShelf bs) {
        System.out.println("退出系统");
    }
}
