import java.util.Arrays;

public class TestArray {
    public static void main1(String[] args) {
        int[] arr = new int[20];
        arr[0] = 1;
        arr[20] = 10;
    }

    public static void main2(String[] args) {
        // 对于基本类型无法对其取地址，因为基本类型在空间中是直接存放的
        int a = 10;
        float b = 20.1f;

        // 打印数组名其实就是打印其地址
        // 因为java中的数组其实new出来的，也就是在堆上的
        // 而arr数组名其实存放的是一个地址，类似于指针指向堆中的数据
        int[] arr = {1,3,5,7,9};
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void main3(String[] args) {
        int[] array1 = new int[3];
        array1[0] = 10;
        array1[1] = 20;
        array1[2] = 30;

        int[] array2 = new int[]{1,2,3,4,5};
        array2[0] = 100;
        array2[1] = 200;
        System.out.println(array1);
        System.out.println(array2);

        array1 = array2;
        array1[2] = 300;
        array1[3] = 400;
        array2[4] = 500;
        System.out.println(array1);
        System.out.println(array2);
        for (int i : array2) {
            System.out.println(i);
        }
    }

    public static void func4(int[] tmp) {
        tmp[0] = 199;
    }

    public static void main4(String[] args) {
        int[] arr = {2,4,6,7,8};
        System.out.println(Arrays.toString(arr));

        func4(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void func5(int[] tmp) {
        tmp[0] = 199;
    }

    public static void newArr5(int[] tmp) {
        tmp = new int[10];
    }

    public static void main5(String[] args) {
        int[] arr1 = {2,4,6,7,8};
        func5(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {2,4,6,7,8};
        newArr5(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    public static int[] getArr() {
        int[] arr = new int[10];
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = getArr();
        arr[0] = 1;

        int[] arr2 = Arrays.copyOf(arr, arr.length);
        arr2[0] = 3;
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        
    }
}
