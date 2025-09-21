package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class t1 {
    public static void main(String[] args) {
        Stream<Integer> s = Stream.of(1, 2, 3, 4);
        List<Integer> collect = s.filter(x -> x == 2)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
