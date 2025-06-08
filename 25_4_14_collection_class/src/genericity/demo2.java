package genericity;

public class demo2 {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b); // true

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d); // false

        Integer e = -128;
        Integer f = -128;
        System.out.println(e == f); // true

        Integer g = -129;
        Integer h = -129;
        System.out.println(g == h); // false
    }
}
