package com.gyx.tree;

/**
 * 线索二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //创建二叉树和结点
        ThreadedBinaryTreeNode root = new ThreadedBinaryTreeNode(1, "Trump");
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        ThreadedBinaryTreeNode node2 = new ThreadedBinaryTreeNode(2, "Biden");
        ThreadedBinaryTreeNode node3 = new ThreadedBinaryTreeNode(3, "Obama");
        ThreadedBinaryTreeNode node4 = new ThreadedBinaryTreeNode(4, "Rosalia");
        ThreadedBinaryTreeNode node5 = new ThreadedBinaryTreeNode(5, "Dardalla");
        ThreadedBinaryTreeNode node6 = new ThreadedBinaryTreeNode(6, "Venti");
        ThreadedBinaryTreeNode node7 = new ThreadedBinaryTreeNode(7, "Jean");

        //手动设置结点间的关系
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        node2.setParent(root);
        node3.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);
        node7.setParent(node3);

        threadedBinaryTree.postThreaded();
        threadedBinaryTree.listPostThreded();
    }
}

/**
 * 二叉树结点，包含两个变量，一个是int，一个是string
 */
class ThreadedBinaryTreeNode {
    private int val;
    private String str;
    private ThreadedBinaryTreeNode left;
    private ThreadedBinaryTreeNode right;
    private ThreadedBinaryTreeNode parent;//父亲结点
    //leftType=0表示指向左子树，等于1表示指向前驱
    //rightType=0表示指向右子树，等于1表示指向后继
    private int leftType;
    private int rightType;

    //构造方法
    public ThreadedBinaryTreeNode(int val, String str) {
        this.val = val;
        this.str = str;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public ThreadedBinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedBinaryTreeNode left) {
        this.left = left;
    }

    public ThreadedBinaryTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadedBinaryTreeNode right) {
        this.right = right;
    }

    public ThreadedBinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(ThreadedBinaryTreeNode parent) {
        this.parent = parent;
    }

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

    public String toString() {
        return "[" + val + " " + str + "] ";
    }
}

/**
 * 二叉树
 */
class ThreadedBinaryTree {
    private ThreadedBinaryTreeNode root;//根结点
    private ThreadedBinaryTreeNode pre;//前驱结点

    //构造方法
    public ThreadedBinaryTree(ThreadedBinaryTreeNode node) {
        this.root = node;
    }

    //遍历前序线索化二叉树
    public void listPreThreded() {
        ThreadedBinaryTreeNode temp = root;
        while (true) {
            System.out.print(temp.toString());
            while (temp.getRightType() == 1) {//如果右边是后继
                temp = temp.getRight();
                System.out.print(temp.toString());
            }
            if (temp.getRight() == null) {
                break;
            }
            temp = temp.getLeft();//不能通过rightType判断了，要手动切换到下一个结点
        }
    }

