package abnormal;

public class demo2 {
    public static int func(int n) {
        // 除零则抛出异常
        if(n == 0) {
            throw new ArithmeticException("除0异常");
        }
        return 1/n;
    }
    public static void main(String[] args) {
        try {
            System.out.println(func(0));
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
