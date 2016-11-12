package com.learning.sorting;

/**
 * http://www.geeksforgeeks.org/counting-sort/
 *
 *
 *  {13,12, 11, 5, 6, 3, 7, 65, 8, 3 ,5};
 *
 Time Complexity: O(n+k) where n is the number of elements in input array and k is the range of input.
 Auxiliary Space: O(n+k)

 What if the elements are in range from 1 to n2?
 *
 */
public class CountingSort extends AbstractBaseSort{
    @Override
    protected void sort(int[] arr) {

        countSort(arr, 10);



    }

    private void countSort(int[] arr, int maxVal) {

        int len = arr.length;
        if(len ==  0 || len == 1){
            return;
        }

        // assume fix range of 1 to 10

        // initialise count array
        int[] count = new int[10];
        for (int i =0; i < 10; i++)
            count[i] = 0;


        // count the keys
        for(int i =0; i < len; i++)
        {
            count[arr[i]] = count[arr[i]]+1;
        }
        printArray(count);

        // calcualte prior sum
        for (int i = 1; i < 10; i++) {
            count[i] = count[i]+ count[i-1];
        }
        printArray(count);
        int[] output = new int[len];

        for(int i =0; i < len; i++)
        {
            output[i] = 0;
        }

        for(int i =0; i < len; i++)
        {
            int index = count[arr[i]]--;

            output[index-1] = arr[i];

        }
        System.out.print("Count sorted array: ");
        printArray(output);
    }
}
