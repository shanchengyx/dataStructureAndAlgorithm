package com.gyx.recursion;

/**
 * n皇后问题
 */
public class NQueenDemo {
    public static void main(String args[]) {
        int dimension = 8;
        NQueen nQueen = new NQueen(dimension);
        nQueen.setQueen(0);
        System.out.println("共" + nQueen.count() + "种摆放方式");
    }
}

/**
 * N皇后类
 */
class NQueen {
    private int dimension;//维数
    int[] chessBorad;//棋盘
    private static int count = 0;

    //构造方法
    public NQueen(int dimension) {
        this.dimension = dimension;
        chessBorad = new int[this.dimension];
    }

    /**
     * 判断当前皇后是否与之前的皇后冲突
     *
     * @param curLine 当前行
     */
    public boolean isConflicted(int curLine) {
        boolean result = false;//默认是不冲突的
        for (int i = 0; i < curLine; i++) {
            //因为摆放的时候就不在同一行，所以只要判断在不在同一列或斜线就行了
            if (chessBorad[curLine] == chessBorad[i] || Math.abs(curLine - i) == Math.abs(chessBorad[curLine] - chessBorad[i])) {
                result = true;//检测到冲突就把结果改成冲突
            }
        }
        return result;
    }

    //摆放皇后
    public void setQueen(int curLine) {
        //如果摆到超出棋盘最上面了，就是成功了
        if (curLine >= dimension) {
            show();
            return;
        }
        for (int column = 0; column < dimension; column++) {
            chessBorad[curLine] = column;
            if (!isConflicted(curLine)) {
                setQueen(curLine + 1);
            }
        }
    }

    //显示皇后的摆放方式
    public void show() {
        for (int i = 0; i < dimension; i++) {
            System.out.print(chessBorad[i] + " ");
        }
        System.out.println();
        count++;
    }

    //一共多少种摆放方式
    public int count() {
        return count;
    }
}
