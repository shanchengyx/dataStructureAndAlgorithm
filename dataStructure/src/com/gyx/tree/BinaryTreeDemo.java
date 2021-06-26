package com.gyx.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建二叉树和结点
        TreeNode root = new TreeNode(1, "Trump");
        BinaryTree binaryTree = new BinaryTree(root);
        TreeNode node2 = new TreeNode(2, "Biden");
        TreeNode node3 = new TreeNode(3, "Obama");
        TreeNode node4 = new TreeNode(4, "Rosalia");
        TreeNode node5 = new TreeNode(5, "Dardalla");
        TreeNode node6 = new TreeNode(6, "Venti");
        TreeNode node7 = new TreeNode(7, "Jean");

        //手动设置结点间的关系
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        //删除结点
        binaryTree.delNode(3);
        binaryTree.preOrder();

//        //遍历
//        binaryTree.preOrder();
//        System.out.println();
//        binaryTree.inOrder();
//        System.out.println();
//        binaryTree.postOrder();
//        System.out.println();
//
//        //前序查找
//        TreeNode preResult = binaryTree.preSelect(6);
//        if (preResult != null) {
//            System.out.println(preResult.toString());
//        } else {
//            System.out.println("没有找到该结点");
//        }
//
//        //中序查找
//        TreeNode inResult = binaryTree.inSelect(6);
//        if (inResult != null) {
//            System.out.println(inResult.toString());
//        } else {
//            System.out.println("没有找到该结点");
//        }
//
//        //后序查找
//        TreeNode postResult = binaryTree.postSelect(6);
//        if (postResult != null) {
//            System.out.println(postResult.toString());
//        } else {
//            System.out.println("没有找到该结点");
//        }
    }
}

/**
 * 二叉树结点，包含两个变量，一个是int，一个是string
 */
class TreeNode {
    private int val;
    private String str;
    private TreeNode left;
    private TreeNode right;

    //构造方法
    public TreeNode(int val, String str) {
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

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public String toString() {
        return "[" + val + " " + str + "] ";
    }

    //根据val删除结点
    public void delNode(TreeNode node, int val) {
        if (node.getLeft() != null && node.getLeft().getVal() == val) {
            if (node.getLeft().getLeft() != null && node.getLeft().getRight() == null) {//如果字结点有左无右
                node.setLeft(node.getLeft().getLeft());//把子结点的左边放上来
            } else if (node.getLeft().getRight() != null && node.getLeft().getLeft() == null) {//如果子结有右无左
                node.setLeft(node.getLeft().getRight());//把子结点的右边放上来
            } else if (node.getLeft().getLeft() != null && node.getLeft().getRight() != null) {//如果子结点左右都有
                TreeNode temp = node.getLeft().getRight();//临时保存子结点的右边
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
                TreeNode temp = node.getRight().getRight();//临时保存子结点的右边
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

    //前序遍历
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.toString());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    //中序遍历
    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.print(node.toString());
        inOrder(node.getRight());
    }

    //后序遍历
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.toString());
    }

    //前序查找
    public TreeNode preSelect(TreeNode node, int val) {
        if (node.getVal() == val) {//判断当前结点是否符合条件
            return node;
        }
        TreeNode result = null;//结果
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

    //中序查找
    public TreeNode inSelect(TreeNode node, int val) {
        TreeNode result = null;//结果
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

    //后序查找
    public TreeNode postSelect(TreeNode node, int val) {
        TreeNode result = null;//结果
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
}

/**
 * 二叉树
 */
class BinaryTree {
    private TreeNode root;//根结点

    //构造方法
    public BinaryTree(TreeNode node) {
        root = node;
    }

    //删除结点
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
        root.delNode(root, val);
    }

    //前序遍历
    public void preOrder() {
        root.preOrder(root);
    }

    //中序遍历
    public void inOrder() {
        root.inOrder(root);
    }

    //后序遍历
    public void postOrder() {
        root.postOrder(root);
    }

    //前序查找
    public TreeNode preSelect(int val) {
        if (root == null) {
            return null;
        }
        return root.preSelect(root, val);
    }

    //中序查找
    public TreeNode inSelect(int val) {
        if (root == null) {
            return null;
        }
        return root.inSelect(root, val);
    }

    //后序查找
    public TreeNode postSelect(int val) {
        if (root == null) {
            return null;
        }
        return root.postSelect(root, val);
    }
}
