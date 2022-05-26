package com.algo.dynamicprogramming;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a.nigam on 26/11/16.
 */
public class MaximumSubArray {

    public static void main(String[] args) {


        LocalDateTime localDateTime = LocalDateTime.now();

        // following array used for max sum sub array problem
//        int[] a = new int[]{13, -3, -25, 20 , -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
//        int[] a = {13, 3, -25, 1, 4, 120 , -500, 1, 5, 56};

        System.out.println( findSubArrayWithMaxSum(new int[]{-4, -5, -5, -3}) == -3);
        System.out.println(findSubArrayWithMaxSum(new int[]{-1, -5, -4}) == -1);
        System.out.println(findSubArrayWithMaxSum(new int[]{6, -5, 4}) == 6);
        System.out.println(findSubArrayWithMaxSum(new int[]{6, -5, 5}) == 6);
        System.out.println(findSubArrayWithMaxSum(new int[]{6, -5, 6}) == 7);
        System.out.println(findSubArrayWithMaxSum(new int[]{6, -7, 8}) == 8);
        System.out.println(findSubArrayWithMaxSum(new int[]{16, -17, 8, 5, 6, 4, -9, 10}) == 24);
        System.out.println(findSubArrayWithMaxSum(new int[]{16, -17, 8, 5, 6, 4, -90, 10}) == 23);


        int[] a = {1,2,3,4,5,5,6,6,7,8,9,9,10,11,12,13,14,15,16};
        Map<Integer, Integer> x = findPairWithSum1(a, 10);
        System.out.println(x);

    }

    private static long findSubArrayWithMaxSum(int[] arr) {

        if(arr.length == 0) {
            return Integer.MIN_VALUE;
        }

        if(arr.length == 1) {
            return arr[0];
        }
        long maxSum= arr[0];
        int runningSum= arr[0];

        for (int i = 1; i < arr.length; i++) {

            int num = arr[i];

            int nrs = runningSum + num;
            // this handles both the cases when running sum is (-)ve or (+)ve
            if(num > 0) {
                if(runningSum >= 0) {
                    runningSum = runningSum + num;
                }
                else {
                    runningSum = num;
                }
            }

            if(num < 0) {
                if(runningSum < 0 && num > runningSum) {
                    // this condition handles the case when running sum is negative
                    runningSum = num;
                }
                else if(runningSum + num > 0) {
                    runningSum = runningSum + num;
                }
                else {
                    // this is barrier of the max so resetting the running sum
                    runningSum = Integer.MIN_VALUE/2;
                }
            }

            if(maxSum < runningSum) {
                maxSum = runningSum;
            }

        }

        return maxSum;

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


}
