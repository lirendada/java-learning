package Reflection;

/**
 * 使用旧版本的newInstance()方法创建对象
 */
public class demo3 {
    public static void main(String[] args) {
        try {
            // 1. 先获取Class对象
            Class c = Class.forName("Reflection.Student");

            // 2. 然后调用newInstance()方法创建对象，注意要强转成Student类型
            Student p = (Student)c.newInstance();
            System.out.println(p);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
