package polymorphism.demo1;

public class TestDemo {
    public static void main(String[] args) {
        Animal ani = new Cat(3, "yt");
        ani.eat();
        System.out.println(ani);

        if(ani instanceof Cat) {
            Cat cat = (Cat)ani;
            cat.miao();
        }
    }
}
