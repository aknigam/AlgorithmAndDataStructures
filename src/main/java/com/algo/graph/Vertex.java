package com.algo.graph;

/**
 * Created by a.nigam on 16/11/16.
 */
public class Vertex<E> {

    int index;
    public E data;
    public int color; // 0 == W, 1 == G, 2 == B
    public int d;// distance
    public Vertex<E> p; // previous - used in BFS
    public int st;
    public int et;

    public Vertex(int i, E d) {
        index = i;
        data = d;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
