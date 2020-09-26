package com.algo.sorting.linear;


import com.algo.sorting.AbstractBaseSort;

/*
http://quiz.geeksforgeeks.org/selection-sort/
The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order)
from unsorted part and putting it at the beginning. The algorithm maintains two sub-arrays in a given array.

1) The subarray which is already sorted.
2) Remaining subarray which is unsorted.

In every iteration of selection sort, the minimum element (considering ascending order) from the unsorted subarray is
picked and moved to the sorted subarray.

In every loop the smallest one moves to the start of the sub-array and it changes the order of the rest of the elements.

Time Complexity: O(n*n) as there are two nested loops.

Auxiliary Space: O(1)


Best case
Average case
Worst case

 */
public class SelectionSort extends AbstractBaseSort {


    protected void sort(int[] arr) {

        if(arr.length==0 || arr.length ==1){
            return;
        }

        for (int i = 0; i < arr.length-1; i++) {

            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]>arr[j]){
                    swap(arr, i, j);
                }
            }

            printArray(arr);
        }

    }




}
