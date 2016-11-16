package com.algo.graph;

import com.algo.util.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 15/11/16.
 */
public class AdjacencyMatrixGraph<E> extends  Graph<E>{


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
    public void addUndirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex) {
        addDirectedEdge(sourceVertex,destVertex);
        addDirectedEdge(destVertex, sourceVertex);
    }

    @Override
    public void addDirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex) {
        if(GraphUtils.isValidVertex(V, sourceVertex.index) && GraphUtils.isValidVertex(V, destVertex.index)){
            g[sourceVertex.index][destVertex.index] = 1;
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
        if(GraphUtils.isValidVertex(V, sourceVertex.index) && GraphUtils.isValidVertex(V, destVertex.index)){
            g[sourceVertex.index][destVertex.index] = weight;
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
        List<Vertex<E>> l = new ArrayList<Vertex<E>>();
        for (int i = 0; i < V; i++) {
            if(g[v.index][i] != 0 ){
                l.add(vlist[i]);
            }
        }
        return l;
    }


    @Override
    public String toString() {
        return GraphUtils.printGraphMatrix(g);
    }
}
