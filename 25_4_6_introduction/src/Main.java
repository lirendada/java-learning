import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        System.out.println(10);
//        System.out.println(10.250);
//        System.out.println('C');
//        System.out.println("lirendada");
//
//        // 也可以直接使用printf像C语言那样子输出
//        System.out.printf("%s\n", "liren");
//
//        // 也可以不换行的输出
//        System.out.print("lirenIg");
//        System.out.print("lirenIg");
//        System.out.println("");
//
//        System.out.println(true);
//        System.out.println(false);
//    }

//    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MAX_VALUE + 1);
//        System.out.println(2147483647 + 1);
////        System.out.println(2147483648); // ❌这个会报错，因为单独一个数值不参加运算的话是一个字面值
//    }

//    public static void main(String[] args) {
//        // 方式一：在定义时给出初始值
//        int a = 10;
//        System.out.println(a);
//
//        // 方式二：在定义时没有给初始值，但使用前必须设置初值
//        int b;
//        b = 10;
//        System.out.println(b);
//
//        // 使用方式二定义后，在使用前如果没有赋值，则编译期间会报错
//        int c = 200;
//        System.out.println(c);
//        c = 100;
//
//        // int型变量所能表示的范围：
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);
//
//        // 注意：在定义int性变量时，所赋值不能超过int的范围
////        int d = 12345678901234; // 编译时报错，初值超过了int的范围
//    }

//    public static void main(String[] args) {
//        char c1 = 'A'; // 大写字母
//        char c2 = '1'; // 数字字符
//        System.out.println(c1);
//        System.out.println(c2);
//
//        // 注意：java中的字符可以存放整形
//        char c3 = '帅';
//        System.out.println(c3);
//    }

//    public static void main(String[] args) {
//        String s1 = "liren ";
//        String s2 = "love yt";
//
//        System.out.println(s1 + s2); // 拼接两个String类型变量
//        System.out.println(s1 + s2 + s1);
//    }

//    public static void main(String[] args) {
//        int a = 250;
//        String b = String.valueOf(a);
//        System.out.println(b);
//    }

//    public static void main(String[] args) {
////        int a = 10;
////        long b = 10L;
////        b = a;
////        a = (int)b; // long-->int, 数据范围由大到小，需要强转，否则报错
////
////        byte b1 = 12; 		 // 12默认为int，没有超过byte范围，隐式转换
////        byte b2 = (byte)257; // 257默认为int，超过byte范围，需要显示转换，否则报错
////
////        boolean flag = false;
////        a = (int)flag; 	   // 编译失败：类型不兼容
////        flag = (boolean)a; // 编译失败：类型不兼容
//
//        byte a = 10;
//        byte b = 20;
//        byte c = (byte)(a + b);
//        System.out.println(c);
//    }

    public static void main(String[] arge) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println(name);
    }
}