public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int i = seqSearch(arr, -11);
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
    public static int seqSearch(int[] arr, int value) {
        //线性查找就是逐一比对，发现有相同值，就返回下表
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}


