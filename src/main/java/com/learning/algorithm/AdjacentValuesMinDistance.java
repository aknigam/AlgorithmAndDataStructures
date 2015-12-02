package com.learning.algorithm;

import java.util.*;

/**
 * Created by a.nigam on 26/11/15.
 */
public class AdjacentValuesMinDistance {


    public static void main(String[] args) {
        AdjacentValuesMinDistance a2  = new AdjacentValuesMinDistance();
        // int A[] = new int[8];
        // A[0] = 0 ;A[1] = 3 ;A[2] = 3; A[3] = 7 ;A[4] = 3; A[5] = 3; A[6] = 11 ;A[7] = 1;

        int A[] = new int[1];
        A[0] = 42;
        System.out.println(a2.solution(A, 19));
    }

    public int solution(int A[], int N){


        Map<Integer, String> map = createMap(A);

        Map<Integer, String> sortedMap =  new TreeMap<Integer, String>(map);

        String n = null;
        String s =  null;
        int minDiff =  Integer.MAX_VALUE;

        for (Map.Entry<Integer, String> e : sortedMap.entrySet()) {

            s = n;
            n  = e.getValue();
            if(s == null){
                continue;
            }
            print(s, n);
            int newmindiff= minDiff(s, n);
            if(minDiff> newmindiff){
                minDiff =newmindiff;
            }

        }


        return minDiff;
    }
    private int minDiff(String s, String n) {
        int i = Integer.MAX_VALUE;
        String[] s1 = s.split(",");
        String[] n1 = n.split(",");

        for (String a : s1) {

            for (String b :n1) {

                System.out.println("["+a+","+ b+"] -  "+ Math.abs((Integer.valueOf(a)- Integer.valueOf(b))) );
                if(i > Math.abs((Integer.valueOf(a)- Integer.valueOf(b))))
                    i = Math.abs((Integer.valueOf(a)- Integer.valueOf(b)));
            }



        }
        return i;
    }

    private void print(String s, String n) {
        String[] s1 = s.split(",");
        String[] n1 = n.split(",");

        for (String a : s1) {

            for (String b :n1) {
                // System.out.println("["+a+","+ b+"]");
            }

        }
    }

    private Map<Integer, String> createMap(int[] a) {
        Map<Integer, String> map = new HashMap<Integer, String>();

        for (int i = 0; i < a.length; i++) {
            addToMap(map , a[i], i);
        }


        return map;
    }

    private void addToMap(Map<Integer, String> map, int key, int index) {
        String val = map.get(key);
        if(val == null){
            val = index+"";
            map.put(key, val);
        }
        else{

            val = val+","+index;
            map.put(key, val);

        }
    }
}
