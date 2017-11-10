package com.algo.util;

import com.algo.graph.Vertex;

/**
 * Created by a.nigam on 14/11/16.
 */
public class ArrayUtils {

    public static void initArray(int[] a, int i) {
        for (int j = 0; j < a.length; j++) {
            a[j] = i;
        }
    }

    public static <T> void initArraygeneric(T[] a, T i) {
        for (int j = 0; j < a.length; j++) {
            a[j] = i;
        }
    }
    public static void print(Object[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]).append("\t");
        }
        System.out.println(sb);
    }

    public static <T> String printGeneric(T[] a) {
        return printGeneric(a, 0, a.length).toString();
    }
    public static <T> StringBuilder printGeneric(T[] a, int startIndex, int endIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            sb.append(a[i]).append("\t");
        }
//        System.out.println(sb);
        return sb;
    }

    public static void initArray(Vertex[] vlist, Vertex o) {
        for (int i = 0; i < vlist.length; i++) {
            vlist[i] = o;
        }
    }

    public static void print2DIntArray(int[][] a) {
        StringBuilder sb = new StringBuilder();
        for (int r = 1; r <= a.length; r++) {
            for (int c = 1; c <= a.length; c++) {
                sb.append(a[r-1][c-1]).append(" ");
            }
            sb.append("\n");

        }
        System.out.println(sb);
    }
    public static void print2DStringArray(String[][] a) {
        StringBuilder sb = new StringBuilder();
        for (int r = 1; r <= a.length; r++) {
            for (int c = 1; c <= a.length; c++) {
                sb.append(a[r-1][c-1]).append(" ");
            }
            sb.append("\n");

        }
        System.out.println(sb);
    }

    public static void swap2dArrayElements(String[][] a, int i, int j, int i1, int j1, boolean debug) {
        if(debug)
            System.out.println("Swapping "+i +","+j +") - ("+i1+","+j1+")");
        String temp = a[i][j];
        a[i][j] = a[i1][j1];
        a[i1][j1] = temp;
    }

    public static void initBoolArray(boolean[] row, boolean b) {
        for (int i = 0; i < row.length; i++) {
            row[i] = b;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp =arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void init2DArray(int[][] a) {

        for (int r = 0; r <= a.length -1; r++) {
            for (int c = 0; c <= a.length -1; c++) {
                a[r][c] = 0;
            }

        }

    }
}
