package com.gyx.search;

/**
 * 线性查找
 */
public class SeqSearchDemo {
    public static void main(String args[]) {
        int[] array = {439, 23, 7, 82, 5, 8};
        int index = seqSearch(array, 439);
        if (index != -1) {
            System.out.println("所找元素下标为" + index);
        } else {
            System.out.println("没有找到该元素");
        }
    }

    //线性查找
    public static int seqSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;//没有找到就返回-1
    }
}
