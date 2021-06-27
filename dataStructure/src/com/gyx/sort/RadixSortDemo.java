package com.gyx.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基数排序
 */
public class RadixSortDemo {
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
        radixSort(array);
        showTime();
    }

    //基数排序
    public static void radixSort(int[] array) {
        int[][] bucket = new int[10][array.length];//10个桶，每个桶的长度等于数组长度
        int[] bucketElementCount = new int[10];//记录每个桶存放数据的个数
        int position = 1;//记录是个位还是百位千位
        while (true) {
            //把元素放入桶中
            for (int i = 0; i < array.length; i++) {
                int radix = (array[i] / position) % 10;//基数
                bucket[radix][bucketElementCount[radix]] = array[i];//把元素放入对应的桶
                bucketElementCount[radix]++;//个数加1
            }
            position = position * 10;//向前进一位
            //如果零桶的元素个数等于数组长度，说明全部元素都放入了零桶，排序完成了
            if (bucketElementCount[0] == array.length) {
                break;
            }
            //从桶中取出元素,这里index是数组的下标，用来取出元素
            for (int i = 0, index = 0; i < bucketElementCount.length; i++) {
                while (bucketElementCount[i] > 0) {
                    array[index] = bucket[i][bucketElementCount[i] - 1];//取出元素
                    index++;//数组下标后移
                    bucketElementCount[i]--;//取出了元素，个数减1
                }
            }
        }
    }
}
