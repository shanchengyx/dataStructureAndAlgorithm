package com.gyx.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉查找
 */
public class BinarySearchDemo {
    public static void main(String args[]) {
        int[] array = {31, 65, 8, 232, 9, 56, 9};
        Arrays.sort(array);//排序
        System.out.println(Arrays.toString(array));//打印排序后的数组
        List<Integer> result = binarySearch(array, 0, array.length - 1, 8);
        System.out.println("所找元素的下标为" + result.toString());
    }

    /**
     * 二分查找
     *
     * @param array 待查找数组
     * @param left  左边下标
     * @param right 右边下标
     * @param val   待查找值
     * @return 是一个数组，包含了查找到的所有坐标
     */
    public static List<Integer> binarySearch(int[] array, int left, int right, int val) {
        if (left > right) {//如果找不到，终止递归
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        if (val < array[mid]) {//如果小于中间值，说明在左边
            return binarySearch(array, left, mid - 1, val);
        } else if (val > array[mid]) {//如果大于中间值，说明在右边
            return binarySearch(array, mid + 1, right, val);
        } else {//剩下的情况就是找到了
            List<Integer> result = new ArrayList<>();
            result.add(mid);
            int leftIndex = mid - 1;//向左查找的指针
            int rightIndex = mid + 1;//向右查找的指针
            while (leftIndex >= left && array[leftIndex] == val) {//向左查找，直到找到所有值
                result.add(leftIndex);
                leftIndex--;
            }
            while (rightIndex <= right && array[rightIndex] == val) {//向右查找，直到找到所有值
                result.add(rightIndex);
                rightIndex++;
            }
            return result;
        }
    }
}
