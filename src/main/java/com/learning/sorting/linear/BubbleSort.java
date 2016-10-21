package com.learning.sorting.linear;

import com.learning.sorting.AbstractBaseSort;

/**
 Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.

 In every loop the biggest one moves to the end without affecting the order of the rest of the elements

 Worst and Average Case Time Complexity: O(n*n). Worst case occurs when array is reverse sorted.

 Best Case Time Complexity: O(n). Best case occurs when array is already sorted.

 Auxiliary Space: O(1)

 Boundary Cases: Bubble sort takes minimum time (Order of n) when elements are already sorted.

 Sorting In Place: Yes

 Stable: Yes

 */
public class BubbleSort  extends AbstractBaseSort {

    @Override
    protected void sort(int[] arr) {
        int l = arr.length;

        if(l == 0 || l == 1){
            return;
        }

        int end  =  l -1;
        boolean swapRequired =  true;

        for (int i = l-1; i > 0; i--) {

            if(!swapRequired && i < (l-1))
                break;
            for (int j = 0; j < i; j++) {

                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    swapRequired = true;
                }
            }

            printArray(arr);

        }


    }
}
