package code_block;

public class TestStatic {
    public static void main(String[] args) {
        {
            int x = 10;
            System.out.println(x);
        }
        int x = 100;
        System.out.println(x);
    }
}
