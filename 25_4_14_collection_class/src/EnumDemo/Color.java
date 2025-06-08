package EnumDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum Color {
    RED("red", 1), GREEN("green", 2), BLUE("blue", 3);

    private String name;
    private int value;

    // 构造函数默认就是private，不写也可以，但是不能写其它的访问权限
    private Color(String name, int value) {
        this.name = name;
        this.value = value;
    }

    Color() {
        this("default", 0);
    }

    public static void main(String[] args) {
        try {
            // 先获取构造函数，再设置Accessible为true
            Class<?> cs = Class.forName("EnumDemo.Color");
            Constructor<?> ctor = cs.getDeclaredConstructor(String.class, int.class, String.class, int.class);
            ctor.setAccessible(true);

            // 调用构造函数创建对象
            Color c = (Color)ctor.newInstance("yellow", 4);
            System.out.println(c.name + " " + c.value);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main2(String[] args) {
//        Color c = new Color(); // ❌无法实例化枚举类型
    }

    public static void main1(String[] args) {
        Color c1 = Color.RED;
        Color c2 = Color.RED;
        System.out.println(c1 == c2); // true
    }
}