    //前序线索化二叉树
    public void preThreaded(ThreadedBinaryTreeNode node) {
        if (node.getLeft() == null) {//如果左边为空
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {//如果右边为空
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if (node.getLeftType() == 0 && node.getLeft() != null) {
            preThreaded(node.getLeft());
        }
        if (node.getRight() != null) {
            preThreaded(node.getRight());
        }
    }

    //重载前序线索化二叉树
    public void preThreaded() {
        if (root == null) {
            System.out.println("二叉树是空的，无法线索化");
            return;
        }
        preThreaded(root);
    }

    //遍历中序线索化二叉树
    public void listInThreded() {
        ThreadedBinaryTreeNode temp = root;
        while (temp != null) {
            while (temp.getLeftType() == 0) {//找到第一个左边是前驱的结点
                temp = temp.getLeft();
            }
            System.out.print(temp.toString());
            while (temp.getRightType() == 1) {//只要右边是后继，就继续
                temp = temp.getRight();
                System.out.print(temp.toString());
            }
            temp = temp.getRight();//不能通过rightType判断了，要手动切换到下一个结点
        }
    }

    //中序线索化二叉树
    public void inThreaded(ThreadedBinaryTreeNode node) {
        if (node.getLeft() != null) {
            inThreaded(node.getLeft());
        }
        if (node.getLeft() == null) {//如果左边为空
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {//如果右边为空
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if (node.getRight() != null) {
            inThreaded(node.getRight());
        }
    }

    //重载中序线索化二叉树
    public void inThreaded() {
        if (root == null) {
            System.out.println("二叉树是空的，无法线索化");
            return;
        }
        inThreaded(root);
    }

    //遍历后序线索化二叉树
    public void listPostThreded() {
        ThreadedBinaryTreeNode temp = root;
        while (temp.getLeftType() == 0) {//只要左边是左子树，就继续
            temp = temp.getLeft();
        }
        while (temp != null) {
            System.out.print(temp.toString());
            while (temp.getRightType() == 1) {//只要右边是后继，就继续
                temp = temp.getRight();
                System.out.print(temp.toString());
            }
            //如果到了根结点，说明遍历结束了，后继为空
            if (temp == root) {
                temp = null;
            }
            //如果当前结点为左子树且父结点无右子树，或者当前结点为右子树，后继为父结点
            else if ((temp == temp.getParent().getLeft() && temp.getParent().getRight() == null) || temp == temp.getParent().getRight()) {
                temp = temp.getParent();
            }
            //如果当前结点为左子树且父结点有右子树，则后继为父结点右子树上后序遍历的第一个结点
            else if (temp == temp.getParent().getLeft() && temp.getParent().getRight() != null) {
                temp = temp.getParent().getRight();
                while (temp.getLeftType() == 0) {
                    temp = temp.getLeft();
                }
            }
        }
    }

    //后序线索化二叉树
    public void postThreaded(ThreadedBinaryTreeNode node) {
        if (node.getLeft() != null) {
            postThreaded(node.getLeft());
        }
        if (node.getRight() != null) {
            postThreaded(node.getRight());
        }
        if (node.getLeft() == null) {//如果左边为空
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {//如果右边为空
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    //重载后序线索化二叉树
    public void postThreaded() {
        if (root == null) {
            System.out.println("二叉树是空的，无法线索化");
            return;
        }
        postThreaded(root);
    }

    //根据val删除结点
    public void delNode(ThreadedBinaryTreeNode node, int val) {
        if (node.getLeft() != null && node.getLeft().getVal() == val) {
            if (node.getLeft().getLeft() != null && node.getLeft().getRight() == null) {//如果字结点有左无右
                node.setLeft(node.getLeft().getLeft());//把子结点的左边放上来
            } else if (node.getLeft().getRight() != null && node.getLeft().getLeft() == null) {//如果子结有右无左
                node.setLeft(node.getLeft().getRight());//把子结点的右边放上来
            } else if (node.getLeft().getLeft() != null && node.getLeft().getRight() != null) {//如果子结点左右都有
                ThreadedBinaryTreeNode temp = node.getLeft().getRight();//临时保存子结点的右边
                node.setLeft(node.getLeft().getLeft());//把子结点的左边放上来
                node.getLeft().setRight(temp);//把右边接上
            } else {//如果子结点左右都无
                node.setLeft(null);
            }
        }
        if (node.getRight() != null && node.getRight().getVal() == val) {
            if (node.getRight().getLeft() != null && node.getRight().getRight() == null) {//如果字结点有左无右
                node.setRight(node.getRight().getLeft());//把子结点的左边放上来
            } else if (node.getRight().getRight() != null && node.getRight().getLeft() == null) {//如果子结有右无左
                node.setRight(node.getRight().getRight());//把子结点的右边放上来
            } else if (node.getRight().getLeft() != null && node.getRight().getRight() != null) {//如果子结点左右都有
                ThreadedBinaryTreeNode temp = node.getRight().getRight();//临时保存子结点的右边
                node.setRight(node.getRight().getLeft());//把子结点的左边放上来
                node.getRight().setRight(temp);//把右边接上
            } else {//如果子结点左右都无
                node.setRight(null);
            }
        }
        if (node.getLeft() != null) {
            delNode(node.getLeft(), val);//向左递归
        }
        if (node.getRight() != null) {
            delNode(node.getRight(), val);//向左递归
        }
    }

    //重载
    public void delNode(int val) {
        //如果树是空的
        if (root == null) {
            System.out.println("树是空的，没有结点可以删除");
            return;
        }
        //如果只有一个结点
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
        }
        delNode(root, val);
    }

    //前序遍历
    public void preOrder(ThreadedBinaryTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.toString());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    //重载
    public void preOrder() {
        preOrder(root);
    }

    //中序遍历
    public void inOrder(ThreadedBinaryTreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.print(node.toString());
        inOrder(node.getRight());
    }

    //重载
    public void inOrder() {
        inOrder(root);
    }

    //后序遍历
    public void postOrder(ThreadedBinaryTreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.toString());
    }

    //重载
    public void postOrder() {
        postOrder(root);
    }

    //前序查找
    public ThreadedBinaryTreeNode preSelect(ThreadedBinaryTreeNode node, int val) {
        if (node.getVal() == val) {//判断当前结点是否符合条件
            return node;
        }
        ThreadedBinaryTreeNode result = null;//结果
        if (node.getLeft() != null) {//向左递归
            result = preSelect(node.getLeft(), val);//接收递归的返回结果
        }
        if (result != null) {//如果不为空，说明找到了，返回结果
            return result;
        }
        if (node.getRight() != null) {//向右递归
            result = preSelect(node.getRight(), val);//接收递归的返回结果
        }
        if (result != null) {//如果不为空，说明找到了，返回结果
            return result;
        }
        return result;//如果没找到，返回的result还是空的
    }

    //重载
    public ThreadedBinaryTreeNode preSelect(int val) {
        if (root == null) {
            return null;
        }
        return preSelect(root, val);
    }

    //中序查找
    public ThreadedBinaryTreeNode inSelect(ThreadedBinaryTreeNode node, int val) {
        ThreadedBinaryTreeNode result = null;//结果
        if (node.getLeft() != null) {//向左递归
            result = inSelect(node.getLeft(), val);//接收递归的返回结果
        }
        if (result != null) {//如果不为空，说明找到了，返回结果
            return result;
        }
        if (node.getVal() == val) {//判断当前结点是否符合条件
            return node;
        }
        if (node.getRight() != null) {//向右递归
            result = inSelect(node.getRight(), val);//接收递归的返回结果
        }
        if (result != null) {//如果不为空，说明找到了，返回结果
            return result;
        }
        return result;//如果没找到，返回的result还是空的
    }

    //重载
    public ThreadedBinaryTreeNode inSelect(int val) {
        if (root == null) {
            return null;
        }
        return inSelect(root, val);
    }

    //后序查找
    public ThreadedBinaryTreeNode postSelect(ThreadedBinaryTreeNode node, int val) {
        ThreadedBinaryTreeNode result = null;//结果
        if (node.getLeft() != null) {//向左递归
            result = postSelect(node.getLeft(), val);//接收递归的返回结果
        }
        if (result != null) {//如果不为空，说明找到了，返回结果
            return result;
        }
        if (node.getRight() != null) {//向右递归
            result = postSelect(node.getRight(), val);//接收递归的返回结果
        }
        if (result != null) {//如果不为空，说明找到了，返回结果
            return result;
        }
        if (node.getVal() == val) {//判断当前结点是否符合条件
            return node;
        }
        return result;//如果没找到，返回的result还是空的
    }

    //重载
    public ThreadedBinaryTreeNode postSelect(int val) {
        if (root == null) {
            return null;
        }
        return postSelect(root, val);
    }
}