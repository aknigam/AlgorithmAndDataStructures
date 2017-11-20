package com.binary;

import com.Interview;

/**
 * Created by a.nigam on 06/12/16.
 */
public class BitOperationSamples {

    public static void samples() {
        byte x = Byte.MAX_VALUE;
        System.out.println(x+"\t"+Integer.toBinaryString(x));

        for (int i = 1; i < 8; i++) {
            x = (byte) (-1 << i);
            System.out.println(x+"\t"+Integer.toBinaryString(x));
        }

        System.out.println("NEGATIVE SERIES");


        x = Byte.MIN_VALUE;
        System.out.println(x+"\t"+Integer.toBinaryString(x));
        for (int i = 1; i < 8; i++) {
            x = (byte) (Byte.MIN_VALUE >> i);
            System.out.println(x+"\t"+Integer.toBinaryString(x));
        }


    }

    // Function to return the only odd occurring element
    public static int findOdd(int arr[], int n) {
        int res = 0, i;
        for (i = 0; i < n; i++)
            res ^= arr[i];
        return res;
    }

    public static void main(String[] args) {

        int arr[] = {12, 12, 14, 90, 14, 14, 14};
        int n = arr.length;
        System.out.println("The odd occurring element is %distance "+ findOdd(arr, n));

    }
}
