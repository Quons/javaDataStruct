import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 6, 3, 8, 5, 7};
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }*/
        long start = System.currentTimeMillis();
        int[] arr2 = insertSort3(arr);
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
                if (arr[i] < arr[j]) {
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

    public static int[] insertSort2(int[] arr) {
        int tmp;
        for (int i = 1; i < arr.length; i++) {//进行插入排序
            for (int j = i - 1; j >= 0; j -= 1) {//从右至左遍历有序部分，给待插入元素找位置
                if (arr[j] > arr[j + 1]) {//不断比较当前待插入元素与遍历元素的大小，不断移动，直至待插入元素大于遍历的元素，说明位置正确
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }

    //将数组分成左边的有序列和右边的无序列，每次从右边的无序列中取出一个插入到有序列中
    public static int[] insertSort3(int[] arr) {
        int tmp;
        for (int i = 1; i < arr.length; i++) {
            int j = i;//arr[i]为待插入元素
            tmp = arr[i];//记录待插入的值
            //后移元素
            while (j - 1 >= 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j -= 1;
            }
            //退出循环说明位置空出来了
            arr[j] = tmp;
        }
        return arr;
    }
}


