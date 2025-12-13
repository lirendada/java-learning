package predicate;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.function.Predicate;

public class test {
    @Test
    public void t1() {
        Predicate p = new StringPredicate();
        System.out.println(p.test("hello"));
        System.out.println(p.test(""));
    }

    /**
     * 匿名内部类写法
     */
    @Test
    public void t2() {
        Predicate<String> p = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 0;
            }
        };
        System.out.println(p.test("hello")); // true
        System.out.println(p.test(""));      // false
    }

    /**
     * lambda表达式写法
     */
    @Test
    public void t3() {
        Predicate<String> p = s -> s.length() > 0;
        System.out.println(p.test("hello")); // true
        System.out.println(p.test(""));      // false
    }

    /**
     * 非操作
     */
    @Test
    public void t4() {
        Predicate<String> p = s -> s.length() > 0;
        System.out.println(p.negate().test("hello")); // false
        System.out.println(p.negate().test(""));      // true
    }

    /**
     * and操作
     */
    @Test
    public void t5() {
        Predicate<String> p1 = s -> s.length() > 0;
        Predicate<String> p2 = s -> s.startsWith("l");
        System.out.println(p1.and(p2).test("hello")); // false
        System.out.println(p1.and(p2).test("liren")); // true
    }

    /**
     * or操作
     */
    @Test
    public void t6() {
        Predicate<String> p1 = s -> s.length() > 0;
        Predicate<String> p2 = s -> s.startsWith("l");
        System.out.println(p1.or(p2).test("hello")); // true
        System.out.println(p1.or(p2).test("liren")); // true
        System.out.println(p1.or(p2).test(""));      // false
    }

    @Test
    public void test() {
        System.out.println(ZonedDateTime.now());
    }
}
