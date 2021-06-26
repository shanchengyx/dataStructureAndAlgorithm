package com.gyx.floyd;

/**
 * 弗洛伊德算法
 */
public class FloydDemo {
    public static void main(String[] args) {
        final int N = 65535;//用65535这个很大的数表示不可达
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};//顶点数组
        //邻接矩阵
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0},
        };
        //创建图
        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.show();
    }
}

/**
 * 图
 */
class Graph {
    private char[] vertex;//顶点数组
    private int[][] dis;//各顶点到其他顶点的距离，这个数组是不断变化的，用于求出最小距离
    private int[][] pre;//前驱的下标

    /**
     * 构造器
     *
     * @param vertex 顶点数组
     * @param matrix 邻接矩阵
     */
    public Graph(char[] vertex, int[][] matrix) {
        //分别初始化三个数组
        this.vertex = new char[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        this.dis = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.dis[i][j] = matrix[i][j];
            }
        }
        this.pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                this.pre[i][j] = i;
            }
        }
    }

    /**
     * 显示距离矩阵和前驱矩阵
     */
    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            System.out.printf("%-7c", vertex[i]);
        }
        System.out.println();
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre[0].length; j++) {
                System.out.printf("%-7d", pre[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < vertex.length; i++) {
            System.out.printf("%-7c", vertex[i]);
        }
        System.out.println();
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis[0].length; j++) {
                System.out.printf("%-7d", dis[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        int length = 0;//记录距离
        for (int k = 0; k < dis.length; k++) {//中间顶点
            for (int i = 0; i < dis.length; i++) {//出发顶点
                for (int j = 0; j < dis.length; j++) {//终点
                    length = dis[i][k] + dis[k][j];//以k为中间顶点的ij距离
                    if (length < dis[i][j]) {
                        dis[i][j] = length;//更新距离
                        pre[i][j] = pre[k][j];//更新前驱
                    }
                }
            }
        }
    }
}
