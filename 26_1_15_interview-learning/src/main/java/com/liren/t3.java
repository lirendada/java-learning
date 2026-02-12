package com.liren;

import com.liren.model.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class t3 {
    public static void main1(String[] args) {
        Class<?> clazz = Person.class;
        System.out.println(clazz.getName());
        for(Field e : clazz.getDeclaredFields()) {
            System.out.println(e.getName());
        }
        for(Method m : clazz.getDeclaredMethods()) {
            System.out.println(m.getName());
        }

    }

    public static void main2(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.liren.model.Person");
        System.out.println(clazz.getName());


        Class<?> clazz2 = Person.class;
        System.out.println(clazz.getName());

        System.out.println(clazz == clazz2);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.liren.model.Person");
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Person o = (Person)constructor.newInstance();

        ClassLoader classLoader = clazz.getClassLoader();
    }
}
