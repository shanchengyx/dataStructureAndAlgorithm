package com.gyx.recursion;

/**
 * 迷宫问题
 */
public class Mase {
    public static void main(String args[]) {
        //先创建一个迷宫
        //用二维数组模拟地图
        int length = 8;
        int width = 8;
        int[][] map = new int[width][length];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < length; i++) {
            map[0][i] = 1;
            map[width - 1][i] = 1;
        }
        //左右全部置为1
        for (int i = 1; i < width - 1; i++) {
            map[i][0] = 1;
            map[i][length - 1] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][4] = 1;
        map[2][4] = 1;
        map[3][4] = 1;
        map[4][4] = 1;
        map[5][4] = 1;
        map[5][3] = 1;
        map[5][2] = 1;
        map[2][2] = 1;
        showMap(map);

        //寻找路线
        findRoute(map, 1, 1, 2, 5);
        showMap(map);
    }

    //显示地图
    public static void showMap(int[][] map) {
        System.out.println("\n地图如下:");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 寻找路线
     * (starti,startj)是起点，(endi,endj)是终点
     * 寻找路线:下，右，上，左
     * 标记:没走过的点为0，墙为1，走过的点为2，走不通的点为3
     */
    public static boolean findRoute(int[][] map, int starti, int startj, int endi, int endj) {
        //如果能走到走点为2，说明走通了
        if (map[endi][endj] == 2) {
            return true;
        } else {
            //没走过的点
            if (map[starti][startj] == 0) {
                map[starti][startj] = 2;//先把当前点设为走得通的点
                if (findRoute(map, starti + 1, startj, endi, endj)) {//向下
                    return true;
                } else if (findRoute(map, starti, startj + 1, endi, endj)) {//向右
                    return true;
                } else if (findRoute(map, starti - 1, startj, endi, endj)) {//向上
                    return true;
                } else if (findRoute(map, starti, startj - 1, endi, endj)) {//向左
                    return true;
                } else {//都不行，返回false
                    map[starti][startj] = 3;//走不通，回溯，把点设为走不通的点
                    return false;
                }
            } else {//其他情况说明这个点不用继续了
                return false;
            }
        }
    }
}
