public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(10, 'A', 'B', 'C');
    }

    //汉诺塔的移动方法
    //使用分治算法

    /**
     * @param num 移动的个数
     * @param a   待移动塔
     * @param b   中间塔
     * @param c   目标塔
     */
    public static void hanoitower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果我们有n>=2情况，我们总是可以看做是两个盘 1，最下边的一个盘 2，上边的所有盘
            //1,先把最上边的盘A->B,移动过程会使用到c
            hanoitower(num - 1, a, c, b);
            //2,把最下边的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3,把B塔的所有盘从B->C,移动过程使用a塔
            hanoitower(num - 1, b, a, c);
        }
    }
}
