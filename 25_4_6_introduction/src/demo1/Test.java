package demo1;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Student[] arr = new Student[] {
            new Student("zhangsan", 18, 99),
            new Student("lisi", 16, 70),
            new Student("wangwu", 27, 80)
        };

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
