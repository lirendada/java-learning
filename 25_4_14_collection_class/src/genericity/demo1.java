package genericity;

/**
 * 自动装箱与拆箱
 */
public class demo1 {
    public static void main(String[] args) {
        Integer i = 10;
        int j = i; // 自动装箱
        System.out.println(i); // 输出 10
        System.out.println(j); // 输出 10

        int a = 20;
        Integer b = a; // 自动拆箱
        System.out.println(a); // 输出 20
        System.out.println(b); // 输出 20
    }
}
