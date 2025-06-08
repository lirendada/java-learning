package polymorphism.demo2;

public class TestDemo {
    public static void func(Animal ani) {
        ani.eat();
    }

    public static void main(String[] args) {
        Dog dog = new Dog(18, "liren");
        Cat cat = new Cat(19, "yt");

        func(dog);
        func(cat);
    }
}
