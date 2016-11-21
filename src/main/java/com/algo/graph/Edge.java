package com.algo.graph;

import java.util.Comparator;

/**
 * Created by a.nigam on 18/11/16.
 */
public class Edge<E> implements Comparable<Edge<E>>{
    Vertex<E>  src;
    Vertex<E>  dest;
    int weight;


    public Edge(Vertex<E> s, Vertex<E> d, int w) {
        src= s;
        dest = d;
        weight = w;
    }

    @Override
    public int compareTo(Edge<E> o) {
        if( weight>o.weight){
             return 1;
        }
        else if( weight<o.weight){
            return -1;
        }
        else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return dest.data+"("+weight+") ";
    }
}
