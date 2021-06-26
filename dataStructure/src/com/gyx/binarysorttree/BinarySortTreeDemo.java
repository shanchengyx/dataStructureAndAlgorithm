package com.gyx.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] array = {3, 10, 15, 6, 9, 1, 14};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < array.length; i++) {
            binarySortTree.add(array[i]);
        }
        binarySortTree.inOrder();
        binarySortTree.delNode(3);
        binarySortTree.inOrder();
        binarySortTree.delNode(10);
        binarySortTree.inOrder();
        binarySortTree.delNode(15);
        binarySortTree.inOrder();
        binarySortTree.delNode(6);
        binarySortTree.inOrder();
        binarySortTree.delNode(9);
        binarySortTree.inOrder();
        binarySortTree.delNode(1);
        binarySortTree.inOrder();
        binarySortTree.delNode(14);
        binarySortTree.inOrder();
    }
}

//二叉排序树
class BinarySortTree {
    //结点，内部类
    class Node {
        int val;//存的值
        Node left;
        Node right;
        Node parent;//父结点

        Node(int val) {
            this.val = val;
        }
    }

    Node root;//根结点

    /**
     * 添加结点
     *
     * @param val 值
     */
    public void add(int val, Node node) {
        if (val <= node.val) {
            if (node.left == null) {
                node.left = new Node(val);
                node.left.parent = node;
            } else {
                add(val, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(val);
                node.right.parent = node;
            } else {
                add(val, node.right);
            }
        }
    }

    /**
     * 重载添加结点
     */
    public void add(int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }
        add(val, root);
    }

    /**
     * 删除结点
     *
     * @param val  要删除的结点的值
     * @param node 结点
     */
    public void delNode(int val, Node node) {
        if (val == node.val) {//找到了，删除该结点
            //删除结点时，只要分两种情况就行了，即有无右结点
            //因为右结点是大的，如果有右结点的话，就要把右结点放上来，如果没有就把左结点放上来
            //具体过程画个图就明白了
            if (node.right != null) {
                //找到右子树最小的结点
                Node temp = node.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                //将左子树接到该结点的左边
                temp.left = node.left;
                if (node.left != null) {
                    node.left.parent = temp;
                }
                //将子结点与父结点接起来
                if (node.parent.left == node) {//如果是父结点的左结点
                    node.parent.left = node.right;
                } else {//如果是父结点的右结点
                    node.parent.right = node.right;
                }
                node.right.parent = node.parent;
            } else {
                //将子结点与父结点接起来
                if (node.parent.left == node) {//如果是父结点的左结点
                    node.parent.left = node.left;
                } else {//如果是父结点的右结点
                    node.parent.right = node.left;
                }
                if (node.left != null) {
                    node.left.parent = node.parent;
                }
            }
        } else if (val < node.val) {
            delNode(val, node.left);//向左递归
        } else {
            delNode(val, node.right);//向右递归
        }
    }

    /**
     * 重载删除结点
     */
    public void delNode(int val) {
        if (root == null) {
            System.out.println("树是空的");
            return;
        }
        if (val == root.val) {//如果要删除的是根结点
            if (root.right != null) {//如果有右结点
                //找到右子树最小的结点
                Node temp = root.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                temp.left = root.left;//将左子树接到该结点的左边
                if (root.left != null) {
                    root.left.parent = temp;
                }
                root = root.right;
                if (root != null) {
                    root.parent = null;
                }
            } else {//如果没有右结点
                root = root.left;
                if (root != null) {
                    root.parent = null;
                }
            }
            return;
        }
        delNode(val, root);
    }

    /**
     * 中序遍历
     *
     * @param node 结点
     */
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    /**
     * 重载中序遍历
     */
    public void inOrder() {
        System.out.println();
        if (root == null) {
            System.out.println("树是空的");
            return;
        }
        inOrder(root);
    }
}
