package com.gyx.Josephu;

/**
 * 约瑟夫问题
 */
public class JosephuDemo {
    public static void main(String args[]) {
        JosephuList list = new JosephuList();//新建链表
        int length = 100;//链表长度
        for (int i = 1; i <= length; i++) {//插入数据
            list.insert(new JosephuNode(i));
        }
        System.out.println("初始化的链表:");
        list.show();

        int k = 1;//第几个人开始
        int m = 3;//要数的数
        System.out.println("\n约瑟夫出队顺序:");
        list.josephu(k, m);
    }
}

//结点
class JosephuNode {
    int no;//编号
    JosephuNode next;//下一结点

    //构造方法
    public JosephuNode(int no) {
        this.no = no;
    }
}

//环形链表
class JosephuList {
    JosephuNode first;//第一个结点

    //构造方法
    public JosephuList() {
        this.first = new JosephuNode(0);
    }

    //判断链表是否为空
    public boolean isEmpty() {
        if (first.next == null) {
            return true;
        } else {
            return false;
        }
    }

    //插入
    public void insert(JosephuNode node) {
        if (isEmpty()) {//如果要插入的是首结点，要特殊处理
            first.next = node;
            node.next = node;
            return;
        }

        JosephuNode temp = first.next;
        JosephuNode preTemp = null;
        do {
            preTemp = temp;
            temp = temp.next;
        } while (temp != first.next);
        preTemp.next = node;
        node.next = first.next;
    }

    //删除
    public void delete(int no) {
        if (isEmpty()) {
            System.out.println("链表为空，没有可删除的结点");
            return;
        }

        //如果要删除的结点为首结点，要特殊处理
        if (no == first.next.no) {
            JosephuNode temp = first.next;
            while (temp.next != first.next) {//找到最后一个结点
                temp = temp.next;
            }
            JosephuNode head = first.next;
            first.next = head.next;
            temp.next = head.next;
            head.next = null;
            return;
        }

        JosephuNode temp = first.next;
        JosephuNode preTemp = null;
        while (temp.no != no) {
            preTemp = temp;
            temp = temp.next;
        }
        preTemp.next = temp.next;
        temp.next = null;
    }

    //显示链表数据
    public void show() {
        if (isEmpty()) {
            System.out.println("链表是空的");
            return;
        }

        JosephuNode temp = first.next;
        do {
            System.out.print(temp.no + " ");
            temp = temp.next;
        } while (temp != first.next);
    }

    //约瑟夫问题解决方法
    public void josephu(int k, int m) {
        JosephuNode cur = first.next;//当前结点
        for (int i = 1; i < k; i++) {//找到第k个结点
            cur = cur.next;
        }
        while (first.next.next != null) {//只要还有两个及以上的结点，就继续
            for (int i = 1; i < m; i++) {
                cur = cur.next;
            }
            System.out.print(cur.no + " ");
            JosephuNode temp = cur;
            cur = cur.next;
            delete(temp.no);
        }

        System.out.println("\n最后剩下的是" + cur.no);
    }
}
