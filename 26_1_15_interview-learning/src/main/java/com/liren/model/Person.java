package com.liren.model;

public class Person implements Cloneable {
    private Address address;

    public Person(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    static {
        System.out.println("静态代码初始化");
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        Person tmp = (Person) super.clone();
        tmp.setAddress(tmp.getAddress().clone());
        return tmp;
    }

    @Override
    public String toString() {
        return "Person{" +
                "address=" + address +
                '}';
    }
}
