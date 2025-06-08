package polymorphism.demo1;

public class Cat extends Animal{

    public Cat(int age, String name) {
        super(age, name);
    }

    public void miao() {
        System.out.println(this.name + "正在喵喵叫");
    }

    @Override
    public void eat() {
        System.out.println(this.name + "正在吃猫粮...");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
