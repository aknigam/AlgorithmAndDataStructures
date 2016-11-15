package com.algo.util;

/**
 * Created by a.nigam on 14/11/16.
 */
public class GraphUtils {


    public static String printGraphMatrix(int[][] a) {
//        System.out.println(a.length);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                sb.append(a[i][j]).append("\t");
//                sb1.append("a["+i+"]["+j+"]").append("\t");
            }
            sb.append("\n");
            sb1.append("\n");
        }
//        System.out.println(sb);
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


}
