package operations;

import book.BookShelf;

public class DelBook implements IOperation {
    @Override
    public void work(BookShelf bs) {
        System.out.println("删除书籍");
    }
}
