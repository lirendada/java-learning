package StringLearning;

import java.util.Arrays;

/**
 * 测试字符串分割中转义字符
 */
public class demo3 {
    public static void main(String[] args) {
        String s = "lianwi&Nkqnwl%oino123)asd|kk";
        String[] ret = s.split("&|%|\\)|\\|"); // 让&、%、)、|作为分隔符，彼此之间用|连接起来！
        System.out.println(Arrays.toString(ret));
    }
}
