package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过Constructor系列的方法创建对象
 */
public class demo4 {
    public static void main(String[] args) {
        try {
            // 1. 获取Class对象
            Class<?> cs = Class.forName("Reflection.Student");

            // 2. 获取无参的构造方法来创建对象
            Constructor<?> non_arg_constructor = cs.getDeclaredConstructor();
            Student s1 = (Student)non_arg_constructor.newInstance();
            System.out.println(s1);

            // 3. 获取有参的构造方法来创建对象，因为是私有的，所以要设置权限为true
            Constructor<?> arg_constructor = cs.getDeclaredConstructor(String.class, int.class);
            arg_constructor.setAccessible(true);
            Student s2 = (Student)arg_constructor.newInstance("liren", 18);
            System.out.println(s2);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
