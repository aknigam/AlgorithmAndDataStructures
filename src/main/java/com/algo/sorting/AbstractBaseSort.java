package com.algo.sorting;

/**
 * Created by a.nigam on 20/10/16.
 */
public abstract class AbstractBaseSort {


    protected static boolean debug = true;

    protected abstract void sort(int[] arr) ;

    protected void sortDecreasing(int[] arr){
        return;
    }

    protected void swap(int[] arr, int i, int j) {
        int temp =arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void printArray(int[] arr) {
        StringBuilder s =  new StringBuilder();
        for (int i : arr){
            s.append(i).append("  ");
        }
        System.out.println(s);
    }





}
