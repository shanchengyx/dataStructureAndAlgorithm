package com.gyx.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSortDemo {
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
        quickSort(array, 0, length - 1);
        showTime();
    }

    //快速排序
    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = array[left];//基准值
        int leftIndex = left;//左边下标
        int rightIndex = right;//右边下标
        while (leftIndex < rightIndex) {
            //从右向左，直到找到小于pivot的数
            while (leftIndex < rightIndex && array[rightIndex] >= pivot) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                array[leftIndex] = array[rightIndex];//把小于pivot的数放到leftIndex上
            }

            //从左向左，直到找到大于等于pivot的数
            while (leftIndex < rightIndex && array[leftIndex] < pivot) {
                leftIndex++;
            }
            if (leftIndex < rightIndex) {
                array[rightIndex] = array[leftIndex];//把大于等于pivot的数放到rightIndex上
            }
        }

        array[leftIndex] = pivot;//把基准值归位
        quickSort(array, left, leftIndex - 1);
        quickSort(array, leftIndex + 1, right);
    }
}
