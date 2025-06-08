package Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 操作类的方法
 */
public class demo5 {
    public static void main(String[] args) {
        try {
            Class<Student> cs = Student.class;

            // 获取带参的私有方法，然后设置权限，最后使用invoke()方法进行调用
            Method func_method = cs.getDeclaredMethod("function", String.class);
            func_method.setAccessible(true);
            func_method.invoke((Student)cs.newInstance(), "我是给私有的function函数传的参数");

            // 还可以输出方法的指定信息
            System.out.println("方法名：" + func_method.getName());
            System.out.println("修饰符为：" + Modifier.toString(func_method.getModifiers()));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
