package Sort;

import java.util.ArrayDeque;
import java.util.Deque;

public class Sort {
    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void insertSort(int[] arr, int n){
        for(int i = 0; i < n - 1; ++i) {
            int end = i;
            int tmp = arr[end + 1];
            while(end >= 0) {
                if(arr[end] > tmp) {
                    arr[end + 1] = arr[end];
                    end--;
                } else {
                    break;
                }
            }
            arr[end + 1] = tmp;
        }
    }

    public void shellSort(int[] arr, int n) {
        int gap = n;
        while(gap > 1) {
            gap = (gap / 3) + 1;
            for(int i = 0; i < n - gap; ++i) {
                int end = i;
                int tmp = arr[end + gap];
                while(end >= 0) {
                    if(arr[end] > tmp) {
                        arr[end + gap] = arr[end];
                        end -= gap;
                    } else {
                        break;
                    }
                }
                arr[end + gap] = tmp;
            }
        }
    }

    public void selectSort(int[] arr, int n) {
        int left = 0, right = n - 1;
        while(left <= right) {
            int min = left, max = left;
            for(int i = left; i <= right; ++i) {
                if(arr[i] < arr[min]) {
                    min = i;
                }
                if(arr[i] > arr[max]) {
                    max = i;
                }
            }

            swap(arr, min, left);
            if(left == max) {
                max = min;
            }
            swap(arr, max, right);
            left++;
            right--;
        }
    }

    public void heapSort(int[] arr, int n) {
        for(int i = (n - 1 - 1) / 2; i >= 0; --i) {
            adjustDown(arr, n, i);
        }

        int end = n - 1;
        while(end > 0) {
            swap(arr, 0, end);
            adjustDown(arr, end, 0);
            end--;
        }
    }

    private void adjustDown(int[] arr, int n, int parent) {
        int child = parent * 2 + 1;
        while(child < n) {
            if(child + 1 < n && arr[child] < arr[child + 1]) {
                child++;
            }

            if(arr[child] > arr[parent]) {
                swap(arr, child, parent);
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    public void quickSort(int[] arr, int left, int right) {
        if(left >= right) {
            return;
        }

        int mid = quick(arr, left, right);
        quickSort(arr, left, mid);
        quickSort(arr, mid + 1, right);
    }

    private int quick(int[] arr, int left, int right) {
        int hole = arr[left];
        while(left < right) {
            while(left < right && arr[right] >= hole) {
                right--;
            }
            if(left < right) {
                arr[left] = arr[right];
            }

            while(left < right && arr[left] <= hole) {
                left++;
            }
            if(left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = hole;
        return left;
    }

    public void quickSortNonRecursive(int[] arr, int left, int right) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(left);
        st.push(right);
        while(!st.isEmpty()) {
            int r = st.pop();
            int l = st.pop();

            int mid = quick(arr, l, r);
            if(l < mid - 1) {
                st.push(l);
                st.push(mid - 1);
            }
            if(r > mid) {
                st.push(mid + 1);
                st.push(r);
            }
        }
    }

    public void mergeSort(int[] arr, int left, int right) {
        int[] tmp = new int[arr.length];
        merge_sort(arr, left, right, tmp);
    }

    private void merge_sort(int[] arr, int left, int right, int[] tmp) {
        if(left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        merge_sort(arr, left, mid, tmp);
        merge_sort(arr, mid + 1, right, tmp);
        merge(arr, left, mid, right, tmp);
    }

    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int l1 = left, l2 = mid + 1, r1 = mid, r2 = right;
        int k = left;
        while(l1 <= r1 && l2 <= r2) {
            if(arr[l1] <= arr[l2]) {
                tmp[k++] = arr[l1++];
            } else {
                tmp[k++] = arr[l2++];
            }
        }
        while(l1 <= r1) {
            tmp[k++] = arr[l1++];
        }
        while(l2 <= r2) {
            tmp[k++] = arr[l2++];
        }

        for(int i = left; i <= right; ++i) {
            arr[i] = tmp[i];
        }
    }
}
