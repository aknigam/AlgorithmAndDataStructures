package com.algo.sorting;

/**
 * Created by a.nigam on 20/10/16.
 */
public class SortingRunner {

    public static void main(String[] args) {
        AbstractBaseSort sort = getSortingImpl();
//        int arr[] =  {178, 1900, 5, 6, 3, 7, 8, 3 ,5};
        int arr[] =  {9,8,7,6,5,4,3,2,1,0};


        System.out.println("Original array");
        sort.printArray(arr);

        sort.sort(arr);

        System.out.println("Sorted array");
        sort.printArray(arr);


    }

    private static AbstractBaseSort getSortingImpl() {
//        return new SelectionSort();
        return new MergeSortWiki();
    }
}
