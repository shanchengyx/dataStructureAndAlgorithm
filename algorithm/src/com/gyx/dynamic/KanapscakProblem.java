package com.gyx.dynamic;

/**
 * 背包问题
 */
public class KanapscakProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3};//物品的重量
        int[] value = {1500, 3000, 2000};//物品的价值
        int capacity = 4;//背包的容量
        int num = weight.length;//物品的个数
        int[][] dp = new int[num + 1][capacity + 1];//动态规划表
        int[][] path = new int[num + 1][capacity + 1];//记录放入的物品
        for (int i = 1; i < num + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (weight[i - 1] > j) {//如果物品重量大于背包容量
                    dp[i][j] = dp[i - 1][j];//取同背包容量的上一次价值，即最大价值
                } else {//如果新物品可以放进背包
                    //取上次最大值和放入新物品的价值中更大的那个
//                    dp[i][j] = Math.max(value[i - 1] + dp[i - 1][j - weight[i - 1]],dp[i - 1][j]);
                    if (value[i - 1] + dp[i - 1][j - weight[i - 1]] > dp[i - 1][j]) {
                        path[i][j] = 1;
                        dp[i][j] = value[i - 1] + dp[i - 1][j - weight[i - 1]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        System.out.println(dp[num][capacity]);

        for (int i = path.length - 1, j = path[0].length - 1; i > 0 && j > 0; ) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个物品放入背包");
                j = j - weight[i - 1];
            }
            i--;
        }
    }
}
