public class ViolenceMatch {
    //暴力匹配
    public static void main(String[] args) {
        String demo = "今天的天气天气真的好啊";
        String subStr = "天气真";
        int i = violenceMatch(demo, subStr);
        System.out.println(i);
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;
        int i = 0;//i指向s1
        int j = 0;//j索引指向s2
        while (i < s1Len && j < s2Len) {//保证匹配时不越界
            if (s1[i] == s2[j]) {
                //匹配成功
                i++;
                j++;
            } else {
                //没有匹配成功，令 i=i-(j-1),j=0
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
// kmp算法 https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
