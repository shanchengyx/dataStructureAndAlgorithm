package com.gyx.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSortDemo {
    public static void main(String args[]) {
        sortTime();
    }

    //显示当前时间
    public static void showTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String formatDate = sdf.format(date);
        System.out.println(formatDate);
    }

    //测试排序时间
    public static void sortTime() {
        int length = 80000;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        showTime();
        bubbleSort(array);
        showTime();
    }

    //打印数组
    public static void printArray(int[] array) {
        System.out.println("数组如下:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] array) {
        //如果一趟过程交换了就是true，没交换就是false，用来判断排序是否完成，从而避免不必要的运算
        boolean isSwap = false;//一开始设为false
        for (int i = array.length - 1; i >= 0; i--) {//从左往右，把大的数放到末尾
            for (int j = 0; j <= i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSwap = true;//交换了就置为true
                }
            }
            if (!isSwap) {//这一趟循环没有交换，说明排序完成了
                break;
            }
            isSwap = false;//置为false
        }
    }
}
