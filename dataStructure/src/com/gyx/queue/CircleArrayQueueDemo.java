package com.gyx.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String args[]) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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

class CircleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;//指向队列头的前一个位置，不包含第一个数据
        this.rear = 0;//指向队列尾，是包含最后一个数据的
    }

    public int getSize() {
        return (rear + maxSize - front) % maxSize;
    }

    public boolean isFull() {
        if ((rear + 1) % maxSize == front) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if ((rear + maxSize - front) % maxSize == 0) {
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
        arr[rear] = data;
        rear = (rear + 1) % maxSize;
    }

    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，没有元素可以取");
        }
        int res = arr[front];
        front = (front + 1) % maxSize;
        return res;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        }
        for (int i = front; i < front + getSize(); i++) {
            System.out.print(arr[i % maxSize] + " ");
        }
        System.out.println();
    }

    //显示队列的头数据
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        return arr[front];
    }
}