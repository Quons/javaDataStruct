import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(arr, 1234);
        System.out.println(i);
    }

    //得到斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契数列

    /**
     * @param arr 数组
     * @param key 我们需要查找的关键码
     * @return 返回对应的下标，如果没有返回-1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契数列分隔数值的下标
        int mid = 0;
        int[] f = fib();//获取到菲波那切数列
        System.out.println(Arrays.toString(f));
        //获取到菲波那切分隔数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k] 值可能大于a的长度，一次我们需要使用arrays类，构造一个新的数组，并指向arr
        int[] tmp = Arrays.copyOf(arr, f[k]);
        //实际上需要使用a数组的最后的数填充tmp
        for (int i = high + 1; i < tmp.length; i++) {
            tmp[i] = arr[high];
        }
        //使用while来循环处理，找到我们的key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < tmp[mid]) {//我们应该继续向数组的左边进行查找
                high = mid - 1;
                k--;//以前一个斐波那契的值查找 因为f[k-1]=f[k-2]+f[k-3],所以左边分隔点的值应该减1
            } else if (key > tmp[mid]) {
                low = mid + 1;
                k -= 2;//从右边开始查找，因为f[k-2]=f[k-3]+f[k-4],所以右边的分隔点的k值应该减2
            } else {
                if (mid <= high) {//后面填充的都是最后的值
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}


