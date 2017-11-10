package com.algo.greedyalgorithm;

import java.util.Arrays;

/**
 * Created by a.nigam on 04/12/16.
 */
public class CoinChangeProblem {


    public static void main(String[] args) {

        System.out.println(110 % 25);
        int[] c = change(123);
        System.out.printf(Arrays.toString(c));
    }

    public static int[] change(int val) {
        int[] change = new int[]{0, 0,0,0};
        int remainingVal = val;
        while (remainingVal > 0) {

            if (remainingVal > 25) {
                change[0] = change[0] + remainingVal / 25;
                remainingVal = remainingVal % 25;
            }
            if (remainingVal > 10) {
                change[1] = change[1] + remainingVal / 10;
                remainingVal = remainingVal % 10;
            }
            if (remainingVal > 5) {
                change[2] = change[2] + remainingVal / 5;
                remainingVal = remainingVal % 5;
            }
            else {
                change[3] = remainingVal;
                remainingVal = 0;
            }

        }

        return change;
    }

    public static final Integer QUARTER = 25;
    public static final Integer DIME = 10;
    public static final Integer NICKEL = 5;
    public static final Integer PENNY = 1;

    public static int makeChange(int val, int demon){

        int nextDenom =  0;

        switch (demon) {
            case 25:
                nextDenom = DIME;
                break;
            case 10:
                nextDenom = NICKEL;
                break;
            case 5:
                nextDenom = PENNY;
                break;
            case 1:
                return 1;
        }



        int ways =  0;

        for (int i = 0; i* demon <= val ; i++) {
            ways = ways + makeChange(val - i * demon, nextDenom);
        }

        return ways;
    }




}


