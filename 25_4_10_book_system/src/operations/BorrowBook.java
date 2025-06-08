package operations;

import book.Book;
import book.BookShelf;

import java.util.Scanner;

public class BorrowBook implements IOperation {
    @Override
    public void work(BookShelf bs) {
        System.out.println("请输入要借的书名");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        for(int i = 0; i < bs.getSize(); i++) {
            if(bs.getBook(i).getName().equals(name)) {
                if(bs.getBook(i).isAvailable() == true) {
                    System.out.println("该书已经被借了！");
                    return;
                } else {
                    System.out.println("该书空闲，可以借阅！");
                    Book b = bs.getBook(i);
                    b.setAvailable(true);
                    return;
                }
            }
        }
        System.out.println("没找到该书！");
    }
}
