package com.gyx.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图的基本功能实现
 */
public class GraphDemo {
    public static void main(String[] args) {
//        String[] vertex = {"A", "B", "C", "D", "E"};//结点的值
        String[] vertex = {"1", "2", "3", "4", "5", "6", "7", "8"};//结点的值
        Graph graph = new Graph(vertex.length);//创建图
        //添加结点
        for (int i = 0; i < vertex.length; i++) {
            graph.vertexList.add(vertex[i]);
        }
        //添加边
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 7, 1);
        graph.showGraph();
        graph.dfs();
        System.out.println();
        graph.bfs();
    }
}

//图
class Graph {
    public List<String> vertexList;//存储顶点集合
    public int[][] edges;//存储邻接矩阵
    public int numOfEdges;//表示边的个数

    //构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第一个顶点
     * @param v2     第二个顶点
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回结点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回结点的值
     *
     * @param index 结点下标
     * @return 结点的值
     */
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 返回权值
     *
     * @param vertex1 第一个结点
     * @param vertex2 第二个结点
     * @return 权值
     */
    public int getWeight(int vertex1, int vertex2) {
        return edges[vertex1][vertex2];
    }

    /**
     * 显示图的邻接矩阵
     */
    public void showGraph() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[0].length; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 获取下一个邻接结点
     *
     * @param head 此次递归的首结点
     * @param pre  上一个邻接结点
     * @return 下一个邻接结点
     */
    public int getNextNeighbor(int head, int pre) {
        //遍历邻接矩阵中head所在的那一行
        for (int next = pre + 1; next < getNumOfVertex(); next++) {
            if (edges[head][next] > 0) {//如果大于0，说明是通的
                return next;//返回下标
            }
        }
        return -1;//如果没有邻接结点了，就返回-1
    }

    /**
     * 深度优先遍历
     *
     * @param isVisited 记录结点是否被访问过
     * @param head      此次递归的首结点
     */
    private void dfs(int head, boolean[] isVisited) {
        System.out.print(getValueByIndex(head) + " ");//先打印出该结点
        isVisited[head] = true;//设置成访问过了
        int next = getNextNeighbor(head, head);//获取下一个邻接结点
        while (next != -1) {//一直循环,直到没有邻接结点
            if (!isVisited[next]) {//如果没被访问过，就递归
                dfs(next, isVisited);
            }
            next = getNextNeighbor(head, next);
        }
    }

    /**
     * 重载深度优先遍历
     */
    public void dfs() {
        boolean[] isVisited = new boolean[vertexList.size()];
        //遍历每个结点
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(i, isVisited);
            }
        }
    }

    /**
     * 广度优先遍历
     *
     * @param isVisited 记录结点是否被访问过
     * @param index     此次递归的首结点下标
     */
    private void bfs(int index, boolean[] isVisited) {
        int head;//队列头结点的下标
        int next;//下一个邻接结点的下标
        Queue<Integer> queue = new LinkedList<>();//队列
        System.out.print(getValueByIndex(index) + " ");//输出当前结点信息
        isVisited[index] = true;//标记为已访问
        queue.add(index);
        while (!queue.isEmpty()) {
            head = queue.poll();//取出头结点
            next = getNextNeighbor(head, head);
            while (next != -1) {
                if (!isVisited[next]) {
                    System.out.print(vertexList.get(next) + " ");
                    isVisited[next] = true;
                    queue.add(next);
                }
                next = getNextNeighbor(head, next);
            }
        }
    }

    /**
     * 重载广度优先遍历
     */
    public void bfs() {
        boolean[] isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(i, isVisited);
            }
        }
    }
}
