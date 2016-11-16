package com.algo.sorting.linear;

import com.algo.sorting.AbstractBaseSort;

/**
 *
 * Time Complexity: O(n*n)

 Auxiliary Space: O(1)

 Boundary Cases: Insertion sort takes maximum st to sort if elements are sorted in reverse order. And it takes minimum
 st (Order of n) when elements are already sorted.

 Algorithmic Paradigm: Incremental Approach

 Sorting In Place: Yes

 Stable: Yes

 Online: Yes

 Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost sorted,
 only few elements are misplaced in complete big array.

 */
public class InsertionSort extends AbstractBaseSort {


    @Override
    protected void sort(int[] arr) {
        int l = arr.length;
        if( l<2){
            return;
        }

        for (int i = 0; i < l-1; i++) {

            System.out.print("Before Iter : "+i+"\t");
            printArray(arr);

            if( arr[i] > arr[i+1] ){

                if(i == 0){
                    swap(arr,i, i+1);
                    continue;
                }
                int temp =  arr[i+1];
                arr[i+1] = arr[i];
                int j = i-1;
                for (; j >= 0; j--) {

                    if( arr[j] > temp ){
                        arr[j+1] = arr[j];
                    }
                    else{
                        break;
                    }
                }

                arr[j+1] = temp;

                
            }

        }

        printArray(arr);
    }
}
