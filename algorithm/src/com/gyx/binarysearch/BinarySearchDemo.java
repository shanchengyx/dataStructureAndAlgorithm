package com.gyx.binarysearch;

import java.util.Arrays;

/**
 * 二分查找
 */
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] array = {1, 34, 2, 7, 19, 7};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        int result = binarySearch(array, 0);
        System.out.println(result);
    }

    /**
     * 二分查找的非递归方法
     *
     * @param array 要查找的数组
     * @param val   要查找的值
     * @return 查找到的值的下标，如果没找到，就返回-1
     */
    public static int binarySearch(int[] array, int val) {
        int low = 0;
        int high = array.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (val < array[mid]) {
                high = mid - 1;
            } else if (val > array[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
