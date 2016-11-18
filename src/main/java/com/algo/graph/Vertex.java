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
    public int st; // DFS start time
    public int et; // DFS end time
    public int cc =0; // connected component. Refer 22.3-12

    public Vertex(int i, E d) {
        index = i;
        data = d;
    }

    @Override
    public String toString() {

        return data.toString();//+ "["+ st+"-"+et+"]";
    }
}
