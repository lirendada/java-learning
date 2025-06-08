package operations;

import book.Book;
import book.BookShelf;

import java.util.Scanner;

public class AddBook implements IOperation {
    @Override
    public void work(BookShelf bs) {
        System.out.print("请输入书名：");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();

        System.out.print("请输入作者：");
        String author = scanner.nextLine();

        System.out.print("请输入价格：");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("请输入类型：");
        String type = scanner.nextLine();

        Book book = new Book(bookName, author, price, type);

        for(int i = 0; i < bs.getSize(); ++i) {
            if(bs.getBook(i).getName().equals(bookName)) {
                System.out.println("已存在该书，不可以上架了！");
            }
        }
        bs.setBook(book, bs.getSize());
        bs.setSize(bs.getSize() + 1);

        System.out.println("添加书籍成功！");
    }
}
