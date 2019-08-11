import java.util.Stack;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        threadedHeroNode tom = new threadedHeroNode(1, "tom");
        threadedHeroNode node3 = new threadedHeroNode(3, "jack");
        threadedHeroNode node6 = new threadedHeroNode(6, "smith");
        threadedHeroNode node8 = new threadedHeroNode(8, "mary");
        threadedHeroNode node10 = new threadedHeroNode(10, "king");
        threadedHeroNode node14 = new threadedHeroNode(14, "dim");
        //二叉树创建
        tom.setLeft(node3);
        tom.setRight(node6);
        node3.setLeft(node8);
        node3.setRight(node10);
        node6.setLeft(node14);
        threadedBinaryTree threadedBinaryTree = new threadedBinaryTree();
//        threadedBinaryTree.setRoot(tom);
//        threadedBinaryTree.threadedNodes();
        //测试：以10号节点测试
        threadedBinaryTree.preThreadedNodes(tom);
        System.out.println("前序索引索化");
        System.out.println(node8.getRightType());
        System.out.println(node8.getRight());
        System.out.println(node10.getLeft());
        System.out.println(node10.getRight());
//
//        System.out.println(node5.getLeft());
//        System.out.println(node5.getRight());
//        System.out.println("线索化");
//        threadedBinaryTree.threadedList();
    }
}

//定义二叉树
class threadedBinaryTree {
    private threadedHeroNode root;
    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化的时候，总是保存前一个节点
    private threadedHeroNode pre = null;

    public void setRoot(threadedHeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        threadedNodes(this.root);
    }

    //遍历线索化二叉树的方法，（中序）
    public void threadedList() {
        //定义一个变量，存储当前遍历的这个节点，从root开始
        threadedHeroNode node = root;
        while (node != null) {
            //循环地找到leftType==1的节点，第一个找到就是8的节点
            //后面会随着遍历而变化，因为当leftType=1时，说明该节点是按照线索化处理后得有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出,因为后继节点就是要输出的值
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的节点
            node = node.getRight();
        }
    }


    //编写对二叉树进行线索化的方法（中序）
    //node的就是要进行线索化的节点
    public void threadedNodes(threadedHeroNode node) {
        //如果node==null，就不能线索化
        if (node == null) {
            return;
        }

        //1,先线索化左子树
        threadedNodes(node.getLeft());
        //2,再线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //要修改当前节点的左指针的类型
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //3,线索化右子树
        threadedNodes(node.getRight());
    }

    //编写对二叉树进行线索化的方法（前序）
    //node的就是要进行线索化的节点
    public void preThreadedNodes(threadedHeroNode node) {
        if (node == null) {
            return;
        }
        //使用非递归的方式遍历二叉树会好做些
        Stack<threadedHeroNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            //前序遍历，先出栈
            threadedHeroNode n = stack.pop();
            System.out.println(n);
            //先判断右边的是否为空，不为空，入栈
            if (n.getRight() != null) {
                stack.push(n.getRight());
            }
            //处理右索引
            if (pre != null && pre.getRight() == null) {
                pre.setRight(n);
                pre.setRightType(1);
            }
            if (n.getLeft() != null) {
                stack.push(n.getLeft());
            } else {
                //处理左索引
                n.setLeft(pre);
                n.setLeftType(1);
            }
            pre = n;
        }


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
    public threadedHeroNode preOrderFind(int ID) {
        if (this.root != null) {
            return this.root.preOrderFind(ID);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    //中序遍历查找
    public threadedHeroNode infixOrderFind(int ID) {
        if (this.root != null) {
            return this.root.infixOrderFind(ID);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    //后序遍历查找
    public threadedHeroNode postOrderFind(int ID) {
        if (this.root != null) {
            return this.root.postOrderFind(ID);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    public threadedHeroNode preOrderDel(int ID) {
        if (this.root != null) {
            if (this.root.getNo() == ID) {
                threadedHeroNode tmp = root;
                this.root = null;
                return tmp;
            } else {
                threadedHeroNode node = this.root.preOrderDel(ID);
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
class threadedHeroNode {
    private int no;
    private String name;
    private threadedHeroNode left;
    private threadedHeroNode right;
    //说明，
    //1，如果leftType==0 说明指向的是左子树，如果是1则表示指向前驱节点
    //2,如果rightType==0 表示指向的右子树，否则1指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public threadedHeroNode(int no, String name) {
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

    public threadedHeroNode getLeft() {
        return left;
    }

    public void setLeft(threadedHeroNode left) {
        this.left = left;
    }

    public threadedHeroNode getRight() {
        return right;
    }

    public void setRight(threadedHeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "threadedHeroNode{" +
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
    public threadedHeroNode preOrderFind(int id) {
        if (this.getNo() == id) {
            return this;
        }
        //递归向左子树前序遍历
        threadedHeroNode resNode = null;
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
    public threadedHeroNode infixOrderFind(int id) {
        //递归向左子树前序遍历
        threadedHeroNode resNode = null;
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
    public threadedHeroNode postOrderFind(int id) {
        //递归向左子树前序遍历
        threadedHeroNode resNode = null;
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
    public threadedHeroNode preOrderDel(int id) {
        if (this.getNo() == id) {
            return this;
        }
        //递归向左子树前序遍历
        threadedHeroNode resNode = null;
        if (this.left != null) {
            if (this.left.getNo() == id) {
                threadedHeroNode tmp = this.left;
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
                threadedHeroNode tmp = this.right;
                this.right = null;
                return tmp;
            }
            resNode = this.right.preOrderDel(id);
        }
        return resNode;
    }
}