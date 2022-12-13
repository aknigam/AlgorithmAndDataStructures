package com.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class JumpGameTwo {


    public static void main(String[] args) {
        JumpGameTwo j = new JumpGameTwo();
        
        int[] nums = new int[]{2,3,1,1,4};
//        int[] nums = new int[]{2,1};

        for (int i = 1; i <=0; i++) {
            System.out.println(i);
        }
        int jumps = j.jump(nums);
        System.out.println(jumps);
    }


    Map<Integer, Integer> cache =  new HashMap<>();
    public int jump(int[] nums) {
        return jumpInternal(nums,0);
    }

    private int jumpInternal(int[] nums, int start) {
        if(cache.containsKey(start)) {
            return cache.get(start);
        }
        System.out.println("Start => "+ start);
        if(start == nums.length -1) {
            return 0;
        }
        if(start >= nums.length) {
            return Integer.MAX_VALUE;
        }
        int jumps = 1;
        for (int j = 1; j <= nums[start] && start + j < nums.length; j++) {
            int ji = jumpInternal(nums, start + j);
            jumps = Math.min(jumps, 1 + ji );

        }

        cache.put(start, jumps);
        return jumps;
    }
}
