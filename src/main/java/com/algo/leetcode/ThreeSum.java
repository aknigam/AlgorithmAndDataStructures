package com.algo.leetcode;

import java.util.*;

/*

https://leetcode.com/problems/3sum/
 */
public class ThreeSum {


    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        Solution solution = new Solution();
        System.out.println(solution.threeSum(nums));
    }

    static class Solution {

        public List<List<Integer>> threeSum(int[] nums) {

            int size = nums.length;
            if(size < 3) {
                return Collections.emptyList();
            }
            if(size == 3) {
                int sum = nums[0] + nums[1] + nums[2];
                if(sum != 0) {
                    return Collections.emptyList();
                }
                return Arrays.asList(Arrays.asList(nums[0] , nums[1] , nums[2]));
            }

            List<List<Integer>> tl = new ArrayList<>();
            for (int i = 0; i < size-2; i++) {
                int one = nums[i];

                List<List<Integer>> ts = twoSums(i+1, size, nums, -1*one);

                for (List<Integer> t : ts) {
                    List<Integer> n = new ArrayList<>(t);
                    n.add(one);
                    tl.add(n);
                }
            }


            return removeDuplicates(tl);

        }

        private List<List<Integer>> removeDuplicates(List<List<Integer>> tl) {
            List<List<Integer>> tlw = new ArrayList<>();
            Set<String> unique = new HashSet();
            for (List<Integer> l: tl){
                Collections.sort(l);
                String s = l.get(0)+""+l.get(1)+""+l.get(0);
                if(!unique.contains(s)){
                    unique.add(s);
                    tlw.add(l);

                }

            }
            return tlw;
        }

        public List<List<Integer>> twoSums(int start, int end, int[] nums, int sum) {
            List<List<Integer>> tl = new ArrayList<>();

//            Map<Integer, Integer> map = new HashMap<>();

            for (int i = start; i < end -1; i++) {
                int one = nums[i];
                for (int j = i+1; j < end; j++) {
                    if(nums[j]+ one == sum) {
                        //found
//                        if(!checkPairExists(map, nums[j], one)) {
//                            map.put(one, nums[j]);
                            tl.add(Arrays.asList(one, nums[j]));
//                        }
                    }
                }
            }
            return tl;
        }

        private boolean checkPairExists( Map<Integer, Integer> map, int one, int two) {

            return (map.containsKey(one) && map.get(one) == two) || (map.containsKey(two) && map.get(two) == one);

        }


    }
}
