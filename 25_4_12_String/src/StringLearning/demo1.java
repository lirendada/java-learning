package StringLearning;

/**
 * 构造方法
 */
public class demo1 {
    public static void main(String[] args) {
        // 1. 常量字符串构造（推荐！！！）
        String s1 = "abc";
        System.out.println(s1);

        // 2. 使用new构造对象（不推荐！！！）
        String s2 = new String("abc");
        System.out.println(s2);

        // 3. 使用字符数组构造（还是会转变成字符处理）
        byte[] arr = new byte[]{ 97, 98, 99 };
        String s3 = new String(arr);
        System.out.println(s3);
    }
}
