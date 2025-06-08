package code_block;

public class Student {
    private String name;
    private String gender;
    private int age;
    private double score;
    private static String classRoom;

    // 实例代码块
    {
        this.name = "liren";
        this.age = 12;
        this.gender = "man";
        System.out.println("I am instance init()!");
    }

    // 静态代码块
    static {
        classRoom = "liren306";
        System.out.println("I am static init()!");
    }

    public Student(){
        System.out.println("I am Student init()!");
    }

    public static void main(String[] args) {
        System.out.println("--------------------------");
        Student s1 = new Student();

        System.out.println("--------------------------");
        Student s2 = new Student();
    }
}
