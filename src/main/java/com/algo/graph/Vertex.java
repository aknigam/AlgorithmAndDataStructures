package com.algo.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by a.nigam on 16/11/16.
 */
public class Vertex<E> implements Comparable<Vertex<E>>{


    Map<Vertex<E>, Integer> weight = new HashMap<>();

    int index;
    public E data;
    public int color; // 0 == W, 1 == G, 2 == B
    public int d = Integer.MIN_VALUE;// distance
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

        return data.toString()+"("+(p==null ? "" : p.data)+"-"+d+")";//+ "["+ st+"-"+et+"]";
    }

    @Override
    public int compareTo(Vertex<E> o) {

        return this.d > o.d?0:1;
    }

}
