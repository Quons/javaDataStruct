public class KnapsackProblem {
    //动态规划，背包问题
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品的价值，这里的val[i]
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组，表
        //v[i][j] 表示在前i个物品中能够装入容量为j
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况，我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列，在本程序中，可以不处理，因为默认都是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置成0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置成0
        }
        //根据前面
        for (int i = 1; i < v.length; i++) {//不处理第一行，是从1开始的
            for (int j = 1; j < v[0].length; j++) {//不处理第一列，j是从1开始的
                //公式
                if (w[i - 1] > j) {//因为我们程序i是从1开始的，因此原来的公式中的w[i]修改成w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    //说明：
                    //因为我们的i，是从1开始的，因此公式需要调整成下面的
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放到背包的情况，我们不能简单地使用公式
                    int a = v[i - 1][j];
                    int b = val[i - 1] + v[i - 1][j - w[i - 1]];
                    if (a > b) {
                        v[i][j] = a;
                    } else {
                        v[i][j] = b;
                        path[i][j] = 1;
                    }
                }
            }
        }

        //输出一下v，看看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //遍历path,其实我们只需要最后的放入
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1) {
//                    System.out.printf("第%d个商品放入到背包\n", i);
//                }
//            }
//        }
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
