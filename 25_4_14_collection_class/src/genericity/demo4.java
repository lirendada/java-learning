package genericity;

public class demo4 {
    public <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};
        System.out.println("交换前：" + arr[1] + " " + arr[3]);
        demo4 d = new demo4();
        d.swap(arr, 1, 3);
        System.out.println("交换后：" + arr[1] + " " + arr[3]);
    }
}
