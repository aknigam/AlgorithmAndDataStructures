package com.algo.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a.nigam on 26/11/16.
 */
public class MaximumSubArray {

    public static void main(String[] args) {

        /*
        // following array used for max sum sub array problem
        int[] a = new int[]{13, -3, -25, 20 , -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        */
        int[] a = {1,2,3,4,5,5,6,6,7,8,9,9,10,11,12,13,14,15,16};
        Map<Integer, Integer> x = findPairWithSum1(a, 10);
        System.out.println(x);
    }

    public static Map<Integer, Integer> findPairWithSum1(int[] a, int sum){
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }

        int p;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            p = sum - a[i];
            if(map.get(p) != null){
                m.put(a[i], a[map.get(p)]);
            }
        }

        return m;

    }
    public static Map<Integer, Integer> findPairWithSum(int[] a, int sum){
        Arrays.sort(a);
        Map<Integer, Integer> m = new HashMap<>();
        if(a[0] > sum){
            return m;
        }

        int len = a.length;

        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < len; j++) {
             if((a[j] + a[i]) == sum){
                 m.put(a[i], a[j]);
                 len = j;
             }
            }

        }
        return m;
    }

    private static int findSubArrayWithMaxSum(int[] a, int s, int e) {

        int sum= 0 ;
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            int x = a[i];
            sum = sum +  x;

            if(sum > maxSum){
                maxSum = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }

        return maxSum;

    }
}
