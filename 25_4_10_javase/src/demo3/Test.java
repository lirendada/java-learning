package demo3;

public class Test {
    public static void main(String[] args) {
        Person a = new Person("liren");
        Person b = new Person("yt");
        Person c = new Person("liren");

        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(b.equals(c));

        System.out.println("---------------");

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
    }
}
