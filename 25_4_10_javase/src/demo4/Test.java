package demo4;

public class Test {
    public static void main(String[] args) {
        Student s = new Student() {
            @Override
            public void func() {
                System.out.println("不做作业...");
            }
        };
        s.func();
    }
}
