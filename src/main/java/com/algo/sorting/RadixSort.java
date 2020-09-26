package com.algo.sorting;

/**
 *
 */
public class RadixSort extends AbstractBaseSort{


    @Override
    protected void sort(int[] arr) {

        int len = arr.length;
        if(len ==  0 || len == 1){
            return;
        }
        int base = 10;
        int max = getMax(arr);
        for (int i = 1; max/i> 0; i*=base) {
            System.out.println("ROUND NO.: "+ i);
            sortBySignificantDigit(arr, i, base);
        }

    }

    private int getMax(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]> max){
                max = arr[i];
            }
        }
        return max;
    }

    private void sortBySignificantDigit(int[] arr, int exp, int base) {

        int len = arr.length;

        int[] count = new int[base];
        for (int i =0; i < base; i++)
            count[i] = 0;


        // count the keys
        for(int i =0; i < len; i++)
        {
            int digitAtindex = (arr[i]/exp)%base;
            count[digitAtindex] = count[digitAtindex]+1;
        }
        printArray(count);

        // calcualte prior sum
        for (int i = 1; i < base; i++) {
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
            int index = count[(arr[i]/exp)%base]--;

            output[index-1] = arr[i];

        }


        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (int i = 0; i < len; i++)
            arr[i] = output[i];
        printArray(arr);


    }
}
