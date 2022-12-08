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
        findPairWithSum1(new int[]{4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4}, 2);


    }


    public static int findPairWithSum1(int[] a, int sum){
        Map<Integer, Stack<Integer>> valuePositionMap = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            if(valuePositionMap.containsKey(a[i])) {
                valuePositionMap.get(a[i]).push(i);
            }
            else {
                Stack<Integer> s = new Stack<>();
                s.push(i);
                valuePositionMap.put(a[i], s);
            }

        }

        int ops = 0;


        for (Map.Entry<Integer, Stack<Integer>> entry: valuePositionMap.entrySet()) {


            Stack<Integer> s1 = entry.getValue();
            if(s1.isEmpty()) {
                continue;
            }
            Integer v1 = entry.getKey();

            Integer v2 = sum - v1;
            if(v2 < 0) {
                continue;
            }
            // stacks may be same. so it is required to remove pop and then check for emptiness again
            while (!s1.isEmpty()) {
                Integer p1 = s1.pop();

                Stack<Integer> s2 = valuePositionMap.get(v2);
                if (s2 == null || s2.isEmpty()) {
                    continue;
                }
                Integer p2 = s2.pop();
                ops++;
                System.out.println(a[p1] + "," + a[p2] + "   - [" + p1 + "," + p2 + "]");
            }



        }

        System.out.println(ops);
        return ops;

    }
}
