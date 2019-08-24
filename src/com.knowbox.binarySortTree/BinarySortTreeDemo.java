public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new BSNode(arr[i]));
        }
        binarySortTree.infixOrder();
        binarySortTree.delNode(10);
        binarySortTree.delNode(5);
        binarySortTree.delNode(3);
        binarySortTree.delNode(7);
        binarySortTree.delNode(2);
        binarySortTree.delNode(12);
        System.out.println("......");
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private BSNode root;

    //添加节点的方法
    public void add(BSNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("为空，无法遍历");
        }
    }

    //查找要删除的节点
    public BSNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public BSNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @param node 传入的节点（当做二叉排序数的根节点）
     * @return 返回的以node为根节点的二叉排序树的最小节点的值，同时删除该节点
     */
    public int delRigthTreeMin(BSNode node) {
        BSNode target = node;
        //循环查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    /**
     * @param node 传入的节点（当做二叉排序数的根节点）
     * @return 返回的以node为根节点的二叉排序树的最小节点的值，同时删除该节点
     */
    public int delLeftTreeMin(BSNode node) {
        BSNode target = node;
        //循环查找左节点，就会找到最小值
        while (target.right != null) {
            target = target.right;
        }
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1，先找到要删除的节点，
            BSNode targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这棵二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去查找targetNode的父节点
            BSNode parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    //说明targetNode是父节点的左子节点
                    parent.left = null;
                } else {
                    //说明是父节点的右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有左右两两棵子树的节点
               /* int v = delRigthTreeMin(targetNode.right);
                targetNode.value = v;*/
                targetNode.value = delLeftTreeMin(targetNode.left);
            } else {//删除只有一个节点的节点
                //如果要删除的节点有左子节点，
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left != null && parent.left.value == value) {//要删除的节点是父节点的左子节点
                            parent.left = targetNode.left;
                        } else {//要删除的节点是父节点的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//要删除的节点有右子节点
                    if (parent != null) {
                        if (parent.left != null && parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

//创建Node节点
class BSNode {
    int value;
    BSNode left;
    BSNode right;

    public BSNode(int value) {
        this.value = value;
    }

    /**
     * 查找要删除的节点
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public BSNode search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            //如果查找的值小于当前的节点，就应该向左子树递归查找
            if (this.left != null) {
                return this.left.search(value);
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            }
        }
        return null;
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要查找的节点的值
     * @return 返回的是要删除的节点的父节点，如果没有返回null
     */
    public BSNode searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找到的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;//没有找到
            }
        }
    }

    //添加节点的方法
    //递归的形式添加节点，需要满足二叉排序数的要求
    public void add(BSNode node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值得关系
        if (node.value < this.value) {
            //如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归地向左子树添加
                this.left.add(node);
            }
        } else {
            //大于根节点
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "BSNode{" +
                "value=" + value +
                '}';
    }
}
