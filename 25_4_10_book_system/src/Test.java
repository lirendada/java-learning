import book.BookShelf;
import user.Administrator;
import user.Client;
import user.User;

import java.util.Scanner;

public class Test {
    public static User login() {
        System.out.println("请输入用户名称：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.println("请输入用户类型： 1.普通用户  2.管理员");
        int type = scanner.nextInt();
        if(type == 1) {
            return new Client(username);
        } else if(type == 2) {
            return new Administrator(username);
        }
        return null;
    }

    public static void main(String[] args) {
        BookShelf bs = new BookShelf();

        User user = login(); // 返回用户类型，回来用User接收，形成向上转型与多态
        if(user == null) {
            return;
        }
        while(true) {
            int choice = user.menu();
            user.doSomething(choice, bs);
        }
    }
}
