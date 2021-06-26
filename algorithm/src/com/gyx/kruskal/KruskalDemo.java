package com.gyx.kruskal;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 克鲁斯卡尔算法
 */
public class KruskalDemo {
    public static void main(String[] args) {
        final int INF = Integer.MAX_VALUE;//用int的最大值表示不连通
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};//顶点数组
        //邻接矩阵
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        Kruskal kruskal = new Kruskal(vertex, matrix);
        List<Edge> result = kruskal.kruskal();
        System.out.println(result.toString());
    }
}

/**
 * 克鲁斯卡尔算法
 */
class Kruskal {
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵，存的是权值
    private List<Edge> edges = new ArrayList<>();//边数组，形式是['A','B',12]，即存储边的两个结点和权值
    private static final int INF = Integer.MAX_VALUE;//用int的最大值表示不连通

    /**
     * 构造器
     *
     * @param vertex 顶点数组
     * @param matrix 邻接矩阵
     */
    public Kruskal(char[] vertex, int[][] matrix) {
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
        getEdges();//获取边的数组
    }

    /**
     * 通过邻接矩阵获取图的边数组
     */
    private void getEdges() {
        for (int i = 0; i < matrix.length; i++) {
            //只需要和自己后面的结点形成边就行了，这样可以避免重复比较，所以j = i + 1
            for (int j = i + 1; j < matrix[0].length; j++) {
                if (matrix[i][j] < INF) {
                    edges.add(new Edge(vertex[i], vertex[j], matrix[i][j]));
                }
            }
        }
    }

    /**
     * 对边进行排序
     */
    private void sortEdge() {
        Collections.sort(edges);
    }

    /**
     * 返回顶点的下标
     *
     * @param ver 顶点
     * @return 顶点的下标
     */
    public int getVertexIndex(char ver) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ver) {
                return i;
            }
        }
        return -1;//如果没找到，返回-1
    }

    /**
     * 获取下标为vertexIndex的顶点的终点
     *
     * @param end         该数组记录各顶点的终点
     * @param vertexIndex 顶点下标
     * @return 该顶点的终点下标
     */
    private int getEndIndex(int[] end, int vertexIndex) {
        //如果数组里顶点的终点为0，说明该顶点的终点还是它自己，没有改变，直接返回就行了
        while (end[vertexIndex] != 0) {
            vertexIndex = end[vertexIndex];//不断地寻找终点
        }
        return vertexIndex;
    }

    /**
     * 克鲁斯卡尔算法生成最小生成树
     *
     * @return 最小生成树的边数组
     */
    public List<Edge> kruskal() {
        sortEdge();//先排序边数组
        int[] end = new int[edges.size()];//保存最小生成树各结点的终点
        List<Edge> result = new ArrayList<>();//最小生成树的边数组
        //开始遍历，生成最小生成树
        for (int i = 0; i < edges.size(); i++) {
            //获取边的两个顶点
            int vertex1Index = getVertexIndex(edges.get(i).getVertex1());
            int vertex2Index = getVertexIndex(edges.get(i).getVertex2());
            //获取这两个顶点的终点
            int vertex1End = getEndIndex(end, vertex1Index);
            int vertex2End = getEndIndex(end, vertex2Index);
            //如果两个顶点的终点不同
            if (vertex1End != vertex2End) {
                //这里是用终点下标来设置终点的，因为在寻找终点时，是通过终点来不断寻找终点的
                end[vertex1End] = vertex2End;
                result.add(edges.get(i));
            }
        }
        return result;
    }
}

/**
 * 边的类，用来存储边的两个结点和权值，形式是['A','B',12]
 */
class Edge implements Comparable<Edge> {
    private char vertex1;//边的一个点
    private char vertex2;//边的一个点
    private int weight;//边的权值

    public char getVertex1() {
        return vertex1;
    }

    public void setVertex1(char vertex1) {
        this.vertex1 = vertex1;
    }

    public char getVertex2() {
        return vertex2;
    }

    public void setVertex2(char vertex2) {
        this.vertex2 = vertex2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * 构造器
     *
     * @param vertex1 边的一个顶点
     * @param vertex2 边的另一个顶点
     * @param weight  权值
     */
    public Edge(char vertex1, char vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge[" + vertex1 + " " + vertex2 + " " + weight + "]";
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}