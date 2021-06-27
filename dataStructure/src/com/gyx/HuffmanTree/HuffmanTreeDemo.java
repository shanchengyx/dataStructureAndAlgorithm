package com.gyx.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 实现一个哈夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] array = {3, 6, 7, 10, 5};
        HuffmanTree huffmanTree = new HuffmanTree(array);
        huffmanTree.preOrder();
    }
}

/**
 * 结点
 * 实现了Comparable接口，为了排序。因为哈夫曼树要选出最小的结点，所以要排序
 */
class Node implements Comparable<Node> {
    int val;
    Node left;
    Node right;

    //构造方法
    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    //从小到大排序
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}

//哈夫曼树
class HuffmanTree {
    Node root;

    //构造方法
    public HuffmanTree(int[] array) {
        createHuffmanTree(array);
    }

    //创建哈夫曼树
    public void createHuffmanTree(int[] array) {
        List<Node> nodes = new ArrayList<>();//把结点放入ArrayList，这样才可以排序
        for (int i = 0; i < array.length; i++) {
            nodes.add(new Node(array[i]));
        }
        Collections.sort(nodes);
        //循环到只剩一个结点
        while (nodes.size() > 1) {
            //取出两个最小的结点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //合成一个新的结点
            Node parentNode = new Node(leftNode.val + rightNode.val);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            //把数组中两个小的结点换成新的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
            //排序
            Collections.sort(nodes);
        }
        //取出根节点
        root = nodes.get(0);
    }

    //前序遍历
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.toString() + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public int preOrder(int i) {
        return 1;
    }

    //重载
    public void preOrder() {
        if (root == null) {
            System.out.println("哈夫曼树是空的");
            return;
        }
        preOrder(root);
    }
}