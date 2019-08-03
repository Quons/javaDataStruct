import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 11, 11, 34, 89};
        ShellSort.shellSort2(arr);
        System.out.println(Arrays.toString(arr));
        int i = insertSearch(arr, 34, 0, arr.length - 1);
        if (i == -1) {
            System.out.println("没有查找到");
        } else {
            System.out.println("找到了，下表为：" + i);
        }
    }

    /**
     * 这里我们是找到一个满足条件的就返回
     *
     * @param arr   要查找的数组
     * @param value 要查找的值
     * @return
     */
    public static int insertSearch(int[] arr, int value, int left, int right) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        //计算中间值
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);//重点
        if (value < arr[mid]) {
            return insertSearch(arr, value, left, mid - 1);
        } else if (value > arr[mid]) {
            return insertSearch(arr, value, mid + 1, right);
        } else {
            return mid;
        }
    }
}


