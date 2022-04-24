package com.algo.greedyalgorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by a.nigam on 14/11/16.
 * http://www.geeksforgeeks.org/greedy-algorithms-set-1-activity-selection-problem/
 *
 * 16.1 An activity-selection problem in Introduction to Algorithms
 *
 * This solution is based on algo mentioned in An iterative greedy algorithm
 */
public class ActivitySelection {


    private static List<Integer> selectActivitiesGreedily(int[] s, int[] f) {

        List<Integer> selected = new LinkedList<>();

        selected.add(0);
        int k =0;

        for (int m = 1; m < s.length; m++) {
            if(s[m] >= f[k]) {
                selected.add(m);
                k = m;
            }

        }

        return selected;
    }




    public static void main(String[] args) {



        int[] s = new int[]{1 ,3 ,0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = new int[]{4,5,6,7,9,9,10,11,12,14,16};

        List<Integer> selected = selectActivitiesGreedily(s, f);

        System.out.println(selected);


    }



}
