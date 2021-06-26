package com.gyx.kmp;

/**
 * kmp算法
 */
public class KMPDemo {
    public static void main(String[] args) {
        String str = "mississppi";
        String subStr = "issppi";
        System.out.println(kmp(str, subStr));
    }

    /**
     * 获取子串的部分匹配表
     *
     * @param subStr 子串
     * @return 部分匹配表
     */
    public static int[] getNext(String subStr) {
        int[] next = new int[subStr.length()];//部分匹配表
        next[0] = 0;//第一个字符的部分匹配值一定是0，这里算是初始化条件，这样后面才能做下去
        for (int i = 1, j = 0; i < subStr.length(); i++) {
            while (j > 0 && subStr.charAt(i) != subStr.charAt(j)) {
                j = next[j - 1];//长度减1，然后找到公共前后缀相等的长度，将j移到那里
            }
            if (subStr.charAt(i) == subStr.charAt(j)) {//相等就把j后移
                j++;
            }
            next[i] = j;//记录该位置前缀和后缀相等的长度
        }
        return next;
    }

    /**
     * kmp
     * 这里思想和getNext差不多，所以没写很多注释
     *
     * @param str    主串
     * @param subStr 子串
     * @return 匹配到的字符串的首字符下标
     */
    public static int kmp(String str, String subStr) {
        int[] next = getNext(subStr);
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != subStr.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == subStr.charAt(j)) {
                j++;
            }
            if (j == subStr.length()) {
                return i - j + 1;//因为j比i多加了1，所以i-j以后还要加1
            }
        }
        return -1;//没找到就返回-1
    }
}
