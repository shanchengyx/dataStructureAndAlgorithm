package com.gyx.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] array = {21, 6, 8, 32, 5, 4, 20, 25, 1};
        heapSort(array);
        System.out.println(Arrays.toString(array));
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
        heapSort(array);
        showTime();
    }

    //交换堆中的元素
    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * 调整堆
     *
     * @param array    数组
     * @param index    要调整的子树
     * @param endIndex 调整的范围，endIndex是范围内最后一个结点的下标
     */
    public static void adjusteHeap(int[] array, int index, int endIndex) {
        int temp = array[index];//调整过程就是把该结点放到合适的位置，这里采用挖坑法，所以先保存变量
        for (int i = 2 * index + 1; i <= endIndex; i = 2 * i + 1) {
            if (i + 1 <= endIndex && array[i + 1] > array[i]) {//找到两个子结点中比较大的那个
                i = i + 1;
            }
            if (array[i] > temp) {//用挖坑法找到合适的位置
                array[index] = array[i];
                index = i;
            } else {
                break;//因为下面的结点都是经过排序的，如果不比子结点小，就不用继续比了
            }
        }
        array[index] = temp;//填坑
    }

    //堆排序
    public static void heapSort(int[] array) {
        //先建堆，(array.length - 2)/2就是最后一个非叶子结点
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            adjusteHeap(array, i, array.length - 1);
        }
        //调整堆，这里endIndex > 0，因为只剩一个结点就不用排了
        for (int endIndex = array.length - 1; endIndex > 0; endIndex--) {
            adjusteHeap(array, 0, endIndex);
            swap(array, 0, endIndex);
        }
    }
}
