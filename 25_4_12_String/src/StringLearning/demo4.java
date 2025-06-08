package StringLearning;

/**
 * 测试一下String和StringBuilder的速度
 */
public class demo4 {
    public static void main(String[] args) {
        String s1 = "";
        System.out.println(s1.isEmpty());
        System.out.println(s1.isBlank());
        StringBuilder s2 = new StringBuilder();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            s1 += i;
        }
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            s2.append(i);
        }
        long end2 = System.currentTimeMillis();

        System.out.println("String: " + (end1 - start1) + "ms");
        System.out.println("StringBuilder: " + (end2 - start2) + "ms");
    }
}
