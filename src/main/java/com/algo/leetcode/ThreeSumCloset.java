package com.algo.leetcode;

import com.algo.util.ArrayUtils;
import com.survey.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/3sum-closest/
Solution: https://www.geeksforgeeks.org/find-a-triplet-in-an-array-whose-sum-is-closest-to-a-given-number/
 */
public class ThreeSumCloset {


    public static void main(String[] args) {
        ThreeSumCloset solution = new ThreeSumCloset();

        int[] nums = {4,0,5,-5,3,3,0,-4,-5};
        int target = -2;
        System.out.println(solution.threeSumClosest(nums, target));
    }


    public int threeSumClosest(int[] nums, int target) {
        // https://www.geeksforgeeks.org/find-a-triplet-in-an-array-whose-sum-is-closest-to-a-given-number/
        return -1;

    }
}
