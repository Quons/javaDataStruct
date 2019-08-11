public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个arrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        arrBinaryTree.sufixOrder();
        arrBinaryTree.postOrder();
    }
}

//编写一个ArrayBinaryTree,实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载这个方法
    public void preOrder() {
        this.preOrder(0);
    }

    //重载这个方法
    public void sufixOrder() {
        this.sufixOrder(0);
    }

    //重载这个方法
    public void postOrder() {
        this.postOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        //如果数组为空，或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void sufixOrder(int index) {
        //如果数组为空，或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //向左遍历
        if ((index * 2 + 1) < arr.length) {
            sufixOrder(index * 2 + 1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            sufixOrder(index * 2 + 2);
        }
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void postOrder(int index) {
        //如果数组为空，或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        //向左遍历
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);
        //输出当前这个元素
    }
}