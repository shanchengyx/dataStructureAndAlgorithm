package com.gyx.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String args[]) {
        ArrayStack stack = new ArrayStack(10);//初始化栈
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

//用数组实现栈
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] array;//数组
    private int top = -1;//栈顶

    //构造方法
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[this.maxSize];
    }

    //是否栈满
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    //是否栈空
    public boolean isEmpty() {
        return (top == -1);
    }

    //入栈
    public void push(int value) {
        //先判断是否栈满，满了就不能再入栈了
        if (isFull()) {
            System.out.println("栈已满，不能再放入元素");
            return;
        }
        top++;
        array[top] = value;
    }

    //出栈
    public int pop() {
        //先判断是否栈空，空了就不能再出栈了
        if (isEmpty()) {
            throw new RuntimeException("栈已空，不能再取出元素");
        }
        int temp = array[top];
        top--;
        return temp;
    }

    //显示栈的所有数据
    public void show() {
        if (isEmpty()) {
            System.out.println("栈是空的");
        }
        int index = top;
        System.out.println("栈的数据如下:");
        for (int i = index; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
