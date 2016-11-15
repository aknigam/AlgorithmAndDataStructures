package com.algo.graph;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by a.nigam on 14/11/16.
 */
public abstract class Graph {

    final int V; // no of vertices

    public Graph(int noOfvertices) {
        V = noOfvertices;
    }

    public abstract void addEdge(int sourceVertex , int destVertex);

    public abstract void addEdge(int sourceVertex, int destVertex, int weight);

    /**
    First index in the 2-D array represents the row
     */

    public void BFS(int s){

        if(!GraphUtils.isValidVertex(V, s)){
            return;
        }
        int[] visited = new int[V];
        GraphUtils.initArray(visited , 0);

        int[] distance = new int[V];
        GraphUtils.initArray(distance , Integer.MAX_VALUE);

        int[] color = new int[V];
        GraphUtils.initArray(color , -1); // 0 == W, 1 == G, 2 == B

        LinkedList<Integer> q =  new LinkedList<Integer>();

        StringBuilder bfs = new StringBuilder();


        color[s] = 0;
        distance[s] = 0;
        q.add(s);

        while (!q.isEmpty()){
            int v = q.poll();
            visited[v] = 1;
            color[v] = 1; // marked G
            bfs.append(v).append("\t");
            List<Integer> adj =  getAdjacentvertices(v);
            for (int i = 0; i < adj.size(); i++) {
                int u = adj.get(i);

                if(visited[u] == 0) {
                    q.add(u);
                }
            }
            color[v] = 2; // marked B
        }

        System.out.println(bfs);




    }

    protected abstract List<Integer> getAdjacentvertices(int v);


    static int[][] geTransposeEffecint(int[][] a){
        int[][] b = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {

        }
        return b;
    }

    int[][] geTranspose(int[][] a){
        int[][] b = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    public static void main(String[] args) {
        int size = 3;
        int[][] a = new int[][]{
                {0,1,0,1,0,0},
                {0,0,0,0,1,0},
                {0,0,0,0,1,1},
                {0,1,0,0,0,0},
                {0,0,0,1,0,0},
                {0,0,0,0,0,1}

        };


        Graph g = new AdjacencyListGraph(4);
//        Graph g = new AdjacencyMatrixGraph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println(g);

        g.BFS(2);

    }

}
