package genericity;

class MyClass<T> {
    T a;
    T[] b = (T[])new Object[10]; // 数组的初始化仍然需要Object来构造，并且进行强制类型转换
}

public class demo3 {
    public static void main(String[] args) {
        // 泛型类的实例化
        MyClass<String> a = new MyClass<String>();
        MyClass<Integer> b = new MyClass<Integer>();
    }
}
