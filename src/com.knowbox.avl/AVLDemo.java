public class AVLDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 6, 7, 8, 9};
        //创建一个avl对象
        AVLSortTree avlSortTree = new AVLSortTree();
        for (int i = 0; i < arr.length; i++) {
            avlSortTree.add(new AVLNode(arr[i]));
        }
        System.out.println("中序遍历");
        avlSortTree.infixOrder();
        System.out.println("没有旋转之前的高度");
        System.out.println(avlSortTree.getRoot().height());
        System.out.println("左子树高度");
        System.out.println(avlSortTree.getRoot().leftHeight());
        System.out.println("右子树高度");
        System.out.println(avlSortTree.getRoot().rightHeight());
        System.out.println("当前节点的根节点=" + avlSortTree.getRoot());
        System.out.println("根节点的左节点=" + avlSortTree.getRoot().left);
    }
}

//创建二叉排序树
class AVLSortTree {
    private AVLNode root;

    public AVLNode getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(AVLNode node) {
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
    public AVLNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public AVLNode searchParent(int value) {
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
    public int delRigthTreeMin(AVLNode node) {
        AVLNode target = node;
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
    public int delLeftTreeMin(AVLNode node) {
        AVLNode target = node;
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
            AVLNode targetNode = search(value);
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
            AVLNode parent = searchParent(value);
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
class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }


    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    //返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转的方法
    private void leftRotate() {
        //创建新的节点，以当前根节点的值创建的
        AVLNode newNode = new AVLNode(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把心的节点的右子树设置成当前节点的左子树
        if (right != null) {
            newNode.right = right.left;
        }
        //把当前节点的值设置成右子树的值
        if (right != null) {
            value = right.value;
        }
        //把当前节点的右子节点设置成右子节点的右子节点
        if (right != null) {
            right = right.right;
        }
        //把当前节点的左子节点设置成新的节点
        left = newNode;
    }

    //右旋转的方法
    private void rightRotate() {
        //创建新的节点，以当前根节点的值创建的
        AVLNode newNode = new AVLNode(value);
        //把新的节点的右子树设置成当前节点的右子树
        newNode.right = right;
        //把新的节点的左子树设置成当前节点左子节点的右子树
        if (left != null) {
            newNode.left = left.right;
        }
        //把当前节点的值设置成左子树的值
        if (left != null) {
            value = left.value;
        }
        //把当前节点的左子节点设置成左子节点的左子节点
        if (left != null) {
            left = left.left;
        }
        //把当前节点的右子节点设置成新的节点
        right = newNode;
    }

    /**
     * 查找要删除的节点
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public AVLNode search(int value) {
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
    public AVLNode searchParent(int value) {
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
    public void add(AVLNode node) {
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

        //当添加完一个节点后，如果右子节点高度-左子节点的高度>1,左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftHeight();
            } else {
                leftRotate();//左旋转
            }
            return;
        }

        //当添加完一个节点后，如果左子节点高度-右子节点的高度>1,右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于他的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左节点进行左旋转
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();//右旋转
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
        return "AVLNode{" +
                "value=" + value +
                '}';
    }
}
