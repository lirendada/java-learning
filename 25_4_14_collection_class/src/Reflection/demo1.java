package Reflection;

class Student {
    // 私有属性name
    private String name = "bit";
    // 公有属性age
    public int age = 18;

    // 不带参数的构造⽅法
    public Student() {
        System.out.println("Student()");
    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Student(String,name)");
    }

    private void eat() {
        System.out.println("i am eat");
    }

    void sleep() {
        System.out.println("i am pig");
    }

    private void function(String str) {
        System.out.println(str);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class demo1 {
    public static void main(String[] args){
        // 1. 使用Class.forName()，注意要指定包名，并且最好捕捉异常
        Class<?> c1 = null;
        try {
            c1 = Class.forName("Reflection.Student");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2. 使用指定类的class静态变量
        Class<?> c2 = Student.class;

        // 3. 使用对象实例的getClass()方法
        Student st = new Student();
        Class<?> c3 = st.getClass();

        // 一个类在JVM中只会有一个Class实例，因此c1、c2、c3都指向同一个Class实例
        System.out.println(c1 == c2); // true
        System.out.println(c2 == c3); // true
        System.out.println(c3 == c1);
    }
}
