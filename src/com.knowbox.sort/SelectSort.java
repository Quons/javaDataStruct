public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        int[] arr2 = selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
       /* for (int a : arr2) {
            System.out.println(a);
        }*/
    }

    //每次从剩余元素中选择出来最大的放大最左边
    public static int[] selectSort(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            //正序遍历
            int a = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] > arr[a]) {
                    a = j;
                }
            }
            //交换
            tmp = arr[a];
            arr[a] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }
}


