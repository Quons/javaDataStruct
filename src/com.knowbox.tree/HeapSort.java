//import java.util.Arrays;
//
//public class HeapSort {
//    public static void main(String[] args) {
//        int arr[] = {4, 6, 8, 5, 9};
//        heapSort(arr);
//    }
//
//    //编写一个堆排序的方法
//    public static void heapSort(int[] arr) {
//        int temp = 0;
//        System.out.println("堆排序");
////        adjustHeap(arr, 1, arr.length);
////        System.out.println("第一次" + Arrays.toString(arr));
////        adjustHeap(arr, 0, arr.length);
////        System.out.println("第二次" + Arrays.toString(arr));
//        //最终代码
//        //从右至左，从下至上的顺序，从最后一个非叶子节点开始整理
//        for (int i = arr.length / 2 - 1; i >= 0; i--) {
//            adjustHeap(arr, i, arr.length);
//        }
//        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
//        //重新调整结构，使其满足堆定义，然后继续交换栈顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
//        for (int j = arr.length - 1; j > 0; j--) {
//            //交换
//            temp = arr[j];
//            arr[j] = arr[0];
//            arr[0] = temp;
//            adjustHeap(arr, 0, j);
//        }
//        System.out.println(Arrays.toString(arr));
//    }
//
//    //将一个数组（二叉树） 调整成一个大顶堆
//
//    /**
//     * 功能 ：完成将以i对应的非叶子节点调整大顶堆
//     *
//     * @param arr 待调整的数组
//     * @param i   表示非叶子节点在数组中的索引
//     * @param len 表示对多少个元素进行调整
//     */
//    public static void adjustHeap(int[] arr, int i, int len) {
//        int temp = arr[i];//先取出当前元素的值，保存在临时变量
//        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
//            if (k + 1 < len && arr[k] < arr[k + 1]) {
//                //说明左子节点小于右子节点的值
//                k++;//k指向右子节点
//
//            }
//            if (arr[k] > temp) {
//                //交换
//                arr[i] = arr[k];//把较大的值赋值给当前节点
//                i = k;
//            } else {
//                break;
//            }
//        }
//        //当for循环结束时，我们已经将以i为父节点的树的最大值，放在了最顶上
//        arr[i] = temp;//将temp值放到调整后得位置
//    }
//}
//

import java.util.Arrays;

public class HeapSort {
    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    /**
     * 堆排序的主要入口方法，共两步。
     */
    public void sort() {
        /*
         *  第一步：将数组堆化
         *  beginIndex = 第一个非叶子节点。
         *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
         *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
         */
        int len = arr.length - 1;
        int beginIndex = (arr.length / 2) - 1;
        for (int i = beginIndex; i >= 0; i--)
            maxHeapify(i, len);
        /*
         * 第二步：对堆化数据排序
         * 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。
         * 然后从新整理被换到根节点的末尾元素，使其符合堆的特性。
         * 直至未排序的堆长度为 0。
         */
        for (int i = len; i > 0; i--) {
            swap(0, i);
            maxHeapify(0, i - 1);
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 调整索引为 index 处的数据，使其符合堆的特性。
     *
     * @param index 需要堆化处理的数据的索引
     * @param len   未排序的堆（数组）的长度
     */
    private void maxHeapify(int index, int len) {
        int li = (index * 2) + 1; // 左子节点索引
        int ri = li + 1;           // 右子节点索引
        int cMax = li;             // 子节点值最大索引，默认左子节点。
        if (li > len) {  // 左子节点索引超出计算范围，直接返回。
            return;
        }
        if (ri <= len && arr[ri] > arr[li]) { // 先判断左右子节点，哪个较大。
            cMax = ri;
        }
        if (arr[cMax] > arr[index]) {
            swap(cMax, index);      // 如果父节点被子节点调换，
            maxHeapify(cMax, len);  // 则需要继续判断换下后的父节点是否符合堆的特性。
        }
    }

    /**
     * 测试用例
     * <p>
     * 输出：
     * [0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9]
     */
    public static void main(String[] args) {
        int[] arr = new int[]{3, 423, 22, 55, 2, 3, 1};
        new HeapSort(arr).sort();
        System.out.println(Arrays.toString(arr));
    }
}