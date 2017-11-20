package com.algo.util;

import com.algo.graph.Graph;
import com.algo.graph.Vertex;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by a.nigam on 14/11/16.
 */
public class GraphUtils {


    public static String printGraphMatrix(int[][] a) {

        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                sb.append(a[i][j]).append("\t");

            }
            sb.append("\n");
            sb1.append("\n");
        }

        return sb.toString();
    }

    public static void initGraph(int[][] a, int val) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[i][j] = val;
            }
        }
    }

    /**
     *
     * @param V no of vertices in the graph
     * @param vertex
     * @return
     */
    public static boolean isValidVertex(int V, int vertex) {
        return vertex>=0 && vertex < V;
    }


    public static <E> void  printColors(Graph<E> g) {
        System.out.println("Colors");
        StringBuilder sb = new StringBuilder();
        Iterator<Vertex<E>> itr = g.vertextIterator();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            sb.append(v.color).append("\t");
        }
        System.out.println(sb);

    }

    public static <E> void printDistance(Graph<E> g) {
        System.out.println("Distances");
        StringBuilder sb = new StringBuilder();
        Iterator<Vertex<E>> itr = g.vertextIterator();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            sb.append(v.distance +"-"+v.connectedComponents).append("\t");
        }
        System.out.println(sb);
    }

    public static <E> void printBFSPrevious(Graph<E> g) {
        System.out.println("Previous");
        StringBuilder sb = new StringBuilder();
        Iterator<Vertex<E>> itr = g.vertextIterator();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            sb.append(v.previous).append("\t");
        }
        System.out.println(sb);

    }

    public static <E> void printTimes(Graph<E> g) {
        System.out.println("Times");
        StringBuilder sb = new StringBuilder();
        Iterator<Vertex<E>> itr = g.vertextIterator();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            sb.append(v.data+"["+v.startTime +","+v.endTime +"]").append("\t");
        }
        System.out.println(sb);
    }

    public static <E> void printBraceNotation(Graph<E> g) {
        Vertex<E>[] vlist = g.vlist;
        Arrays.sort(vlist, new Comparator<Vertex<E>>() {
            public int compare(Vertex<E> o1, Vertex<E> o2) {
                return o1.startTime > o2.startTime ? 1:0;
            }
        });
        StringBuilder sb = new StringBuilder();

        String b = "(";
        for (int i = 0; i < vlist.length; i++) {

        }

        Arrays.sort(vlist, new Comparator<Vertex<E>>() {
            public int compare(Vertex<E> o1, Vertex<E> o2) {
                return o1.startTime < o2.startTime ? 1:0;
            }
        });
    }


    public static <E> void printCC(Graph<E> g) {

        System.out.println("Previous");
        StringBuilder sb = new StringBuilder();
        Iterator<Vertex<E>> itr = g.vertextIterator();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            sb.append(v.data+"["+v.startTime +","+v.endTime +"]").append("\t");
        }
        System.out.println(sb);

    }
}
