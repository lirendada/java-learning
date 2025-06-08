package operations;

import book.BookShelf;

public class ReturnBook implements IOperation {
    @Override
    public void work(BookShelf bs) {
        System.out.println("归还书籍");
    }
}
