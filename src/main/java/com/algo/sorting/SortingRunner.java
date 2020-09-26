package com.algo.sorting;

import com.algo.sorting.divideandconqur.MergeSort;
import com.algo.sorting.linear.InsertionSort;

/**
 * Created by a.nigam on 20/10/16.
 */
public class SortingRunner {

    public static void main(String[] args) {
        AbstractBaseSort sort = getSortingImpl();
//        int arr[] =  {1900, 178, 5, 6, 3};//, 7, 8, 3 ,5};
//        int arr[] =  {9,8,7,6,5,4,3,2,1,0};
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};


        System.out.println("Original array");
        sort.printArray(arr);

        sort.sort(arr);

        System.out.println("Sorted array");
        sort.printArray(arr);

    }

    private static AbstractBaseSort getSortingImpl() {
//        return new SelectionSort();
//        return new InsertionSort();
        return new RadixSort();
    }
}
