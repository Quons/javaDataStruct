import java.util.Arrays;

public class KmpAlgorithm {
    //kmp算法
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
//        String str2 = "BBC";
        //ABCDABD
        //ABCDABD
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int i = kmpSearch(str1, str2, next);
        System.out.println(i);
    }

    /**
     * @param str1 原字符串
     * @param str2 子串
     * @param next 子串的部分匹配表
     * @return 匹配到的索引值，没有则返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理str1.charAt(i) != str2.charAt(j),去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }


            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个子串的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串的长度是1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j),当我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立时才推出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //当这个条件满足时，部分匹配值就是要加一
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
// kmp算法 https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
