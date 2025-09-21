package lambda;

import java.util.Arrays;

class Student implements Comparable<Student> {
    public int age;
    public int score;

    public Student(int age, int score, String name) {
        this.age = age;
        this.score = score;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", score=" + score +
                ", name='" + name + '\'' +
                '}';
    }

    public String name;

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }
}

public class t2 {
    public static void main(String[] args) {
        Student[] s = new Student[]{
                new Student(18, 100, "liren"),
                new Student(24, 86, "yt"),
                new Student(16, 95, "lt")
        };
        Arrays.sort(s);
        System.out.println(Arrays.toString(s));
    }
}
