package code_block;

public class OutClass {
    public int data1 = 1;
    int data2 = 2;
    private int data3 = 3;

    public OutClass(int data1, int data2, int data3, InnerClass inAndOut) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.inAndOut = inAndOut;
    }

    public OutClass() {}

    // 这里以后面要讲的实例内部类举例子
    class InnerClass {
        public int data4 = 4;
        int data5 = 5;
        private int data6 = 6;

        public int getSum() {
            return data4 + data5 + data6;
        }
    }

    // 在外部类中可以直接通过内部类的类名访问内部类
    InnerClass inAndOut = new InnerClass();
    public void printInSum() {
        System.out.println(inAndOut.getSum());
    }

    // 由于main是静态方法，所以无法向上边这种方法一样访问内部类
    // 但是我们可以通过内部类的完整类名： 外部类名.内部类名 -》访问内部类
    public static void main(String[] args) {
        // 方法1：直接通过OutClass()的类名来获取内部类
        OutClass.InnerClass in1 = new OutClass().new InnerClass();
        System.out.println(in1.getSum());

        // 方法2：通过OutClass()的对象来获取内部类
        OutClass out = new OutClass();
        OutClass.InnerClass in2 = out.new InnerClass();
        System.out.println(in2.getSum());

        // 方法3：在外部类中可以直接通过内部类的类名访问内部类（看上面）
        out.printInSum();
    }
}