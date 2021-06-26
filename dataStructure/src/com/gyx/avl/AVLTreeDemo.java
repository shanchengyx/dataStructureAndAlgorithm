package com.gyx.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] array = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < array.length; i++) {
            avlTree.add(array[i]);
        }
        avlTree.preOrder();
        System.out.println("\n" + avlTree.leftHeight(avlTree.root));
        System.out.println(avlTree.rightHeight(avlTree.root));
//        avlTree.leftRotate(avlTree.root);
//        avlTree.preOrder();
//        avlTree.rightRotate(avlTree.root);
//        avlTree.preOrder();

//        avlTree.inOrder();
//        avlTree.delNode(3);
//        avlTree.inOrder();
//        avlTree.delNode(10);
//        avlTree.inOrder();
//        avlTree.delNode(15);
//        avlTree.inOrder();
//        avlTree.delNode(6);
//        avlTree.inOrder();
//        avlTree.delNode(9);
//        avlTree.inOrder();
//        avlTree.delNode(1);
//        avlTree.inOrder();
//        avlTree.delNode(14);
//        avlTree.inOrder();
    }
}

//AVL树
class AVLTree {
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
        if (rightHeight(node) - leftHeight(node) > 1) {
            //如果右结点的左子树高于右子树，要先右旋右结点
            if (leftHeight(node.right) > rightHeight(node.right)) {
                rightRotate(node.right);
            }
            leftRotate(node);
            return;
        }
        if (leftHeight(node) - rightHeight(node) > 1) {
            //如果左结点的右子树高于左子树，要先左旋左结点
            if (rightHeight(node.left) > leftHeight(node.left)) {
                leftRotate(node.left);
            }
            rightRotate(node);
            return;
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
     * 前序遍历
     *
     * @param node 结点
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 重载前序遍历
     */
    public void preOrder() {
        System.out.println();
        if (root == null) {
            System.out.println("树是空的");
            return;
        }
        preOrder(root);
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

    /**
     * 返回当前结点的高度
     */
    public int height(Node node) {
        int leftHeight = 1;
        int rightHeight = 1;
        if (node.left != null) {
            leftHeight = height(node.left) + 1;
        }
        if (node.right != null) {
            rightHeight = height(node.right) + 1;
        }
        return Math.max(leftHeight, rightHeight);
    }

    /**
     * 返回左子树的高度
     *
     * @return 左子树的高度
     */
    public int leftHeight(Node node) {
        if (node.left == null) {
            return 0;
        } else {
            return height(node.left);
        }
    }

    /**
     * 返回右子树的高度
     *
     * @return 右子树的高度
     */
    public int rightHeight(Node node) {
        if (node.right == null) {
            return 0;
        } else {
            return height(node.right);
        }
    }

    /**
     * 左旋
     *
     * @param node 要旋转的结点
     */
    public void leftRotate(Node node) {
        if (node == null) {
            return;
        }
        //如果要左旋的是根结点
        if (node == root) {
            Node nodeRight = node.right;
            if (nodeRight != null) {
                root.right = nodeRight.left;
                if (nodeRight.left != null) {
                    nodeRight.left.parent = root;
                }
                nodeRight.left = root;
                root.parent = nodeRight;
                root = nodeRight;
            }
            return;
        }
        Node nodeRight = node.right;
        if (nodeRight != null) {
            node.right = nodeRight.left;
            if (nodeRight.left != null) {
                nodeRight.left.parent = node;
            }
            nodeRight.left = node;
            if (node.parent.left == node) {//如果旋转的结点是左结点
                node.parent.left = nodeRight;
            } else {//如果旋转的结点是右结点
                node.parent.right = nodeRight;
            }
            nodeRight.parent = node.parent;
            node.parent = nodeRight;
        }
    }

    /**
     * 右旋
     *
     * @param node 要旋转的结点
     */
    public void rightRotate(Node node) {
        if (node == null) {
            return;
        }
        //如果要右旋的是根结点
        if (node == root) {
            Node nodeLeft = node.left;
            if (nodeLeft != null) {
                root.left = nodeLeft.right;
                if (nodeLeft.right != null) {
                    nodeLeft.right.parent = root;
                }
                nodeLeft.right = root;
                root.parent = nodeLeft;
                root = nodeLeft;
            }
            return;
        }
        Node nodeLeft = node.left;
        if (nodeLeft != null) {
            node.left = nodeLeft.right;
            if (nodeLeft.right != null) {
                nodeLeft.right.parent = node;
            }
            nodeLeft.right = node;
            if (node.parent.left == node) {//如果旋转的结点是左结点
                node.parent.left = nodeLeft;
            } else {//如果旋转的结点是右结点
                node.parent.right = nodeLeft;
            }
            nodeLeft.parent = node.parent;
            node.parent = nodeLeft;
        }
    }
}
