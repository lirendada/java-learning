package user;

import operations.*;

import java.util.Scanner;

public class Client extends User {

    public Client(String name) {
        super(name);
        this.operations = new IOperation[] {
                new ExitSystem(),
                new FindBook(),
                new BorrowBook(),
                new ReturnBook()
        };
    }

    @Override
    public int menu() {
        System.out.println("欢迎 "+this.getName()+" 来到图书管理系统!");
        System.out.println("**********普通用户菜单*********");
        System.out.println("1. 查找图书");
        System.out.println("2. 借阅图书");
        System.out.println("3. 归还图书");
        System.out.println("0. 退出系统");
        System.out.println("*****************************");
        System.out.println("请输入你的操作：");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
}
