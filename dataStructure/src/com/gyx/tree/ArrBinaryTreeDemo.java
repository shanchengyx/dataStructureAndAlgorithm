package com.gyx.tree;

/**
 * 数组实现的顺序存储二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(array);
        arrBinaryTree.preOrder();
        arrBinaryTree.inOrder();
        arrBinaryTree.postOrder();
    }
}

/**
 * 顺序存储二叉树
 */
class ArrBinaryTree {
    private int[] array;//数组

    //构造方法
    public ArrBinaryTree(int[] array) {
        this.array = array;
    }

    //前序遍历
    public void preOrder(int index) {
        System.out.print(array[index] + " ");
        if (2 * index + 1 < array.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < array.length) {
            preOrder(2 * index + 2);
        }
    }

    //重载
    public void preOrder() {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.print("前序遍历:");
        preOrder(0);
        System.out.println();
    }

    //中序遍历
    public void inOrder(int index) {
        if (2 * index + 1 < array.length) {
            inOrder(2 * index + 1);
        }
        System.out.print(array[index] + " ");
        if (2 * index + 2 < array.length) {
            inOrder(2 * index + 2);
        }
    }

    //重载
    public void inOrder() {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.print("中序遍历:");
        inOrder(0);
        System.out.println();
    }

    //后序遍历
    public void postOrder(int index) {
        if (2 * index + 1 < array.length) {
            postOrder(2 * index + 1);
        }
        if (2 * index + 2 < array.length) {
            postOrder(2 * index + 2);
        }
        System.out.print(array[index] + " ");
    }

    //重载
    public void postOrder() {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.print("后序遍历:");
        postOrder(0);
        System.out.println();
    }
}
