package com.gyx.queue;

import java.util.Scanner;

/**
 * 数组实现不可复用的队列
 */
public class Queue {
    public static void main(String args[]) {
        ArrayQueue queue = new ArrayQueue(3);
        boolean loop = true;
        char key = ' ';
        Scanner sc = new Scanner(System.in);
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加元素");
            System.out.println("g(get):取出元素");
            System.out.println("p(peek):查看队列头");
            System.out.println("e(exit):退出程序");
            key = sc.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数:");
                    int data = sc.nextInt();
                    queue.add(data);
                    break;
                case 'g':
                    try {
                        int res = queue.poll();
                        System.out.println("取出的数据是:" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int res = queue.peek();
                        System.out.println("队列头的元素为:" + res);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;//指向队列头的前一个位置，不包含第一个数据
        this.rear = -1;//指向队列尾，是包含最后一个数据的
    }

    public boolean isFull() {
        if (rear == maxSize - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (rear == front) {
            return true;
        } else {
            return false;
        }
    }

    public void add(int data) {
        if (isFull()) {
            System.out.println("队列已满，不能再插入");
            return;
        }
        rear++;
        arr[rear] = data;
    }

    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，没有元素可以取");
        }
        front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //显示队列的头数据
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        return arr[front + 1];
    }
}
