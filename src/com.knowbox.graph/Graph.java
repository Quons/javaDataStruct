import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储定点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义数组boolean[] ,记录某个节点是否被访问
    private boolean[] isVisited;


    public static void main(String[] args) {
        int n = 5;
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加定点
        for (String s : vertexValue) {
            //添加定点
            graph.insertVertex(s);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //显示邻接矩阵
        graph.showGraph();
        //测试深度遍历
        System.out.println("深度遍历");
        // graph.dfs();
        System.out.println("广度优先");
        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和arrayList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个邻接节点的下标

    /**
     * @param index
     * @return 如果存在就返回对应的下标，否则就返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 第一次就是0
    public void dfs(boolean[] isVisited, int i) {
        //首先我们访问该节点，输出
        System.out.print(getValue(i) + "->");
        //将节点设置为已访问过
        isVisited[i] = true;
        //查找节点i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明有邻接节点
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果已经被访问过，就访问邻接节点的下一个节点
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载，遍历我们所有的节点，并进行dfs
    public void dfs() {
        //遍历所有的节点，进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队里的头结点的对应下标
        int w;//邻接节点的下标w
        //队列，记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点，输出节点信息
        System.out.print(getValue(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头节点
            u = queue.removeFirst();
            //得到第一邻接节点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValue(w) + "=>");
                    //标记为已访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }

    //遍历所有的节点，都进行广度优先搜索
    private void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点对应的数据
    public String getValue(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int i = 0; i < edges.length; i++) {
            System.out.println(Arrays.toString(edges[i]));
        }
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1     表示点的下标即使第几个定点"A"-"B" "A"->0 "B"->1
     * @param v2     表示第二个定点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
