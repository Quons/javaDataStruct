public class RadixSort {
    public static void main(String[] args) {
        // int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        //第一轮（针对每个元素的个位进行排序处理）
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每一个一维数组，大小定为arr.length
        //3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶汇总，实际放了多少个数据，我们定义一个一维数组来记录每个桶的每次放入的数据个数
        //可以样理解：比如：bucketElementCounts[0] ,记录的就是bucket[0]桶的放入数据的个数
        int[] bucketElementCounts = new int[10];
        //查找最大数
        int max = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] > max) {
                max = arr[j];
            }
        }
        int len = (max + "").length();
        int base = 1;
        for (int l = 0; l < len; l++) {
            if (l > 0) {
                base *= 10;
            }
            for (int j = 0; j < arr.length; j++) {
                int single = arr[j] / base % 10;
                //查询放入的位置
                int index = bucketElementCounts[single];
                bucket[single][index] = arr[j];
                //桶的索引加一
                bucketElementCounts[single]++;
            }
            int count = 0;
            //从桶中取出元素放回到原来的数组中
            for (int j = 0; j < bucket.length; j++) {
                //取出桶中的元素
                for (int i = 0; i < bucketElementCounts[j]; i++) {
                    arr[count] = bucket[j][i];
                    count++;
                }
                //清空桶
                bucketElementCounts[j] = 0;
            }
        }
    }
}
