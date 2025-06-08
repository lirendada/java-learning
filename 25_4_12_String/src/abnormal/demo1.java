package abnormal;

public class demo1 {
    public static void main(String[] args) {
        try {
            int a = 10 / 0;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        System.out.println("nice!");
    }
}
