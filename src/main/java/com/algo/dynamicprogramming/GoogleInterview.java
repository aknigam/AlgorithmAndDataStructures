package com.algo.dynamicprogramming;

import com.algo.util.ArrayUtils;

/**
 * Created by a.nigam on 09/12/16.
 */
public class GoogleInterview {

    // https://www.geeksforgeeks.org/introduction-to-backtracking-data-structure-and-algorithm-tutorials/#:~:text=Backtracking%20can%20be%20defined%20as,search%20for%20a%20feasible%20solution.
    // print all combinations
    public static void main(String[] args) {

        String[] songs = new String[]{"A", "B", "C"};
        printAllOrder(songs, 0, songs.length-1);
    }


    static int counter =0;
    private static void printAllOrder(String[] songs, int start, int end) {
        System.out.println("\t\t\tprintAllOrder..."+ counter++);
        if(start == end) {
            ArrayUtils.print(songs);
        }


        for (int i = start; i <= end; i++) {
            if(start + 1> end) {
                break;
            }
            printAllOrder(songs, start+1, end);

            swap(songs, start, end);


        }


    }

    private static void swap(String[] songs, int start, int end) {
        String temp = songs[start];
        songs[start] = songs[end];
        songs[end] = temp;
    }
}
