package com.algo.graph;

import com.algo.util.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 15/11/16.
 */
public class AdjacencyMatrixGraph extends  Graph{


    int[][] g;

    public AdjacencyMatrixGraph(int noOfvertices){
        super(noOfvertices);
        g = new int[noOfvertices][noOfvertices];
        GraphUtils.initGraph(g, 0);

    }

    public AdjacencyMatrixGraph(int[][] sg){

        super(sg.length);
        g = sg;

    }

    @Override
    public void addUndirectedEdge(int sourceVertex, int destVertex) {
        addDirectedEdge(sourceVertex,destVertex);
        addDirectedEdge(destVertex, sourceVertex);
    }

    @Override
    public void addDirectedEdge(int sourceVertex, int destVertex) {
        if(GraphUtils.isValidVertex(V, sourceVertex) && GraphUtils.isValidVertex(V, destVertex)){
            g[sourceVertex][destVertex] = 1;
        }
    }

    @Override
    public void addUndirectedEdge(int sourceVertex, int destVertex, int weight) {
        addDirectedEdge(sourceVertex, destVertex, weight);
        addDirectedEdge(destVertex, sourceVertex, weight);

    }

    @Override
    public void addDirectedEdge(int sourceVertex, int destVertex, int weight) {
        if(GraphUtils.isValidVertex(V, sourceVertex) && GraphUtils.isValidVertex(V, destVertex)){
            g[sourceVertex][destVertex] = weight;
        }
    }

    @Override
    protected List<Integer> getAdjacentvertices(int v) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0; i < V; i++) {
            if(g[v][i] != 0 ){
                l.add(i);
            }
        }
        return l;
    }


    @Override
    public String toString() {
        return GraphUtils.printGraphMatrix(g);
    }
}
