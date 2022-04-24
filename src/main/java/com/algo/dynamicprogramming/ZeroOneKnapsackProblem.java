package com.algo.dynamicprogramming;

import com.algo.util.ArrayUtils;

import java.util.Arrays;

/**
 * Created by a.nigam on 05/12/16.
 *
 * This is a question from ITA (Search - "Greedy versus dynamic programming")
 *
 * FractionalKnapsackProblem can be solved using greedy Algorithm. Find the value density (vi/wj) and sort in
 * descending order
 */
public class ZeroOneKnapsackProblem {

    private static boolean debug = false;

    public static void main(String[] args) {
        int[] w = new int[]{ 10, 20, 30 };
        int[] v = new int[]{ 60, 100, 120 };
        System.out.println(11/2);
//        int[] w = new int[]{ 1, 1, 1 };
//        int[] v = new int[]{ 10, 20, 30 };

        int wCap = 50;
        int maxVal = topDownFillKnapSack(w, v, wCap, 0);
        System.out.println(maxVal);
    }

    private static int topDownFillKnapSack(int[] w, int[] v, int wCap, int iBegin) {


        if(iBegin >= w.length){
            return 0;
        }
        int n =  w.length;
        int wi = 0;
        int vi = 0;

        for (int i = iBegin; i < n && wi <= wCap ; i++) {

            if(i != iBegin){
                ArrayUtils.swap(w, i, iBegin);
                ArrayUtils.swap(v, i, iBegin);
            }
            wi = w[iBegin];

            if(wi > wCap){
                continue;
            }
            if(debug) {
                System.out.println(iBegin + "\t" + wCap + "\t" + vi);
                System.out.println(Arrays.toString(w) + "\t\t" + Arrays.toString(v));
            }


            int vin = v[iBegin] + topDownFillKnapSack(w, v, wCap - w[iBegin], iBegin + 1);
            vi =  Math.max(vi ,  vin);

        }

        return vi;

    }

    public static int bottomUpFillKnapSack(int[] w, int[] v, int wCap, int iBegin){




        return -1;
    }


}
