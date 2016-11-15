package com.algo.graph;


import com.algo.util.ArrayUtils;
import com.algo.util.GraphUtils;

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

    public abstract void addUndirectedEdge(int sourceVertex , int destVertex);

    public abstract void addDirectedEdge(int sourceVertex , int destVertex);

    public abstract void addUndirectedEdge(int sourceVertex, int destVertex, int weight);

    public abstract void addDirectedEdge(int sourceVertex, int destVertex, int weight);

    /**
    First index in the 2-D array represents the row
     */

    public void BFS(int s){

        if(!GraphUtils.isValidVertex(V, s)){
            return;
        }
        int[] previous = new int[V];
        ArrayUtils.initArray(previous , 0);

        int[] distance = new int[V];
        ArrayUtils.initArray(distance , Integer.MAX_VALUE);

        int[] color = new int[V];
        ArrayUtils.initArray(color , -1); // 0 == W, 1 == G, 2 == B

        LinkedList<Integer> q =  new LinkedList<Integer>();

        StringBuilder bfs = new StringBuilder();


        color[s] = 0;
        distance[s] = 0;
        q.add(s);

        while (!q.isEmpty()){
            int v = q.poll();

            color[v] = 1; // marked G
            bfs.append(v).append("\t");
            List<Integer> adj =  getAdjacentvertices(v);
            for (int i = 0; i < adj.size(); i++) {
                int u = adj.get(i);

                if(color[u] == -1) {
                    color[u] = 0;
                    distance[u] = distance[v]+1;
                    previous[u] = v;
                    q.add(u);
                }
            }
            color[v] = 2; // marked B
        }


        ArrayUtils.print(color);
        ArrayUtils.print(distance);
        ArrayUtils.print(previous);
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


//        Graph g = new AdjacencyListGraph(5);
        Graph g = new AdjacencyMatrixGraph(5);

        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(1, 2);
//        g.addUndirectedEdge(2, 0);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(3, 4);
//        g.addUndirectedEdge(2, 4);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(1, 3);
        System.out.println(g);

        g.BFS(2);

    }

}
