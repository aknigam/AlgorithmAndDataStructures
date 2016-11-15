package com.algo.sorting;

/**
 * Created by a.nigam on 20/10/16.
 */
public class MergeSort extends AbstractBaseSort {

    private int[] sortedArray;

    @Override
    protected void sort(int[] arr) {

        int len = arr.length;
        sortedArray = new int[len];
        initSortedArray();
        sortpartial(arr, 0, len-1);
        System.out.print("SA: ");
        printArray(sortedArray);

    }

    private void initSortedArray() {
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = 0;
        }
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
