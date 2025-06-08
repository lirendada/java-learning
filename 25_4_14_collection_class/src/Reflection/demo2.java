package Reflection;

import java.lang.reflect.*;

/**
 * 反射操作字段
 */
public class demo2 {
    public static void main(String[] args) {
        // 使用反射操作字段
        try {
            Class<?> cs = Class.forName("Reflection.Student");

            // 1. 输出类名，打印字段信息
            System.out.println("ClassName: " + cs.getName());
            for(Field e : cs.getDeclaredFields()) {
                System.out.println(e.getName() + " " + e.getType().getName() + " " + Modifier.toString(e.getModifiers()));
            }

            // 2.1 获取私有属性name
            Field namefield = cs.getDeclaredField("name");
            namefield.setAccessible(true); // 对于私有属性，需要设置Accessible为true

            // 2.2 获取该类对象，并设置name属性的值，然后用namefield.get()获取这个私有值
            Student st = (Student)cs.newInstance();
            namefield.set(st, "liren");
            System.out.println("反射私有属性修改了name：" + namefield.get(st));

        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
