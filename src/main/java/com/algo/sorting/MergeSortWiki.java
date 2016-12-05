package com.algo.sorting;

import java.util.Arrays;

/**
 * Created by a.nigam on 20/10/16.
 */
public class MergeSortWiki extends AbstractBaseSort {


    private boolean debug = true;

    @Override
    protected void sort(int[] arr) {

        bottomUpMergeSort(arr);


    }

    private void bottomUpMergeSort(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);

        bottomUpMergeSortinternal(a, b , 0, a.length -1, 0);
    }

    private void bottomUpMergeSortinternal(int[] a, int[] b, int iBegin, int iEnd, int level) {


        int n = a.length;
        for (int width = 1; width < n; width = width * 2) {
            for (int j = 0; j < n; j = j + 2 * width) {
                bottomUpMerge(a, j, Math.min(j + width, n), Math.min (j + 2 * width, n), b);
            }
            System.out.println(Arrays.toString(a)+"\t\t"+ Arrays.toString(a));
            copyArray(b, a , n);
        }


    }

    private void copyArray(int[] b, int[] a, int n) {
        for (int j = 0; j < n; j++) {
            a[j] = b[j];
        }
    }

    private void bottomUpMerge(int[] a, int iLeft, int iRight, int iEnd, int[] b) {
        int i = iLeft, j = iRight;
        // While there are elements in the left or right runs...
        for (int k = iLeft; k < iEnd; k++) {
            // If left run head exists and is <= existing right run head.
            if (i < iRight && (j >= iEnd || a[i] <= a[j])) {
                b[k] = a[i];
                i = i + 1;
            } else {
                b[k] = b[j];
                j = j + 1;
            }
        }
    }


    private void topDownMergeSort(int[] a) {

        int[] b = Arrays.copyOf(a, a.length);
        b[a.length -1] = 1;
        topDownMergeSortInternal(b, a, 0, a.length -2 , 0);



    }

    private void topDownMergeSortInternal(int[] b, int[] a, int s, int e, int level) {
        String tabs = getTabs(level);
        if(debug) {
            String bs = Arrays.toString(b);
            String as = Arrays.toString(a);
            String line= null;
            if(a[a.length -1] == 0) {
                line = String.format("T-1-Begin: %d \t End: %d ..>\tA  \t\t B\t ", s, e);
//                line = String.format("T-1-Begin: %d \t End: %d ..>\tA %s \t\t B\t %s", s, e, as, bs);
            }else {
                line = String.format("T-1-Begin: %d \t End: %d ..>\tB  \t\t A\t ", s, e);
//                line = String.format("T-1-Begin: %d \t End: %d ..>\tB %s \t\t A\t %s", s, e, as, bs);
            }

            System.out.println(level+tabs+line);
        }

        if( (e - s +1) <2 ){
            return;
        }

        int mid = (s + e)/2;

        topDownMergeSortInternal(a, b, mid+1, e, level+1);
        topDownMergeSortInternal(a, b, s, mid, level+1);

        mergeTopDown(b, a , s, e, mid, level+1);


        if(debug) {
            String bs = Arrays.toString(b);
            String as = Arrays.toString(a);
            String line= null;
            if(a[a.length -1] == 0) {
                line = String.format("T-1-Begin: %d \t End: %d ..>\tA  \t\t B\t ", s, e);
//                line = String.format("T-1-Begin: %d \t End: %d ..>\tA %s \t\t B\t %s", s, e, as, bs);
            }else {
                line = String.format("T-1-Begin: %d \t End: %d ..>\tB  \t\t A\t ", s, e);
//                line = String.format("T-1-Begin: %d \t End: %d ..>\tB %s \t\t A\t %s", s, e, as, bs);
            }

            System.out.println(level+tabs+line);
        }
        System.out.println();


    }

    private void mergeTopDown(int[] a, int[] b, int iBegin, int iEnd, int iMiddle, int level) {
        String tabs = getTabs(level);
        if(debug) {
            String bs = Arrays.toString(b);
            String as = Arrays.toString(a);
            String line = null;
            if(a[a.length -1] == 0) {
                line = String.format("B-1-Begin: %d \t End: %d ..>\tB  \t\t A\t ", iBegin, iEnd);
//                line = String.format("B-1-Begin: %d \t End: %d ..>\tB %s \t\t A\t %s", iBegin, iEnd, as, bs);
            }else {
                line = String.format("B-1-Begin: %d \t End: %d ..>\tA  \t\t B", iBegin, iEnd);
//                line = String.format("B-1-Begin: %d \t End: %d ..>\tA %s \t\t B\t %s", iBegin, iEnd, as, bs);
            }

            System.out.println(level+tabs+line);
        }


        int i = iBegin;
        int j = iMiddle + 1;
        for (int k = iBegin; k <= iEnd ; k++) {

            if((i <= iMiddle) && ((j > iEnd) || a[i] <= a[j])){
                b[k] = a[i];
                i++;
            }
            else{
                b[k] = a[j];
                j++;
            }

        }
        if(debug) {
            String bs = Arrays.toString(b);
            String as = Arrays.toString(a);
            String line = null;
            if(a[a.length -1] == 0) {
                line = String.format("B-1-Begin: %d \t End: %d ..>\tB  \t\t A\t ", iBegin, iEnd);
//                line = String.format("B-1-Begin: %d \t End: %d ..>\tB %s \t\t A\t %s", iBegin, iEnd, as, bs);
            }else {
                line = String.format("B-1-Begin: %d \t End: %d ..>\tA  \t\t B", iBegin, iEnd);
//                line = String.format("B-1-Begin: %d \t End: %d ..>\tA %s \t\t B\t %s", iBegin, iEnd, as, bs);
            }
            System.out.println(level+tabs+line);
        }







    }

    private String getTabs(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }







}
