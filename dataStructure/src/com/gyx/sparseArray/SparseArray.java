package com.gyx.sparseArray;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String args[]) {
        //11 * 11的棋盘
        //0表示没棋子，1表示白子，2表示黑子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][5] = 3;
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //转换成稀疏数组
        //先统计非零值的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //给稀疏数组赋值
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //存放数据
        int index = 1;//稀疏数组的下标
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = chessArr1[i][j];
                    index++;
                }
            }
        }

        System.out.println();
        System.out.println("得到的稀疏数组");
        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组恢复成二维数组
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println();
        System.out.println("将稀疏数组恢复成二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
