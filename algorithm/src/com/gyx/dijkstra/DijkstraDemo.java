package com.gyx.dijkstra;


import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 */
public class DijkstraDemo {
    public static void main(String[] args) {
        final int N = 65535;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};//顶点数组
        //邻接矩阵
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(vertex, matrix);
        graph.dsj(6);
        graph.showResult();
    }
}

/**
 * 图
 */
class Graph {
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//记录访问顶点的情况

    /**
     * 构造器
     *
     * @param vertex 顶点数组
     * @param matrix 邻接矩阵
     */
    public Graph(char[] vertex, int[][] matrix) {
        //给顶点数组赋值
        this.vertex = new char[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        //给邻接矩阵赋值
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * 更新index顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index 顶点下标
     */
    private void update(int index) {
        int dis = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            dis = vv.getDis(index) + matrix[index][j];
            if (!vv.isVisited(j) && dis < vv.getDis(j)) {
                vv.updatePre(j, index);
                vv.updateDis(j, dis);
            }
        }
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发顶点的下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = vertex.length - 1; i > 0; i--) {
            index = vv.updateArr();
            update(index);
        }
    }

    /**
     * 展示结果
     */
    public void showResult() {
        for (int i = 0; i < vv.already_arr.length; i++) {
            System.out.print(vv.already_arr[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < vv.pre_visited.length; i++) {
            System.out.print(vv.pre_visited[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < vv.dis.length; i++) {
            System.out.print(vv.getDis(i) + " ");
        }
    }
}

/**
 * 记录访问顶点的情况
 */
class VisitedVertex {
    private final int N = 65535;
    public boolean[] already_arr;//已经访问过的顶点集合
    public int[] pre_visited;//记录访问过程中每一个结点的前驱结点
    public int[] dis;//记录出发顶点到其他所有顶点的距离

    /**
     * 构造器
     *
     * @param num        顶点个数
     * @param firstIndex 出发顶点的下标
     */
    public VisitedVertex(int num, int firstIndex) {
        this.already_arr = new boolean[num];
        this.pre_visited = new int[num];
        this.dis = new int[num];
        Arrays.fill(dis, N);
        this.already_arr[firstIndex] = true;//将出发顶点标记为已访问
        this.dis[firstIndex] = 0;//出发顶点的距离设置为0
    }

    /**
     * 判断顶点是否被访问过
     *
     * @param index 顶点下标
     * @return 访问过就返回true, 没访问过就返回false
     */
    public boolean isVisited(int index) {
        return already_arr[index];
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index  顶点下标
     * @param newDis 新的距离
     */
    public void updateDis(int index, int newDis) {
        dis[index] = newDis;
    }

    /**
     * 更新前驱结点
     *
     * @param index  顶点下标
     * @param newPre 新的前驱
     */
    public void updatePre(int index, int newPre) {
        pre_visited[index] = newPre;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index 顶点下标
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 选择新的起点
     *
     * @return 返回新起点的下标
     */
    public int updateArr() {
        int min = N;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (!isVisited(i) && getDis(i) < min) {
                min = getDis(i);
                index = i;
            }
        }
        already_arr[index] = true;
        return index;
    }
}