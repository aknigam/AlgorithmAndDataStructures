package com.learning.sorting;

/**
 * Like Merge Sort, QuickSort is a Divide and Conquer algorithm. It picks an element as pivot and partitions the given
 * array around the picked pivot. There are many different versions of quickSort that pick pivot in different ways.

 Always pick first element as pivot.
 Always pick last element as pivot (implemented below)
 Pick a random element as pivot.
 Pick median as pivot.

 The key process in quickSort is partition(). Target of partitions is, given an array and an element x of array as
 pivot, put x at its correct position in sorted array and put all smaller elements (smaller than x) before x, and
 put all greater elements (greater than x) after x. All this should be done in linear time.

 */
public class QuickSort extends AbstractBaseSort {


    @Override
    protected void sort(int[] arr) {
        int len = arr.length;

        if(len == 0 || len ==1){
            return;
        }


        quickSort(arr, 0, arr.length-1);
        System.out.println("Sorted array");
        printArray(arr);
    }

    private void quickSort(int[] arr, int startIndex, int endIndex) {


        System.out.print(startIndex+"\t"+ endIndex+"\t: -->");
        printArray(arr);

        if(startIndex>=endIndex){
            System.out.println("");
            return;
        }

        int pivot  = getPivot(arr, startIndex , endIndex);

        int pivotPosition = movePivotToCorrectPosition(arr, pivot, startIndex, endIndex);
        // sort left subarray
        if(arr[pivotPosition] == pivot){
            quickSort(arr, startIndex , pivotPosition - 1);
        }else {
            quickSort(arr, startIndex , pivotPosition);
        }

        quickSort(arr , pivotPosition + 1, endIndex);
        // sort right subarray


    }


    /*
    At any point elements before i are <= pivot
     */
    private int movePivotToCorrectPosition(int[] arr, int pivot, int low, int high) {

        int i = (low-1); // index of smaller element
        for (int j=low; j<=high-1; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
//            System.out.print(i+"\t"+j+"\t: --> ");
//            printArray(arr);
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;

    }

    private int getPivot(int[] arr, int s, int e) {
        return arr[e];
    }

    public static void main(String[] args) {
        int[] arr = {50,10, 50, 10, 50, 10,50,10, 50, 70, 30, 50, 45,50,10, 50, 10, 50, 30};
        QuickSort s = new QuickSort();
        s.printArray(arr);

        int pi = s.movePivotToCorrectPosition(arr, 42, 0, arr.length - 1);
        System.out.println(pi);
        s.printArray(arr);
    }
}
