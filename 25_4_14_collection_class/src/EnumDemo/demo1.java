package EnumDemo;

public class demo1 {
    public static void main(String[] args) {
        Color1 color1 = Color1.RED;
        System.out.println(color1.name());    // 输出颜色名称
        System.out.println(color1.ordinal()); // 输出颜色的序号
        System.out.println(color1); // 直接输出颜色对象
        System.out.println(color1.describeConstable()); // 输出颜色的描述信息
        System.out.println(color1.valueOf("BLACK")); // 通过名称获取枚举值

        // 有个比较特殊的是values()方法，它可以返回所有枚举值数组，它不存在Enum中，而是在编译时由编译器生成的。
        // 但是如果枚举值太多，可能导致内存溢出，所以一般不用这个方法。
        System.out.println("----------------------输出所有枚举元素：");
        Color1[] Color1s = Color1.values();
        for(int i = 0; i < Color1s.length; ++i) {
            System.out.println(Color1s[i] + " " + Color1s[i].ordinal());
        }

//        Color1 c2 = new Color1(); // ❌不能实例化枚举类，只能通过枚举值访问其属性和方法。
    }
}
