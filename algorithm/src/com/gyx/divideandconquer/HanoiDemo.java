package com.gyx.divideandconquer;

/**
 * 汉诺塔
 */
public class HanoiDemo {
    public static void main(String[] args) {
        hanoi(10, "a", "b", "c");
    }

    public static void hanoi(int num, String a, String b, String c) {
        if (num == 1) {
            System.out.println("1 " + a + "->" + c);
        } else {
            hanoi(num - 1, a, c, b);
            System.out.println(num + " " + a + "->" + c);
            hanoi(num - 1, b, a, c);
        }
    }
}
