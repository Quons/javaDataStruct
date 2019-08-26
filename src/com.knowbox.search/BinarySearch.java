import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 11, 11, 34, 89};
        ShellSort.shellSort2(arr);
        System.out.println(Arrays.toString(arr));
        int i = binarySearch2(arr, 34, 0, arr.length - 1);
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
    public static int binarySearch(int[] arr, int value, int left, int right) {
        //计算中间值
        int mid = (left + right) / 2;
        if (value < arr[mid]) {
            return binarySearch(arr, value, left, mid - 1);
        } else if (value > arr[mid]) {
            return binarySearch(arr, value, mid + 1, right);
        } else if (value == arr[mid]) {
            System.out.println("value:" + value);
            return mid;
        } else if (left == right) {
            return -1;
        }
        return -1;
    }


    public static int binarySearch2(int[] arr, int value, int left, int right) {
        //计算中间值
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (value < arr[mid]) {
                right = mid - 1;
            } else if (value > arr[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
            System.out.println("mid:" + mid + " left:" + left + " right:" + right);
        }
        return -1;
    }
}


