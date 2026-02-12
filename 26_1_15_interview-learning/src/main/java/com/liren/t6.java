package com.liren;

import java.util.Arrays;
import java.util.Random;

public class t6 {
    public static void main(String[] args) {
        // 1. 构造测试数据（数量偏大）
        int size = 50; // 可以改成 1000 / 10000
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            // [-100, 100] 范围，包含负数和重复值
            arr[i] = random.nextInt(201) - 100;
        }

        // 2. 排序前打印
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        // 3. 排序
//        quick_sort(arr, 0, arr.length - 1);
        heap_sort(arr, arr.length);

        // 4. 排序后打印
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
        System.out.println("是否升序：" + isSortedAscending(arr));
    }

    public static boolean isSortedAscending(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }


    public static void quick_sort(int[] arr, int left, int right) {
        if(left >= right) {
            return;
        }

        int key = quick(arr, left, right);
        quick_sort(arr, left, key - 1);
        quick_sort(arr, key + 1, right);
    }

    public static int quick(int[] arr, int left, int right) {
        int gap = arr[left];
        while(left < right) {
            while(left < right && arr[right] >= gap) {
                right--;
            }
            if(left < right) {
                arr[left++] = arr[right];
            }

            while(left < right && arr[left] <= gap) {
                left++;
            }
            if(left < right) {
                arr[right--] = arr[left];
            }
        }
        arr[left] = gap;
        return left;
    }

    public static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    public static void heap_sort(int[] arr, int n) {
        // 先建大堆
        for(int i = (n - 2) / 2; i >= 0; --i) {
            heap_down(arr, i, n);
        }

        // 排序
        int end = n - 1;
        while(end > 0) {
            swap(arr, 0, end);
            heap_down(arr, 0, end);
            end--;
        }
    }

    public static void heap_down(int[] arr, int root, int n) {
        int parent = root;
        int i = (parent * 2) + 1;
        while(i < n) {
            if(i + 1 < n && arr[i] < arr[i + 1]) {
                i++;
            }

            if(arr[parent] < arr[i]) {
                swap(arr, parent, i);
                parent = i;
                i = (parent * 2) + 1;
            } else {
                break;
            }
        }
    }
}
