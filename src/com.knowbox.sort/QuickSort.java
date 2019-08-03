
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {7, 3, 8, 2, 7, 9, 10, 1, 7, 54};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //System.out.println(Arrays.toString(arr));
       /* for (int a : arr2) {
            System.out.println(a);
        }*/
    }

    //移位法
    public static void quickSort(int[] arr, int left, int right) {
        int tmp;
        int l = left;
        int r = right;
        //pivot 中轴
        int pivot = arr[(left + right) / 2];
        //while循环的目的是让比pivot值小放到左边
        //比pivot值大的放到右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot值，才推出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot的值才推出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果1>=r,说明pivot的左右两边的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //交换
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            // System.out.println("pivot:" + pivot + " l:" + l + " r:" + r + " arr:" + Arrays.toString(arr));
            //如果交换完后，发现这个arr[l]==pivot值相等,r--，前移
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后，发现这个arr[r]==pivot值相等,l++，前移
            if (arr[r] == pivot) {
                l++;
            }
        }
//        System.out.println("pivot:" + pivot + " l:" + l + " r:" + r + " left" + left + " right:" + right + " arr:" + Arrays.toString(arr));
        //如果l==r,必须l++，r--,否则栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}


