import java.util.Arrays;

public class FloydAlgorithm {
    //弗洛伊德算法,求最短路径问题
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建临街举证
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        //创建Graph 对象
        FGraph fGraph = new FGraph(vertex.length, matrix, vertex);
        fGraph.floyd();
        fGraph.show();
    }
}

class FGraph {
    private char[] vertex;//存放顶点的数组
    private int[][] dis;//保存，从各个顶点出发到其他顶点的距离，最后的结果，也是包留在该数组
    private int[][] pre;//保存到达目标顶点的前驱顶点

    /**
     * 构造器
     *
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public FGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组进行初始化
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //小时pre数组的和dis数组
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++) {
            //先将pre数组输出
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            //先将dis数组输出
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "->" + vertex[i] + ": " + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    //弗洛伊德算法
    public void floyd() {
        int len = 0;//变量保存距离
        //从中间顶点的遍历，k就是中间顶点的下标
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发['A','B','C','D','E','F','G']
            for (int i = 0; i < dis.length; i++) {
                //到达j顶点 ['A','B','C','D','E','F','G']
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];//求出从i顶点出发，经过k这个中间顶点，到大j顶点的距离
                    if (len < dis[i][j]) {//如果len小于dis[i][j]
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];//更新前驱顶点
                    }
                }
            }
        }
    }
}
