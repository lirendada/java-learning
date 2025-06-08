package Lambda;

@FunctionalInterface
interface MyInterface1 {
    void myMethod();
}

@FunctionalInterface
interface MyInterface2 {
    int myMethod(int a, int b);
}

public class demo2 {
    public static void main(String[] args) {
        MyInterface1 myif1 = () -> System.out.println("无参无返回值方法");
        myif1.myMethod();

        MyInterface2 myif2 = (a, b) -> a + b;
        System.out.println("有参数有返回值：" + myif2.myMethod(2, 3));
    }
}
