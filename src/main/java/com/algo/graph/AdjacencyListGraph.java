package com.algo.graph;

import com.algo.util.GraphUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by a.nigam on 15/11/16.
 */
public class AdjacencyListGraph<E> extends Graph<E>{

    LinkedList<Vertex<E>>[] g;
    public AdjacencyListGraph(int noOfvertices){
        super(noOfvertices);
        g = new LinkedList[noOfvertices];

        for (int i = 0; i < noOfvertices; i++) {
            g[i] = new LinkedList<Vertex<E>>();
        }

    }

    @Override
    public void addUndirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex) {
        addDirectedEdge(sourceVertex, destVertex);
        addDirectedEdge(destVertex, sourceVertex);
    }

    @Override
    public void addDirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex) {
        if(GraphUtils.isValidVertex(V, sourceVertex.index) && GraphUtils.isValidVertex(V, destVertex.index)) {
            g[sourceVertex.index].add(destVertex);
            if(vlist[sourceVertex.index] ==  null){
                vlist[sourceVertex.index] = sourceVertex;
            }
            if(vlist[destVertex.index] ==  null){
                vlist[destVertex.index] = destVertex;
            }
        }
    }

    @Override
    public void addUndirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex, int weight) {
        addDirectedEdge(sourceVertex, destVertex, weight);
        addDirectedEdge(destVertex, sourceVertex, weight);
    }

    @Override
    public void addDirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex, int weight) {
        if(GraphUtils.isValidVertex(V, sourceVertex.index) && GraphUtils.isValidVertex(V, destVertex.index)) {
            g[sourceVertex.index].add(destVertex);
            if(vlist[sourceVertex.index] ==  null){
                vlist[sourceVertex.index] = sourceVertex;
            }
            if(vlist[destVertex.index] ==  null){
                vlist[destVertex.index] = destVertex;
            }
        }
    }

    @Override
    protected List<Vertex<E>> getAdjacentvertices(Vertex<E> v) {
        return g[v.index];
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