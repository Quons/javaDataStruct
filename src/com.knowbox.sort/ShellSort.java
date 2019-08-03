
public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        shellSort3(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // System.out.println(Arrays.toString(arr));
    }

    //交换法
    public static void shellSort(int[] arr) {
        int tmp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//控制步长
            for (int i = gap; i < arr.length; i++) {//进行插入排序
                for (int j = i - gap; j >= 0; j -= gap) {//将右边无序部分的第一个元素插入到左边有序部分的正确位置
                    if (arr[j] > arr[j + gap]) {//不断比较当前待插入元素与遍历元素的大小，不断移动，直至待插入元素大于遍历的元素，说明位置正确
                        tmp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = tmp;
                    }
                }
            }
        }
    }

    //依次降低步长，进行宏观调整
    public static void shellSort2(int[] arr) {
        int tmp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//控制步长
            for (int i = gap; i < arr.length; i++) {
                //正序遍历
                for (int j = i % gap; j < i; j += gap) {
                    if (arr[i] < arr[j]) {
                        //腾出空位，将i插入
                        tmp = arr[i];
                        for (int f = i - gap; f >= j; f -= gap) {
                            arr[f + gap] = arr[f];
                        }
                        arr[j] = tmp;
                    }
                }
            }
        }
    }

    //移位法
    public static void shellSort3(int[] arr) {
        int tmp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//控制步长
            for (int i = gap; i < arr.length; i++) {//以gap为界，左边为有序组，gap以及gap右边的为无序组
                int j = i;//i为分界线
                tmp = arr[i];//记录待插入数值
                while (j - gap >= 0 && tmp < arr[j - gap]) {//移位，把位置给待插入值腾出位置
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = tmp;//插入
            }
        }
    }
}


