package demo2;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person s1 = new Person("lirendada");
        Person s2 = (Person)s1.clone();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println("------------");

        s2.m.money = 200;

        System.out.println(s1);
        System.out.println(s2);
    }
}
