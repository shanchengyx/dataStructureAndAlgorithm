package com.gyx.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSortDemo {
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
        int length = 8000000;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        showTime();
        shellSort(array);
        showTime();
    }

    //希尔排序
    public static void shellSort(int[] array) {
        int insertVal = 0;//待要入的值
        int insertIndex = 0;//要插入的位置
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {//增量不断变化
            for (int i = gap; i < array.length; i++) {
                insertVal = array[i];
                insertIndex = i - gap;//让每个数都在组内排序
                //不断向前，直到找到比insertVal小的数
                while (insertIndex >= 0 && array[insertIndex] >= insertVal) {
                    array[insertIndex + gap] = array[insertIndex];
                    insertIndex = insertIndex - gap;
                }
                array[insertIndex + gap] = insertVal;//插入的位置是上一个insertIndex`
            }
        }
    }
}
