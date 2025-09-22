package collection;

public class t1 {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b); // true

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d); // false
        System.out.println(c.equals(d));

        Integer e = -128;
        Integer f = -128;
        System.out.println(e == f); // true
        System.out.println(e.equals(f));

        Integer g = -129;
        Integer h = -129;
        System.out.println(g == h); // false
        System.out.println(g.equals(h));
    }
}
