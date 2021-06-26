package com.gyx.stack;

import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String args[]) {
        LinkedListStack stack = new LinkedListStack(5);//初始化栈
        Scanner input = new Scanner(System.in);
        char key = ' ';//接收键盘输入
        //1表示入栈，2表示出栈，3表示显示栈的数据,4表示退出程序
        boolean loop = true;//控制循环
        while (loop) {
            System.out.println("\n1:入栈 2:出栈 3:显示栈的数据 4:退出程序");
            key = input.next().charAt(0);
            switch (key) {
                case '1':
                    System.out.println("请输入一个数据:");
                    int value = input.nextInt();
                    stack.push(value);
                    break;
                case '2':
                    try {
                        System.out.println("出栈元素为:" + stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case '3':
                    stack.show();
                    break;
                case '4':
                    loop = false;
                    System.out.println("程序已退出");
                    break;
                default:
                    System.out.println("你输入的操作不存在，请重新输入");
                    break;
            }
        }
        input.close();
    }
}

//用链表实现栈
class LinkedListStack {
    //结点
    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    private int maxSize;//栈的大小
    private int length = 0;//有效数据的长度
    private Node head;//头结点

    //构造方法
    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        head = new Node(0);
    }

    //栈是否空
    public boolean isEmpty() {
        return (length == 0);
    }

    //栈是否满
    public boolean isFull() {
        return (length == maxSize);
    }

    //入栈
    public void push(int val) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈已满，不能再放入数据");
            return;
        }
        Node node = new Node(val);
        //如果栈是空的，要特殊处理
        if (isEmpty()) {
            head.next = node;
            length++;
            return;
        }
        Node temp = head.next;
        head.next = node;
        node.next = temp;
        length++;
    }

    //出栈
    public int pop() {
        //先判断是否栈满
        if (isEmpty()) {
            throw new RuntimeException("栈是空的，没有元素可以出栈");
        }
        //如果栈中只有一个元素，要特殊处理
        if (length == 1) {
            int val = head.next.val;
            head.next = null;
            length--;
            return val;
        }
        Node temp = head.next;
        int val = temp.val;
        head.next = temp.next;
        temp.next = null;
        temp = null;
        length--;
        return val;
    }

    //显示栈的数据
    public void show() {
        //先判断是否栈满
        if (isEmpty()) {
            System.out.println("栈是空的");
            return;
        }
        System.out.println("栈的数据如下:");
        Node temp = head.next;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
}