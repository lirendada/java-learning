package operations;

import book.BookShelf;

public class FindBook implements IOperation {
    @Override
    public void work(BookShelf bs) {
        System.out.println("查阅书籍");
    }
}
