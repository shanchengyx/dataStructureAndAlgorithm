package com.gyx.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 插值查找
 */
public class InsertValueSearchDemo {
    public static void main(String args[]) {
        int[] array = {31, 65, 8, 232, 9, 56, 9};
        Arrays.sort(array);//排序
        System.out.println(Arrays.toString(array));//打印排序后的数组
        List<Integer> result = insertValueSearch(array, 0, array.length - 1, 9);
        System.out.println("所找元素的下标为" + result.toString());
        searchTime();
    }

    //显示当前时间
    public static void showTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String formatDate = sdf.format(date);
        System.out.println(formatDate);
    }

    //测试查找时间
    public static void searchTime() {
        int length = 8000000;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        Arrays.sort(array);
        showTime();
        insertValueSearch(array, 0, length - 1, 108);
        showTime();
    }

    //插值查找
    public static List<Integer> insertValueSearch(int[] array, int left, int right, int val) {
        if (left > right || val < array[left] || val > array[right]) {//如果找不到，终止递归
            return new ArrayList<Integer>();
        }
        int mid = left + (right - left) * (val - array[left]) / (array[right] - array[left]);
        if (val < array[mid]) {//如果小于中间值，说明在左边
            return insertValueSearch(array, left, mid - 1, val);
        } else if (val > array[mid]) {//如果大于中间值，说明在右边
            return insertValueSearch(array, mid + 1, right, val);
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
