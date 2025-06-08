package Static;

public class TestStatic {
//    public int age;
//    static int a1 = 10;
//
//    public static void main(String[] args) {
//        testStatic s1 = new testStatic();
//        testStatic s2 = new testStatic();
//        testStatic s3 = new testStatic();
//
//        System.out.println(testStatic.a1);//通过类名访问
//
//        System.out.println(s1.a1);//通过s1对象访问
//        System.out.println(s2.a1);//通过s2对象访问
//        System.out.println(s3.a1);//通过s3对象访问
//    }

    public static int count = 1; // 定义静态变量count

    public int method1() {
        count++;    // 访问静态变量count并赋值
        System.out.println("在静态方法 method1()中的 count="+count);
        return count;
    }

    public static int method2() {
        count += count;    // 访问静态变量count并赋值
        System.out.println("在静态方法 method2()中的 count="+count);
          return count;
    }

    public static void PrintCount() {
        count += 2;
        System.out.println("在静态方法 PrintCount()中的 count="+count);
    }

    public static void main(String[] args) {
        TestStatic sft = new TestStatic();

        // 1. 通过实例对象调用实例方法
        System.out.println("method1() 方法返回值 intro1="+sft.method1());

        // 2. 直接调用静态方法
        System.out.println("method2() 方法返回值 intro1="+method2());

        // 3. 通过类名调用静态方法，打印 count
        TestStatic.PrintCount();
    }
}
