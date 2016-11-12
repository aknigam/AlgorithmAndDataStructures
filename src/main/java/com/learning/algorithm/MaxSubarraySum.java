package com.learning.algorithm;

/**
 * Created by a.nigam on 06/11/16.
 */
public class MaxSubarraySum {


    public static void main(String[] args) {
        int[] a =  {-2, -3, 4, -1, -2, 1,5, -3};

        printmaxSumAndArray(a);
    }

    private static void printmaxSumAndArray(int[] a) {


        int max = 0;
        int r = 0;
        int ac = 0;

        int start = -1;
        int end = -1;

        for (int i = 0; i < a.length; i++) {


            r= max + a[i];
            if(r <= 0 && start >-1){


            }
            if(r > max ){
                max =  ac;
                if(start == -1){
                    start =  i;
                }
                else{
                    end =  i;
                }
            }

        }

    }
}
