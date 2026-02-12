package com.liren;

import com.liren.model.Address;
import com.liren.model.Person;

public class t2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person(new Address("武汉"));
        Person personCopy = person.clone();
        System.out.println(person.getAddress() == personCopy.getAddress());
    }
}