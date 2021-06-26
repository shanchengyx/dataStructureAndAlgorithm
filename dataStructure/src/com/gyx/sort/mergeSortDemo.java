package com.gyx.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class mergeSortDemo {
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
        mergeSort(array, 0, length - 1);
        showTime();
    }

    //归并排序
    public static void mergeSort(int[] array, int left, int right) {
        if (left >= right) {//递归终止条件
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    //合并
    public static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - (left - 1)];//临时数组
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = 0;
        while (leftIndex <= mid && rightIndex <= right) {
            if (array[leftIndex] <= array[rightIndex]) {
                temp[tempIndex] = array[leftIndex];
                leftIndex++;
                tempIndex++;
            } else {
                temp[tempIndex] = array[rightIndex];
                rightIndex++;
                tempIndex++;
            }
        }

        //将剩余元素放到临时数组中
        while (leftIndex <= mid) {
            temp[tempIndex] = array[leftIndex];
            leftIndex++;
            tempIndex++;
        }
        while (rightIndex <= right) {
            temp[tempIndex] = array[rightIndex];
            rightIndex++;
            tempIndex++;
        }

        //将临时数组复制给array
        for (int i = left; i <= right; i++) {
            array[i] = temp[i - left];
        }
    }
}
