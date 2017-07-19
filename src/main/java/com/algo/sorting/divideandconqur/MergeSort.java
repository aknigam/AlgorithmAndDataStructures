package com.algo.sorting.divideandconqur;

import com.algo.sorting.AbstractBaseSort;

import java.util.Arrays;

/**
 * Created by a.nigam on 20/10/16.
 */
public class MergeSort extends AbstractBaseSort {

    private int[] sortedArray;

    private int counter = 1;
    private boolean flag = false;

    @Override
    protected void sort(int[] arr) {

        int len = arr.length;
        sortedArray = new int[len];
        initSortedArray(arr);
        sp(arr, sortedArray, 0, len-1);
        System.out.print("SA: ");
//        printArray(sortedArray);
//        printArray(arr);

    }

    private void sp(int[] source, int[] target, int startIndex, int endIndex) {
        if(startIndex < endIndex){
            int mid = startIndex + (endIndex-startIndex)/2;

            sp(source, target, startIndex, mid);
            sp(source,target, mid+1, endIndex);

            merge2(source, target, startIndex, endIndex, mid);

        }
    }

    private void merge2(int[] s, int[] t, int startIndex, int endIndex, int midIndex) {
        int i = startIndex;
        int j = midIndex+1;

        int l =  midIndex - startIndex + 1;
        int r = endIndex - midIndex;
        int k = startIndex;
        int target[] = t;
        int source[] = s;

        if(counter%2 == 0)
        {
            target = s;
            source = t;


        }
        counter++;
        System.out.print("Sorted array: \t");
        printArray(source);

        while (i <= midIndex && j <= endIndex)
        {
            if(source[i] < source[j]) {
                target[k] = source[i];
                i++;
            }
            else {
                target[k] = source[j];
                j++;
            }
            k++;


        }
        if(k <= endIndex)
        {
            if(i <= midIndex){
                for (; i <= midIndex && k <= endIndex; i++) {
                    target[k++] = source[i];
                }
            }
            else
            {
                for (; j <= endIndex && k <= endIndex; i++) {
                    target[k++] = source[j];
                }
            }
        }


    }

    private void initSortedArray(int[] arr) {
        sortedArray = Arrays.copyOf(arr, arr.length);
    }




    /**
     *
     * OLD IMPLEMENTATION
     *
     */

    private int[] sortpartial(int[] arr, int startIndex, int endIndex) {

        printIndex(startIndex, endIndex);
        if(startIndex < endIndex){
            int mid = startIndex + (endIndex-startIndex)/2;

            sortpartial(arr, startIndex, mid);
            sortpartial(arr, mid+1, endIndex);

            merge(arr, startIndex, endIndex, mid);
            return sortedArray;
        }
        if(startIndex == endIndex){
            sortedArray[startIndex]= arr[startIndex];
        }

        return sortedArray;


    }

    private void initSortedArray() {
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = 0;
        }
    }


    private int[] merge1(int[] a, int startIndex, int endIndex, int midIndex) {


        int i = startIndex;
        int j = midIndex+1;

        int l =  midIndex - startIndex + 1;
        int r = endIndex - midIndex;
        int k = startIndex;
        int target[] = sortedArray;
        int source[] = a;
        if(!flag)
        {
            target = a;
            source = sortedArray;
            flag = true;

        }
        System.out.print("Sorted array: \t");
        printArray(source);
        flag = false;
        while (i <= midIndex && j <= endIndex)
        {
            if(source[i] < source[j]) {
                target[k] = source[i];
                i++;
            }
            else {
                target[k] = source[j];
                j++;
            }
            k++;


        }
        if(k <= endIndex)
        {
            if(i <= midIndex){
                for (; i <= midIndex && k <= endIndex; i++) {
                    target[k++] = source[i];
                }
            }
            else
            {
                for (; j <= endIndex && k <= endIndex; i++) {
                    target[k++] = source[j];
                }
            }
        }

//        printArray(a);
//        printArray(sortedArray);
        return target;
    }


    private int[] merge(int[] a, int startIndex, int endIndex, int midIndex) {


        System.out.println("merging");
        System.out.println( startIndex+"\t"+ midIndex+"\t"+ endIndex);
        System.out.println("Initial state");
        printArray(sortedArray);


        int[] ta = new int[endIndex - startIndex + 1];

        for (int i = 0; i < ta.length; i++) {
            ta[i] =  sortedArray[startIndex+i];
        }

        for (int i = 0,  j =(midIndex-startIndex)+1, currentIndex =  startIndex; currentIndex<=endIndex ;currentIndex++ ) {

            try {
                if (i > (midIndex-startIndex) && j > ta.length-1)
                    return sortedArray;

                if (i > (midIndex-startIndex)) {
                    // put remaining from b and continue
                    sortedArray[currentIndex] = ta[j++];
                    continue;
                }

                if (j > (ta.length-1)) {
                    // put remaining from a and continue
                    sortedArray[currentIndex] = ta[i++];
                    continue;
                }

                if (ta[i] < ta[j]) {
                    sortedArray[currentIndex] = ta[i++];
                } else {
                    sortedArray[currentIndex] = ta[j++];
                }


            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println( "Exception\t"+startIndex+"\t"+ midIndex+"\t"+ endIndex+"\t"+i+"\t"+j+"\t"+ ta.length);
            }
        }

        printArray(sortedArray);
        return sortedArray;
    }


    private void printIndex(int startIndex, int endIndex) {
        System.out.println("Sort partial");
        int len  = sortedArray.length;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if(i == startIndex){
                s.append(startIndex).append("\t");
            }
            else if(i == endIndex){
                s.append(endIndex).append("\t");
            }
            else{
                s.append("-").append("\t");
            }
        }
        System.out.println(s);

    }


}
