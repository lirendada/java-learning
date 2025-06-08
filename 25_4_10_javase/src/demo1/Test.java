package demo1;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 18, 60);
        Student s2 = new Student("lisi", 15, 99);
        Student[] s = {s1, s2};

        // 使用年龄对比排序
        AgeComp ac = new AgeComp();
        /*if(ac.compare(s1, s2) > 0)
            System.out.println("zhangsan大!");
        else
            System.out.println("lisi大!");*/
        Arrays.sort(s, ac);
        System.out.println(Arrays.toString(s));

        // 使用分数对比排序
        ScoreComp sc = new ScoreComp();
        Arrays.sort(s, sc);
        System.out.println(Arrays.toString(s));
    }
}
