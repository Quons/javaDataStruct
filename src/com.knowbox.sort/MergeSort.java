
public class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2, 3, 10, 43, -1};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        int[] tmp = new int[arr.length];
        long start = System.currentTimeMillis();

        mergeSort(arr, 0, arr.length - 1, tmp);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        // System.out.println(Arrays.toString(arr));
    }

    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, tmp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, tmp);
            //每分解一次就合并一次
            merge(arr, left, mid, right, tmp);
        }
    }

    //合并的方法

    /**
     * @param arr   原始数组
     * @param left  左边有序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param tmp   做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;//初始化i，左边有序序列的初始化索引
        int j = mid + 1;//初始化j，右边有序序列的初始化索引
        int t = 0;//指向tmp数组的当前索引
        //先将左右两边的有序序列拷贝到tmp数组中
        while (i <= mid && j <= right) {//循环直到两个中有一个越界
            //判断大小
            if (arr[i] < arr[j]) {
                //将arr[i]拷贝到临时数组里
                tmp[t] = arr[i];
                i++;
            } else {
                tmp[t] = arr[j];
                j++;
            }
            //移动临时数组索引
            t++;
        }
        while (i <= mid) {
            tmp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            tmp[t] = arr[j];
            j++;
            t++;
        }
        //再将tmp数组拷贝回原始数组 ，因为左右两个边界都包括，所以长度要加一
        for (int s = 0; s < right - left + 1; s++) {
            arr[left + s] = tmp[s];
        }
    }
}
