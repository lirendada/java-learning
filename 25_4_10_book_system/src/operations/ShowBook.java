package operations;

import book.BookShelf;

public class ShowBook implements IOperation {
    @Override
    public void work(BookShelf bs) {
        System.out.println("显示书籍");
    }
}
