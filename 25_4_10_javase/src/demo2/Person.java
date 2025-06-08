package demo2;

public class Person implements Cloneable {
    public String name;
    Money m = new Money();

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", m=" + m +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person)super.clone();
        p.m = (Money)this.m.clone();
        return p;
    }
}
