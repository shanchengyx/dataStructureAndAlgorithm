package com.gyx.violencematch;

/**
 * 暴力匹配
 */
public class ViolenceMatchDemo {
    public static void main(String[] args) {
        String str1 = "甘雨胡桃可可莉迪迪迪奥娜";
        String str2 = "迪娜";
        int result = violenceMatch(str1, str2);
        System.out.println(result);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i < s1.length && j < s2.length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
            if (j == s2.length) {
                return i - j;
            }
        }
        return -1;
    }
}
