import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessBoard {
    private static int X;//棋盘的列
    private static int Y;//棋盘的行数
    //创建一个数组，标记棋盘的各个数组是否被访问过
    private static boolean visited[];
    //使用一个属性，标记是否棋盘的所有所有位置都被访问过
    private static boolean finished;//如果为true，表示成功

    //马踏棋盘，回溯算法
    public static void main(String[] args) {
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 5;//马儿初始位置的行，从1开始编号
        int column = 5;//马儿的初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是false
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();

        System.out.println("共耗时：" + (end - start));
        //输出棋盘的最后情况
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessBoard 棋盘
     * @param row        马当前的位置的行 从0 开始
     * @param column     马当前的位置列，从0 开始
     * @param step       是第几步，初始位置就是第一步
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step;
        visited[row * X + column] = true;//标记该位置已访问
        //获取当前位置可以都的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断改点是否已经访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        //判断有没有完成任务，使用step 和应该走的步数比较，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置为0
        //说明：step<X*Y 成立的情况有两种
        if (step < X * Y && !finished) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 功能：求当前点的下一步点的集合
     *
     * @param curPoint 当前点
     * @return 下一步可行点的集合
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> points = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        return points;
    }

    //根据当前这一步的所有的下一步的选择位置，进行非递减排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                ArrayList<Point> o1next = next(o1);
                ArrayList<Point> o2next = next(o2);
                return o1next.size() - o2next.size();
            }
        });
    }
}
