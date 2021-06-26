package com.gyx.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSortDemo {
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
        selectSort(array);
        showTime();
    }

    //打印数组
    public static void printArray(int[] array) {
        System.out.println("数组如下:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    //选择排序
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {//从左往右，把最小的数放在前面
            int minNumIndex = i;//最小数的下标
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minNumIndex]) {//寻找最小的数，并记录下标
                    minNumIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minNumIndex];
            array[minNumIndex] = temp;
        }
    }
}
