import sun.security.util.Length;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        int[] arr2 = bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        /*for (int a : arr2) {
            System.out.println(a);
        }*/
    }

    public static int[] bubbleSort(int[] arr) {
        //倒序遍历
        int tmp = 0;
        boolean flag;
        for (int i = arr.length - 1; i >= 0; i--) {
            //正序遍历
            flag = false;
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    flag = true;
                    //如果下一个小于前一个，交换顺序
                    tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
            if (!flag) {
                System.out.println("break");
                break;
            }
        }
        return arr;
    }
}


