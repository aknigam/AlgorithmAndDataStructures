package com.algo.leetcode;


import java.util.*;

/**
 * Refer: https://leetcode.com/problems/max-number-of-k-sum-pairs/
 */
public class MaxNumberofKSumPairs {

    public static void main(String[] args) {

//        findPairWithSum1(new int[]{3,5,1,5}, 2);
//        findPairWithSum1(new int[]{2,2,2,3,1,1,4,1}, 4);
//        findPairWithSum1(new int[]{3,1,3,4,3}, 6);
//        findPairWithSum1(new int[]{1, 2, 3,4}, 5);
//        findPairWithSum1(new int[]{4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4}, 2);
        findPairWithSum1(new int[]{5000000,5000000,5000000,5000000,5000000}, 10000000);


    }


    public static int findPairWithSum1(int[] a, int sum){

        Map<Integer, Integer> valuePositionMap = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            if(valuePositionMap.containsKey(a[i])) {
                valuePositionMap.put(a[i],valuePositionMap.get(a[i])+1);
            }
            else {
                valuePositionMap.put(a[i], 1);
            }

        }

        int ops = 0;


        for (Map.Entry<Integer, Integer> entry: valuePositionMap.entrySet()) {

            int v1 = entry.getKey();
            int s1 = entry.getValue();
            if(s1 == 0) {
                continue;
            }

            int v2 = sum - v1;
            if(v2 < 0) {
                continue;
            }

            while(s1 > 0) {

                if (v1 == v2) {
                    if(s1 < 2) {
                        break;
                    }
                    s1 = s1-2;
                    valuePositionMap.put(v1, s1 );
                    ops++;

                }
                else {
                    if(!valuePositionMap.containsKey(v2)) {
                        break;
                    }
                    int s2 = valuePositionMap.get(v2);
                    if (s1 > 0 && s2 > 0) {
                        ops++; s1= s1-1; s2= s2-1;
                        valuePositionMap.put(v1, s1 );
                        valuePositionMap.put(v2, s2 );
                    }
                    else {
                        break;
                    }
                }
            }

        }

        System.out.println(ops);
        return ops;

    }
}
