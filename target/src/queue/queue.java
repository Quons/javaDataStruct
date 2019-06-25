package com.knowbox.queue;

public class queue {
    public static void main(String[] args) {
        //创建一个原始的二维数组11* 11
        //0：表示没有棋子，1：表示黑子，2：表示蓝子
        int[][] chessArra1 = new int[11][11];
        chessArra1[1][2] = 1;
        chessArra1[2][4] = 2;
        for (int[] row : chessArra1) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
        //将二维数组转换成稀疏数组
        //1，先遍历而为数组得到非0数据的个数
        int sum = 0;
        for (int[] row : chessArra1) {
            for (int v : row) {
                if (v != 0) {
                    sum++;
                }
            }
        }
        //2,创建对应的稀疏数组
        int[][] sparseArray2 = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray2[0][0] = 11;
        sparseArray2[0][1] = 11;
        sparseArray2[0][2] = sum;
        //遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0;
        for (int i = 0; i < chessArra1.length; i++) {
            for (int j = 0; j < chessArra1[i].length; j++) {
                if (chessArra1[i][j] != 0) {
                    count++;
                    sparseArray2[count][0] = i;
                    sparseArray2[count][1] = j;
                    sparseArray2[count][2] = chessArra1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println("得到稀疏数组为～～～～");
        for (int[] ints : sparseArray2) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        //将稀疏数组恢复成二维数组
        //先创建对应的数组
        int[][] originArray = new int[sparseArray2[0][0]][sparseArray2[0][1]];
        for (int i = 1; i < sparseArray2.length; i++) {
            originArray[sparseArray2[i][0]][sparseArray2[i][1]] = sparseArray2[i][2];
        }
        System.out.println("原始稀疏数组为～～～");
        for (int[] row : originArray) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }
}
//p10