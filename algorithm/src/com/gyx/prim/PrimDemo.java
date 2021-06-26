package com.gyx.prim;

/**
 * 普利姆算法
 */
public class PrimDemo {
    public static void main(String[] args) {
        //创建图的结点
        char[] val = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //图的结点数
        int num = val.length;
        //邻接矩阵，10000表示不连通
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };
        //创建图
        Graph graph = new Graph(num, val, weight);
        graph.showGraph();
        MinTree minTree = new MinTree();
        minTree.prim(graph, 0);
    }
}

//最小生成树
class MinTree {
    /**
     * 普利姆算法生成最小生成树
     *
     * @param graph 图
     * @param first 从哪一个结点开始生成
     */
    public void prim(Graph graph, int first) {
        boolean[] isVisited = new boolean[graph.num];//记录结点是否访问过
        isVisited[first] = true;//将第一个结点标记为已访问
        int vertex1 = -1;//用来记录最小权值的两个结点
        int vertex2 = -1;
        int minWeight = 10000;//用来记录每次遍历的最小权值，最开始先设为不连通
        //一共要生成num - 1条边，所以循环num - 1次
        for (int count = graph.num - 1; count > 0; count--) {
            minWeight = 10000;//最开始先设为不连通
            for (int i = 0; i < graph.num; i++) {//i用来找到已访问的结点
                for (int j = 0; j < graph.num; j++) {//j用来找到未访问的结点
                    //如果i已访问，j未访问，且i，j连起来的权值小于之前的最小值
                    if (isVisited[i] == true && isVisited[j] == false && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        vertex1 = i;
                        vertex2 = j;
                    }
                }
            }
            System.out.println(graph.val[vertex1] + "->" + graph.val[vertex2]
                    + " weight:" + graph.weight[vertex1][vertex2]);
            isVisited[vertex2] = true;//标记为已访问
        }
    }
}

//图
class Graph {
    public int num;//结点个数
    public char[] val;//结点数据
    public int[][] weight;//权值

    /**
     * 构造器
     *
     * @param num    结点个数
     * @param val    结点的值
     * @param weight 权值
     */
    public Graph(int num, char[] val, int[][] weight) {
        this.num = num;
        this.val = new char[this.num];
        this.weight = new int[this.num][this.num];
        for (int i = 0; i < this.num; i++) {
            this.val[i] = val[i];//初始化图的系数
            for (int j = 0; j < this.num; j++) {
                this.weight[i][j] = weight[i][j];//初始化邻接矩阵
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     */
    public void showGraph() {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
    }
}
