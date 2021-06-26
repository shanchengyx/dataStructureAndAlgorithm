package com.gyx.linkedList;

public class SingleLinkedListDemo {
    public static void main(String args[]) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero4);

        list.update(new HeroNode(4, "零冲", "豹子头"));

        list.delete(1);

        list.show();
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public String toString() {
        return no + "," + name + "," + nickName;
    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        } else {
            return false;
        }
    }

    public void add(HeroNode node) {
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //按编号添加
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        HeroNode cur = null;
        while (temp != null && temp.no < node.no) {
            cur = temp;
            temp = temp.next;
        }
        if (temp == null) {
            cur.next = node;
        } else if (temp.no == node.no) {
            return;
        } else {
            cur.next = node;
            node.next = temp;
        }
    }

    public void update(HeroNode node) {
        if (isEmpty()) {
            System.out.println("链表是空的");
            return;
        }

        HeroNode temp = head;
        while (temp != null && temp.no != node.no) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("没有想修改的结点");
        } else {
            temp.no = node.no;
            temp.name = node.name;
            temp.nickName = node.nickName;
        }
    }

    public void delete(int no) {
        HeroNode temp = head;
        HeroNode cur = null;
        while (temp != null && temp.no != no) {
            cur = temp;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("没有想删除的结点");
        } else {
            cur.next = temp.next;
        }
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("链表是空的");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}
