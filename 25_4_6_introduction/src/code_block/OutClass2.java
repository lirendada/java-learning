package code_block;

public class OutClass2 {
    // 静态内部类
    static class InnerClass {
    }

    public static void main(String[] args) {
        // 不需要创建外部类的实例
        InnerClass in = new InnerClass();
    }
}

class OtherClass {
    public static void main(String[] args) {
        OutClass2.InnerClass in = new OutClass2.InnerClass();
    }
}

