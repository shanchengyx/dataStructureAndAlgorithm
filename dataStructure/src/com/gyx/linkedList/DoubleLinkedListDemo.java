package com.gyx.linkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String args[]) {
        //定义结点
        DoubleLinkedListHeroNode hero1 = new DoubleLinkedListHeroNode(1, "宋江", "及时雨");
        DoubleLinkedListHeroNode hero2 = new DoubleLinkedListHeroNode(2, "卢俊义", "玉麒麟");
        DoubleLinkedListHeroNode hero3 = new DoubleLinkedListHeroNode(3, "吴用", "智多星");
        DoubleLinkedListHeroNode hero4 = new DoubleLinkedListHeroNode(4, "林冲", "豹子头");

        //定义链表
        DoubleLinkedList list = new DoubleLinkedList();

        list.insertByOrder(hero1);
        list.insertByOrder(hero2);
        list.insertByOrder(hero3);
        list.insertByOrder(hero4);
        System.out.println("添加结点后的链表");
        list.show();

        list.delete(4);
        System.out.println("\n删除结点后的链表");
        list.show();

        list.update(new DoubleLinkedListHeroNode(2, "小卢", "玉椰羊"));
        System.out.println("\n修改结点后的链表");
        list.show();
    }
}

//双向链表的结点
class DoubleLinkedListHeroNode {
    public int no;//编号
    public String name;//名字
    public String nickname;//称号
    public DoubleLinkedListHeroNode pre;//前指针
    public DoubleLinkedListHeroNode next;//后指针

    //构造方法
    public DoubleLinkedListHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //转换成字符串函数
    public String toString() {
        return no + "," + name + "," + nickname;
    }
}

//双向链表
class DoubleLinkedList {
    public DoubleLinkedListHeroNode head = new DoubleLinkedListHeroNode(0, "", "");//头结点

    //判断是否为空
    public boolean isEmpty() {
        if (head.next == null) {//为空返回true
            return true;
        } else {//不为空返回false
            return false;
        }
    }

    //按编号顺序插入
    public void insertByOrder(DoubleLinkedListHeroNode node) {
        DoubleLinkedListHeroNode temp = head;
        DoubleLinkedListHeroNode preTemp = null;//记录前一个
        while (temp != null && temp.no < node.no) {//遍历，直到为空或者找到适合的编号位置
            preTemp = temp;
            temp = temp.next;
        }
        if (temp == null) {//如果为空
            preTemp.next = node;
            node.pre = preTemp;
        } else {
            preTemp.next = node;
            node.next = temp;
            temp.pre = node;
            node.pre = preTemp;
        }
    }

    //删除结点，按编号删除
    public void delete(int no) {
        if (isEmpty()) {
            System.out.println("链表是空的，没有元素可删除");
            return;
        }
        DoubleLinkedListHeroNode temp = head.next;
        while (temp != null && temp.no != no) {//遍历，直到为空或找到要删除的结点
            //TODO 查找要删除的结点
            temp = temp.next;
        }
        if (temp == null) {//如果为空
            System.out.println("要删除的结点不存在");
        } else {//如果找到了要删除的结点
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
            temp.pre = null;
            temp.next = null;
        }
    }

    //修改结点
    public void update(DoubleLinkedListHeroNode node) {
        if (isEmpty()) {
            System.out.println("链表是空的，没有元素可删除");
            return;
        }
        DoubleLinkedListHeroNode temp = head.next;
        while (temp != null && temp.no != node.no) {//遍历，直到为空或找到要删除的结点
            //TODO 查找要修改的结点
            temp = temp.next;
        }
        if (temp == null) {//如果为空
            System.out.println("要修改的结点不存在");
        } else {//如果找到了要修改的结点
            temp.no = node.no;
            temp.name = node.name;
            temp.nickname = node.nickname;
        }
    }

    //遍历链表
    public void show() {
        DoubleLinkedListHeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}
