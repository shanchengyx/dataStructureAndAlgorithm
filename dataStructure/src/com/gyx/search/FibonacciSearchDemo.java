package com.gyx.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 斐波那契查找
 */
public class FibonacciSearchDemo {
    public static void main(String args[]) {
        int[] array = {31, 65, 8, 232, 9, 56, 9};
        Arrays.sort(array);//排序
        System.out.println(Arrays.toString(array));//打印排序后的数组
        List<Integer> result = fibonacciSearch(array, 0, array.length - 1, 32);
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
        int length = 80000000;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        Arrays.sort(array);
        showTime();
        fibonacciSearch(array, 0, length - 1, 108);
        showTime();
    }

    //生成斐波那契数列
    public static int[] fibonacci(int maxSize) {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 斐波那契查找
     *
     * @param array 待查找的数组
     * @param left  左边下标
     * @param right 右边下标
     * @param val   待查找的值
     * @return 返回查找到的下标
     */
    public static List<Integer> fibonacciSearch(int[] array, int left, int right, int val) {
        //生成斐波那契数列
        int k = 2;
        int[] fib = fibonacci(k + 1);
        while (fib[k] - 1 < array.length) {
            k++;
            fib = fibonacci(k + 1);//生成斐波那契数列
        }

        int[] newArray = Arrays.copyOf(array, fib[k] - 1);//扩容
        //空的位置补上最大的数
        for (int i = right + 1; i < newArray.length; i++) {
            newArray[i] = array[right];
        }

        int low = left;
        int high = newArray.length - 1;
        int mid = 0;
        //开始查找
        while (low <= high) {
            mid = low + fib[k - 1] - 1;//得到左右两个子数列之间的下标
            if (val < newArray[mid]) {
                high = mid - 1;
                k--;//换成左边的子数列
            } else if (val > newArray[mid]) {
                low = mid + 1;
                k = k - 2;//换成右边的子数列
            } else {
                if (mid <= high) {
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
                } else {//如果mid > high，就超出原数组的范围了，说明要找的就是最大那个数
                    List<Integer> result = new ArrayList<>();
                    result.add(newArray[mid]);
                    return result;
                }
            }
        }
        //low > high，说明找不到这样的元素，返回空数组
        return new ArrayList<Integer>();
    }
}
