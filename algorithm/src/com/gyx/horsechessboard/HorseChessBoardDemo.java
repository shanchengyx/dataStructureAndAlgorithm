package com.gyx.horsechessboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 马踏棋盘
 */
public class HorseChessBoardDemo {
    public static void main(String[] args) {
        int numRow = 8;//行数
        int numColumn = 8;//列数
        HorseChessBoard horseChessBoard = new HorseChessBoard(numRow, numColumn);
        horseChessBoard.play(0, 0, 1);//开始马踏棋盘
        int[][] chessBoard = horseChessBoard.getChessBoard();//获取棋盘
        //打印结果
        for (int i = 0; i < chessBoard.length; i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }
}

/**
 * 马踏棋盘
 */
class HorseChessBoard {
    private int numRow = 0;//行数
    private int numColumn = 0;//列数
    private int[][] chessBoard;//棋盘
    private boolean[][] isVisited;//标记各个位置是否被访问
    private boolean isFinished = false;//标记是否完成了马踏棋盘

    public int[][] getChessBoard() {
        return chessBoard;
    }

    /**
     * 构造器
     *
     * @param numRow    行数
     * @param numColumn 列数
     */
    public HorseChessBoard(int numRow, int numColumn) {
        this.numRow = numRow;
        this.numColumn = numColumn;
        this.chessBoard = new int[numRow][numColumn];
        this.isVisited = new boolean[numRow][numColumn];
    }

    /**
     * 获取马下一步可以走的点
     *
     * @param curPoint 当前点
     * @return 下一步可以走的点
     */
    public java.util.List<Point> getNext(Point curPoint) {
        java.util.List<Point> result = new ArrayList<>();
        Point tempPoint = new Point();
        //检测8个位置中，哪些是可以走的
        //左上角
        if ((tempPoint.x = curPoint.x - 2) >= 0 && (tempPoint.y = curPoint.y - 1) >= 0) {
            result.add(new Point(tempPoint));
        }
        if ((tempPoint.x = curPoint.x - 1) >= 0 && (tempPoint.y = curPoint.y - 2) >= 0) {
            result.add(new Point(tempPoint));
        }
        //右上角
        if ((tempPoint.x = curPoint.x + 1) < numColumn && (tempPoint.y = curPoint.y - 2) >= 0) {
            result.add(new Point(tempPoint));
        }
        if ((tempPoint.x = curPoint.x + 2) < numColumn && (tempPoint.y = curPoint.y - 1) >= 0) {
            result.add(new Point(tempPoint));
        }
        //右下角
        if ((tempPoint.x = curPoint.x + 2) < numColumn && (tempPoint.y = curPoint.y + 1) < numRow) {
            result.add(new Point(tempPoint));
        }
        if ((tempPoint.x = curPoint.x + 1) < numColumn && (tempPoint.y = curPoint.y + 2) < numRow) {
            result.add(new Point(tempPoint));
        }
        //左下角
        if ((tempPoint.x = curPoint.x - 1) >= 0 && (tempPoint.y = curPoint.y + 2) < numRow) {
            result.add(new Point(tempPoint));
        }
        if ((tempPoint.x = curPoint.x - 2) >= 0 && (tempPoint.y = curPoint.y + 1) < numRow) {
            result.add(new Point(tempPoint));
        }
        return result;
    }

    /**
     * 开始马踏棋盘
     *
     * @param x    当前位置的横坐标
     * @param y    当前位置的纵坐标
     * @param step 当前是第几步
     */
    public void play(int x, int y, int step) {
        chessBoard[y][x] = step;//在棋盘上标记这是第几步
        isVisited[y][x] = true;//标记为已访问
        java.util.List<Point> next = getNext(new Point(x, y));//获取下一步可以走的点
        sortPoint(next);
        Point tempPoint;//用来记录next数组中的点
        while (!next.isEmpty()) {
            tempPoint = next.remove(0);//这里是要取出每一步，不能用get，只能用remove
            if (!isVisited[tempPoint.y][tempPoint.x]) {//如果没访问，就递归
                play(tempPoint.x, tempPoint.y, step + 1);
            }
        }
        //注意，这个isFinished是关键，它是用来控制回溯的，如果它为假，
        //表示未完成，就要回溯，也就是要逐渐往回修改信息
        //如果它为真，就只是往回，但不修改信息
        if (step < numRow * numColumn && !isFinished) {
            chessBoard[y][x] = 0;//重置为0
            isVisited[y][x] = false;
        } else {
            isFinished = true;//整个棋盘都走完了，标记为已完成
        }
    }

    /**
     * 把下一步的点按下下一步的数量排序，例如，下一步1号点的下下一步数量比2号点的少，它就排在前面
     */
    public void sortPoint(java.util.List<Point> next) {
        next.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = getNext(o1).size();//第一个点的下下一步数量
                int count2 = getNext(o2).size();//第二个点的下下一步数量
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
