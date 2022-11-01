package com.algo.leetcode;

public class FindInRotatedArray {

    public static void main(String[] args) {
        Solution s = new Solution();
        int i =s.search(new int[]{1, 3}, 3);
        System.out.println(i);
    }

    static class Solution {
        public int search(int[] nums, int target) {

            int size = nums.length;
            if(size == 0) {
                return -1;
            }
            if(size == 1) {
                return target == nums[0] ? 0 : -1;
            }

            int pivot = size-1;
            int previous = nums[0];
            for(int i= 1; i< size; i++)
            {
                if(previous > nums[i]) {
                    pivot = i-1;
                    break;
                }
                previous = nums[i];

            }

            // 0 to pivot
            // pivot + 1 to len -1

            int startIndex = -1;
            int endIndex = -1;

            if(pivot == size -1) {
               return search(nums, 0, pivot, target);
            }
            if(target <= nums[size-1]) {
                startIndex = pivot + 1;
                endIndex = size -1;
            }
            else {
                startIndex = 0;
                endIndex = pivot;
            }

            return search(nums, startIndex, endIndex, target);


        }

        public int search(int[] nums, int startIndex, int endIndex, int target) {

            if(endIndex < startIndex) {
                return -1;
            }

            if(endIndex == startIndex) {
                return target == nums[startIndex] ? startIndex : -1;
            }

            int mid =  startIndex + (endIndex - startIndex)/2;


            int targetIndex = search(nums, startIndex, mid, target);

            if(targetIndex != -1) {
                return targetIndex;
            }
            return search(nums, mid+1, endIndex, target);

        }
    }
}
