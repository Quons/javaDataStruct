import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 6, 3, 8, 5, 7};
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }*/
        long start = System.currentTimeMillis();
        int[] arr2 = insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(Arrays.toString(arr2));
    }

    //将数组分成左边的有序列和右边的无序列，每次从右边的无序列中取出一个插入到有序列中
    public static int[] insertSort(int[] arr) {
        int tmp;
        for (int i = 1; i < arr.length; i++) {
            //正序遍历
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    //腾出空位，将i插入
                    tmp = arr[i];
                    for (int f = i - 1; f >= j; f--) {
                        arr[f + 1] = arr[f];
                    }
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }
}


