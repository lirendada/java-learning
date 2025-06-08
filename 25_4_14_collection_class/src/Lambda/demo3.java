package Lambda;

@FunctionalInterface
interface MyInterface {
    void myMethod();
}

public class demo3 {

    public static void main(String[] args) {
        int a = 20;
        MyInterface myInterface = () -> {
//            int a = 10;
            System.out.println(a);
        };
        myInterface.myMethod();
    }
}
