package com.algo.string;

public class MedianOfTwoSortedArrays {


    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        MedianOfTwoSortedArrays s = new MedianOfTwoSortedArrays();

        System.out.println(s.findMedianSortedArrays(nums2, nums1));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] nums = new int[len1 + len2];
        intitatise(nums);
        int len = len1 + len2;

        if(len == 0) {
            return 0;
        }

        int a1 = 0;
        int a2 = 0;
        int i = 0;
        for (; i < len; i++) {
            if(a1 == len1 || a2 == len2) {
                break;
            }
            int x = nums1[a1];
            int y = nums2[a2];

            if(x < y) {
                a1++;
                nums[i] = x;
            }
            else{
                a2++;
                nums[i] = y;
            }
        }
        if(a1 < len1) {
            for (int j = a1; j < len1; j++) {
                nums[i++] = nums1[j];
            }
        }
        else {
            for (int j = a2; j < len2; j++) {
                nums[i++] = nums2[j];
            }

        }
        print(nums);
        if(len%2 == 0) {
            // 0, 1, 2
            int in = len/2;
            double m = nums[in] + nums[in -1];
            return m/2;
        }
        else {
            return nums[len/2];
        }


    }

    private void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
        System.out.println("\n");
    }

    private void intitatise(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -1;
        }
    }
}
