package com.algo.util;

/**
 * Created by a.nigam on 14/11/16.
 */
public class ArrayUtils {

    public static void initArray(int[] a, int i) {
        for (int j = 0; j < a.length; j++) {
            a[j] = i;
        }
    }

    public static void print(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]).append("\t");
        }
        System.out.println(sb);
    }
}
