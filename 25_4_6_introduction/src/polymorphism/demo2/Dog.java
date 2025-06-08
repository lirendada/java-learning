package polymorphism.demo2;

public class Dog extends Animal {

    public Dog(int age, String name) {
        super(age, name);
    }

    @Override
    public void eat() {
        System.out.println(this.name + "正在吃狗粮...");
    }
}
