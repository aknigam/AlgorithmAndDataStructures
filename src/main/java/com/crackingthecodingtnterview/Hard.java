package com.crackingthecodingtnterview;

import com.algo.util.ArrayUtils;

import java.util.Arrays;

/**
 * Created by a.nigam on 26/11/16.
 */
public class Hard {

    private static boolean debug = false;

    public static void main(String[] args) {
        int[] a=new int[]{2,3,5,6,7,12,67,45, 9, 8};
        System.out.println(Arrays.toString(a));

        StringBuilder sb = new StringBuilder();
        int x ;
        for (int i = 0; i < a.length; i++) {
            x = selectionRankAlgorithm(a, a.length -i, 0, a.length -1);
            System.out.println((a.length -i )+"th largest\t"+ x);
        }
        System.out.println(sb);
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void largestSumSubMatrix(int[][] a){



    }

    public static int selectionRankAlgorithm(int[] a, int i, int s, int e){

        int len = e -s +1;
        if(len == 0){
            return -1;
        }
        if(i > len){
            return -1;
        }
        if(len ==1 && i ==1){
            return a[s];
        }

        int m = (s+ e)/2;
        int midVal = a[m];

        // this swap is required to make sure that the element at the boundary > midVal
        ArrayUtils.swap(a, m, e);

        int j ,k;

        for ( j = s, k = e -1 ; j <= k;) {
            if(debug)
                System.out.println(j +"["+a[j]+"] -"+midVal+"- " + k+"["+a[k]+"]");

            if(a[j] <= midVal){
                j++;
                continue;
            }
            if(a[k] > midVal ){
                k--;
                continue;
            }
            if(j < k) {
                ArrayUtils.swap(a, j, k);
                j++;
                k--;
            }
        }
        if(debug){
            System.out.println(midVal + "\t"+s + "\t("+ j +"["+a[j]+"]\t" + k+"["+a[k]+"]" +")\t"+ e + "\t: "+ Arrays.toString(a));
        }
        if(j == k){
            if(a[j] > midVal){
                j--;
            }
            else{
                k++;
            }
        }
        else{
            j--;k++;
        }
        ArrayUtils.swap(a, k, e);
        if(debug){
            System.out.println(midVal + "\t"+s + "\t("+ j +"["+a[j]+"]\t" + k+"["+a[k]+"]" +")\t"+ e + "\t: "+ Arrays.toString(a));
        }

        int rightLength = e  - k ;
        /**
         * if index of k is same as i then return k
         */

        int kIndex = rightLength + 1;

        if(kIndex == i){
            return a[k];
        }

        if(rightLength ==  i){
            if(debug)
                System.out.println("Finding smallest in "+ (k+1)+"-"+ e);
            return findSmallest(a, k+1, e);
        }
        else if(rightLength >  i){
            if(debug){
                System.out.println("Recursion right side "+ (k+ 1)+"-"+ e);
            }
            return selectionRankAlgorithm(a, i, k, e);
        }
        else{
            if(debug){
                System.out.println("Recursion left side "+ (k+1)+"-"+ e);
            }
            return selectionRankAlgorithm(a, (i - rightLength-1), s , j);
        }



    }

    private static int findSmallest(int[] a, int s, int e) {

        int min = a[s];
        for (int j = s; j <=  e; j++) {
            if(min > a[j]){
                min = a[j];
            }
        }

        return min;
    }
}
