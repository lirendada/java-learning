package lambda;

import java.util.Comparator;

public class t3 {
    public static void main(String[] args) {
        Comparator<Integer> cmp1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(cmp1.compare(12, 21));

        Comparator<Integer> cmp2 = (o1, o2) -> {
            return Integer.compare(o1, o2);
        };
        System.out.println(cmp2.compare(21, 22));

        Comparator<Integer> cmp3 = Integer::compare;
        System.out.println(cmp3.compare(22, 21));
    }
}
