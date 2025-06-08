package Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static boolean isSorted(int[] arr, int n) {
        for(int i = 0; i < n - 1; ++i) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[100000];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000000);
        }

        Sort sort = new Sort();
        long start = System.currentTimeMillis();
        sort.insertSort(arr, arr.length);
        long end = System.currentTimeMillis();
        System.out.println("插入排序花费：" + (end - start) + "ms");
        System.out.println("是否排序：" + isSorted(arr, arr.length));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000000);
        }
        start = System.currentTimeMillis();
        sort.shellSort(arr, arr.length);
        end = System.currentTimeMillis();
        System.out.println("希尔排序花费：" + (end - start) + "ms");
        System.out.println("是否排序：" + isSorted(arr, arr.length));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000000);
        }
        start = System.currentTimeMillis();
        sort.selectSort(arr, arr.length);
        end = System.currentTimeMillis();
        System.out.println("选择排序花费：" + (end - start) + "ms");
        System.out.println("是否排序：" + isSorted(arr, arr.length));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000000);
        }
        start = System.currentTimeMillis();
        sort.heapSort(arr, arr.length);
        end = System.currentTimeMillis();
        System.out.println("堆排序花费：" + (end - start) + "ms");
        System.out.println("是否排序：" + isSorted(arr, arr.length));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000000);
        }
        start = System.currentTimeMillis();
        sort.quickSort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println("递归快速排序花费：" + (end - start) + "ms");
        System.out.println("是否排序：" + isSorted(arr, arr.length));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000000);
        }
        start = System.currentTimeMillis();
        sort.quickSortNonRecursive(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println("非递归快速排序花费：" + (end - start) + "ms");
        System.out.println("是否排序：" + isSorted(arr, arr.length));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000000);
        }
        start = System.currentTimeMillis();
        sort.mergeSort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println("归并排序花费：" + (end - start) + "ms");
        System.out.println("是否排序：" + isSorted(arr, arr.length));
    }
}
