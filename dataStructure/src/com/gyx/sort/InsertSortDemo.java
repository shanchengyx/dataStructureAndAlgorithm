package com.gyx.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSortDemo {
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
        insertSort(array);
        showTime();
    }

    //打印数组
    public static void printArray(int[] array) {
        System.out.println("数组如下:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    //插入排序
    public static void insertSort(int[] array) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < array.length; i++) {
            insertVal = array[i];//待插入的值
            insertIndex = i - 1;//要插入的位置
            //一直找，直到找到比它小的数
            while (insertIndex >= 0 && array[insertIndex] >= insertVal) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertVal;//插入的位置应该是比它小的数的后一个位置
        }
    }
}
