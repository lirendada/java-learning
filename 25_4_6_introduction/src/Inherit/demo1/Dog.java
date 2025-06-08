package Inherit.demo1;

public class Dog extends Animal {
    public Dog(String name) {
        this.name = name;
    }

    public void bark() {
        System.out.println(name + "吃饭中...");
    }

    public void wag() {
        System.out.println(name + "摇尾巴...");
    }
}
