package code_block;

public class OutClass1 {
    public int a = 1;

    class Inner {
        int a = 2;
        int b1 = a;
        int b2 = this.a;

        int b3 = OutClass1.this.a; // 需要通过this.a访问
    }

    public static void main(String[] args) {
        OutClass1 out = new OutClass1();
        Inner in = out.new Inner();
        System.out.println(in.a);
        System.out.println(in.b1);
        System.out.println(in.b2);
        System.out.println(in.b3);
    }
}
