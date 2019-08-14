import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        creatHuffmanTree(arr);
    }

    //创建赫夫曼树
    public static void creatHuffmanTree(int[] arr) {
        //构建一个node 的list
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            System.out.println(nodes);
            //取出全职最小的节点
            //1 取出权值最小的节点
            Node leftNode = nodes.get(0);
            //2，取出权值第二小的节点
            Node rightNode = nodes.get(1);
            //3，构建一棵心的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4,从arraylist中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        System.out.println(nodes);
        preOrder(nodes.get(0));
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树不能遍历");
        }
    }
}

//为了让node这个对象支持排序，
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
