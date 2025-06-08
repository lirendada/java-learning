package abnormal;

public class demo3 {
    public static void func1() {
        throw new NullPointerException("空指针异常");
    }

    public static void func2() {
        try {
            func1();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("1111111111111");
        }
    }

    public static void main(String[] args) {
        try {
            func2();
        } catch (NullPointerException e) {
            e.printStackTrace();                // 打印信息最全面
        }
    }
}
