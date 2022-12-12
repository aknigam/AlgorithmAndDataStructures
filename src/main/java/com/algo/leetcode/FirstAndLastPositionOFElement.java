package com.algo.leetcode;

import com.algo.util.ArrayUtils;

/*

https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

Probably this was also the facebook question
 */
public class FirstAndLastPositionOFElement {

    public static void main(String[] args) {
        FirstAndLastPositionOFElement sol = new FirstAndLastPositionOFElement();


        int[] nums = {5, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        int target = 8;
        int[] ans = sol.searchRange(nums, target);

    }

    // non-decreasing
    private int[] searchRange(int[] nums, int target) {


        /*
        // first find any index where target is present

        // go to left and try to find the occurrence of lower number.
        if mid is less then go to right until you discover this number
        if mid is the target then go to left
        once you discover a lower number of left then try to find the target on the right

        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17
        1   2   3   4   [5] 6   7   [7]   7   7 7   7   7   9   10  12  18  20

        // go to right and try to find the occurrence of higher number.


         */
        int left = -1, right = -1;
        ArrayUtils.printIntArray(nums);

        int anyIndexWithTargetValue = findTargetIndex(nums, target, 0, nums.length - 1);
        System.out.println(anyIndexWithTargetValue + "[" + nums[anyIndexWithTargetValue] + "]");

        boolean leftMove = true;
        int[] pair = {-1, -1};
        int sl = 0;
        int sr = anyIndexWithTargetValue - 1;
        while (true) {

            if(leftMove) {
                pair = findIndexOnLeftWithNonTargetValue(nums, target, sl, sr);
            } else  {
                pair = findIndexOnRightWithNonTargetValue(nums, target, sl , sr);
            }
            System.out.println(pair + "[" + nums[pair[0]] + "]");

            sl = pair[0];
            sr = pair[1];
            if(leftMove) {
                if (pair[1]  == sl) {
                    left = 0;
                    break;
                }
                else if (nums[sl + 1] == target) {
                    left = sl + 1;
                    break;
                }
                else {
                    sl = sl +1;
                }
            }
            else {
                if (pair[1] ==  sr) {
                    left = 0;
                    break;
                }
                else if (nums[sr - 1] == target) {
                    right = sr -1;
                    break;
                }
                else {
                    sr = sr -1;
                }
            }

            leftMove = !leftMove;


        }

        System.out.println(left +" <-> "+right);





        // go to right until you find


        return new int[0];
    }

    private int[] findIndexOnLeftWithNonTargetValue(int[] nums, int target, int start, int end) {

        if (start > end) {
            return new int[]{-1, end};
        }
        int midIndx = start + (end - start) / 2;

        if (nums[end] != target) {
            return new int[]{end, end};
        }
        if (nums[midIndx] == target) {
            return findIndexOnLeftWithNonTargetValue(nums, target, start, midIndx - 1);
        }
        return new int[]{midIndx, end};
    }

    private int[] findIndexOnRightWithNonTargetValue(int[] nums, int target, int start, int end) {

        if (start > end) {
            return new int[]{-1, end};
        }
        int midIndx = start +  (end - start) / 2;
        if (nums[start] != target) {
            return new int[]{start, end};
        }
        if (nums[midIndx] == target) {
            return findIndexOnRightWithNonTargetValue(nums, target, midIndx + 1, end);
        }
        return new int[]{start, midIndx};
    }

    private int findTargetIndex(int[] nums, int target, int start, int end) {

        if (start > end) {
            return -1;
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }

        int midIndx = (end - start) / 2;

        if (target == nums[midIndx]) {
            return midIndx;
        }
        if (target > nums[midIndx]) {
            return findTargetIndex(nums, target, midIndx + 1, end);
        }
        return findTargetIndex(nums, target, start, midIndx - 1);
    }
}
