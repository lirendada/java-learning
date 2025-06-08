package Class;
import static java.lang.Math.pow;

public class main {
    public static void main1(String[] args) {
        WashMachine wmach = new WashMachine();
        wmach.WashClothes();
        wmach.dryClothes();
        wmach.SetTime();
    }

    public static void main2(String[] args) {
        TestDate d1 = new TestDate();
        d1.printDate();

        d1.setDate(2022, 10, 7);
        d1.printDate();
    }

    public static void main(String[] args) {
        Computer p = new Computer("HW", "i7", "8G", "13*14");
        System.out.println(p.brand); // default属性：只能被本包中类访问
        System.out.println(p.screen); // public属性： 可以任何其他类访问
//        System.out.println(p.cpu); // private属性：只能在Computer类中访问，不能被其他类访问
        System.out.println(pow(2, 2));
    }
}
