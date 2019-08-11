import java.util.Stack;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一颗二叉树
        binaryTree binaryTree = new binaryTree();
        //创建需要的节点
        heroNode node1 = new heroNode(1, "宋江");
        heroNode node2 = new heroNode(2, "吴勇");
        heroNode node3 = new heroNode(3, "卢俊义");
        heroNode node4 = new heroNode(4, "林冲");
        heroNode node5 = new heroNode(5, "关胜");
        //先手动创建二叉树，后面学习递归的方式创建二叉树
        binaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        System.out.println("前序");
        binaryTree.preOrder();
        System.out.println("非递归先序遍历");
        preList(node1);
        System.out.println("中序");
        binaryTree.infixOrder();
        System.out.println("后序");
        binaryTree.postOrder();
        heroNode heroNode = binaryTree.preOrderFind(4);
        if (heroNode != null) {
            System.out.println("找到了：");
            System.out.println(heroNode);
        }
        heroNode heroNode2 = binaryTree.infixOrderFind(4);
        if (heroNode2 != null) {
            System.out.println("找到了：");
            System.out.println(heroNode);
        }
        heroNode heroNode3 = binaryTree.postOrderFind(4);
        if (heroNode3 != null) {
            System.out.println("找到了：");
            System.out.println(heroNode);
        }
        System.out.println("删除节点");
        binaryTree.preOrderDel(3);
        binaryTree.preOrder();

    }

    //非递归前序遍历
    public static void preList(heroNode root) {
        //如果为空，返回
        if (root == null) {
            System.out.println("空树");
            return;
        }
        //创建栈
        Stack<heroNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            //出栈
            heroNode node = stack.pop();
            System.out.println(node);
            //先把右边入栈，再把左边入栈，是为了出栈是先访问左边的节点
            if (node.getRight() != null) {
                stack.add(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
        }
    }
}

//定义二叉树
class binaryTree {
    private heroNode root;

    public void setRoot(heroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public heroNode preOrderFind(int ID) {
        if (this.root != null) {
            return this.root.preOrderFind(ID);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    //中序遍历查找
    public heroNode infixOrderFind(int ID) {
        if (this.root != null) {
            return this.root.infixOrderFind(ID);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    //后序遍历查找
    public heroNode postOrderFind(int ID) {
        if (this.root != null) {
            return this.root.postOrderFind(ID);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    public heroNode preOrderDel(int ID) {
        if (this.root != null) {
            if (this.root.getNo() == ID) {
                heroNode tmp = root;
                this.root = null;
                return tmp;
            } else {
                heroNode node = this.root.preOrderDel(ID);
                if (node == null) {
                    System.out.println("该节点不存在");
                } else {
                    System.out.println("删除成功" + node);
                    return node;
                }
            }
        } else {
            System.out.println("二叉树为空");
        }
        return null;
    }
}

//先创建HeroNode节点
class heroNode {
    private int no;
    private String name;
    private heroNode left;
    private heroNode right;

    public heroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public heroNode getLeft() {
        return left;
    }

    public void setLeft(heroNode left) {
        this.left = left;
    }

    public heroNode getRight() {
        return right;
    }

    public void setRight(heroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "heroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    //编写中序遍历的方法
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//先输出父节点
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//先输出父节点
    }

    //编写前序遍历的方法
    public heroNode preOrderFind(int id) {
        if (this.getNo() == id) {
            return this;
        }
        //递归向左子树前序遍历
        heroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderFind(id);
        }
        if (resNode != null) {
            return resNode;
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            return this.right.preOrderFind(id);
        }
        return null;
    }

    //编写中序遍历的方法
    public heroNode infixOrderFind(int id) {
        //递归向左子树前序遍历
        heroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderFind(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.getNo() == id) {
            return this;
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            return this.right.infixOrderFind(id);
        }
        return null;
    }

    //编写后序遍历的方法
    public heroNode postOrderFind(int id) {
        //递归向左子树前序遍历
        heroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderFind(id);
        }
        if (resNode != null) {
            return resNode;
        }

        //递归向右子树前序遍历
        if (this.right != null) {
            resNode = this.right.postOrderFind(id);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.getNo() == id) {
            return this;
        }
        return null;
    }

    //编写前序遍历删除
    public heroNode preOrderDel(int id) {
        if (this.getNo() == id) {
            return this;
        }
        //递归向左子树前序遍历
        heroNode resNode = null;
        if (this.left != null) {
            if (this.left.getNo() == id) {
                heroNode tmp = this.left;
                this.left = null;
                return tmp;
            }
            resNode = this.left.preOrderDel(id);
        }
        if (resNode != null) {
            return resNode;
        }

        //递归向右子树前序遍历
        if (this.right != null) {
            if (this.right.getNo() == id) {
                heroNode tmp = this.right;
                this.right = null;
                return tmp;
            }
            resNode = this.right.preOrderDel(id);
        }
        return resNode;
    }
}