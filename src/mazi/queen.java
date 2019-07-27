package com.knowbox.mazi;

public class queen {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟一个迷宫
        //地图
        int[][] map = new int[8][14];
        //使用1表示墙
        //上下全部改为1
        for (int i = 0; i < 14; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][13] = 1;
        }

        //墙
        map[3][1] = 1;
        map[3][2] = 1;

        map[1][4] = 1;
        map[2][4] = 1;
        map[3][4] = 1;
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 14; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        //输出地推
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 14; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //1,map表示地图
    //2,i,j 表示从地图的哪个地方开始出发(1,1)
    //3,如果小球能到map[6][5]位置，则通路找到
    //4,约定：当map[i][j]为0表示没有走过，当为1时表示墙，2，表示通路走过；3，表示改点已经走过，但是走不通
    //5，再走迷宫是，需要确定一个策略（方法）下->右-> 上-> 左,如果改点走不通，再回溯


    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j   找到哪个位置
     * @return 如果找到通路，就返回true，否则就返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[1][12] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没走过
                //按照策略 下->右-> 上-> 左 走
                map[i][j] = 2;//假定改点是可以走通。
                if (setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果不等于零，可能是1，2，3
                return false;
            }
        }
    }
}

