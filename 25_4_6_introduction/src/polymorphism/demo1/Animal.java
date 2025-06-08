package polymorphism.demo1;

public class Animal {
    public int age;
    public String name;

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void eat() {
        System.out.println(this.name + "正在吃饭...");
    }
}
