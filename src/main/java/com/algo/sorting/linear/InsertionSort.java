package com.algo.sorting.linear;

import com.algo.sorting.AbstractBaseSort;
import com.algo.util.ArrayUtils;

/**
 *
 * Time Complexity: O(n*n)

 Auxiliary Space: O(1)

 Boundary Cases: Insertion sort takes maximum startTime to sort if elements are sorted in reverse order. And it takes minimum
 startTime (Order of n) when elements are already sorted.

 Algorithmic Paradigm: Incremental Approach

 Sorting In Place: Yes

 Stable: Yes

 Online: Yes

 Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost sorted,
 only few elements are misplaced in complete big array.

 http://quiz.geeksforgeeks.org/wp-content/uploads/2013/03/insertion-sort.png

 */
public class InsertionSort extends AbstractBaseSort {


    @Override
    protected void sort(int[] arr) {
        int l = arr.length;
        if (l < 2) {
            return;
        }


        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }

//        printArray(arr);
    }


    protected void sortDecreasing(int[] arr) {
        int l = arr.length;
        if (l < 2) {
            return;
        }


        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }

        printArray(arr);
    }
}
