package com.algo.graph;

import com.algo.util.GraphUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by a.nigam on 15/11/16.
 */
public class AdjacencyListGraph extends Graph{

    LinkedList<Integer>[] g;
    public AdjacencyListGraph(int noOfvertices){
        super(noOfvertices);
        g = new LinkedList[noOfvertices];

        for (int i = 0; i < noOfvertices; i++) {
            g[i] = new LinkedList<Integer>();
        }

    }

    @Override
    public void addEdge(int sourceVertex, int destVertex) {
        if(GraphUtils.isValidVertex(V, sourceVertex) && GraphUtils.isValidVertex(V, destVertex)) {
            g[sourceVertex].add(destVertex);
            g[destVertex].add(sourceVertex);
        }
    }

    @Override
    public void addEdge(int sourceVertex, int destVertex, int weight) {
        if(GraphUtils.isValidVertex(V, sourceVertex) && GraphUtils.isValidVertex(V, destVertex)) {
            g[sourceVertex].add(destVertex);
        }
    }

    @Override
    protected List<Integer> getAdjacentvertices(int v) {
        return g[v];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            sb.append(i).append("-->").append(g[i]).append("\n");
        }
        return sb.toString();
    }
}
