package com.algo.dynamicprogramming;


import java.util.Arrays;
import java.util.Collections;

/**

ITL: 15.4-5

https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/

 Dynamic programming solution
 ----------------------------
 https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/


 {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is
 {10, 22, 33, 50, 60, 80}.


 1 , 3 , 5, 7 , 9    [9]


 Does it fit into this series in the end => size + 1
 Does it fit into this series between the end and the end -1 element => size [modified list]






1. If A[i] is smallest among all end
   candidates of active lists, we will start
   new active list of length 1.
2. If A[i] is largest among all end candidates of
  active lists, we will clone the largest active
  list, and extend it by A[i].
3. If A[i] is in between, we will find a list with
  largest end element that is smaller than A[i].
  Clone and extend this list by A[i]. We will discard all
  other lists of same length as that of this modified list.
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
//        int[] nums =  {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};


//        System.out.println("{4,  3, 5, 7} "+ longestSubsequence(new int[]{4,  3, 5, 7}));
//        System.out.println("{2,  3, 5, 7} "+ longestSubsequence(new int[]{2,  3, 5, 7}));
//        System.out.println("{2,  3, 1, 7} "+ longestSubsequence(new int[]{2,  3, 1, 7}));
//        System.out.println("{0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15} "+ longestSubsequence(new int[]{0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15}));
//        System.out.println("{0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15} "+ longestSubsequence(new int[]{8,4,12,2,10,6,14,1,9,5,13,3,11,7}));
        System.out.println(longestSubsequence(new int[]{10, 22, 9, 33, 21, 50, 41, 60}));

//        System.out.println(longestSubsequence(new int[]{3, 6}, 0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE));//2
//        System.out.println(longestSubsequence(new int[]{3, 6}, 0, 1, 3, 5)); //1
//        System.out.println(longestSubsequence(new int[]{3, 6}, 0, 1, 4, 6)); //1
//        System.out.println(longestSubsequence(new int[]{3, 6}, 0, 1, 4, 5)); // 0
//        System.out.println(longestSubsequence(new int[]{3, 6}, 0, 1, 4, 5)); // 0
//        System.out.println(longestSubsequence(new int[]{6, 3}, 0, 1, 2, 6)); // 1
//        System.out.println(longestSubsequence(new int[]{6, 3}, 0, 1, 4, 5)); // 1

    }

    static int longestSubsequence(int a[])
    {
        Result result = longestSubsequence(a, 0, a.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(result.s);
        return result.size;
    }

    static class Result implements  Comparable<Result>{
        int size =0;
        String s = "";

        public Result(int result, String s) {
            size = result;
            this.s = s;
        }

        public Result() {

        }


        @Override
        public int compareTo(Result o) {
            int x = this.size;
            int y = o.size;
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        }
    }

    static Result longestSubsequence(
            int a[],
            int begin,
            int end,
            int lowerBound,
            int upperBound)
    {

        int len = end - begin + 1;
        if(len == 0) {
            return new Result(0, "");
        }
        int e = a[end];
        int s = a[begin];
        if(len == 1 ) {
            // return new int[]{a[begin]};
            if(a[begin] >= lowerBound && a[begin] <= upperBound) {

                return new Result(1, ""+a[begin]);
            }
            return new Result(0, "");
        }


        /*
        Simple






         */

        Result ca = new Result();
        Result cb =new Result();
        Result cc= new Result();
        Result cd = new Result();

        if(s <= e && s >= lowerBound && e <= upperBound) {
            // a - First included and last included - both are in the range and last >= first
            ca = longestSubsequence(a, begin +1, end-1, s, e);
            ca.size = ca.size+2;
            if(ca.s != "") {
                ca.s = s + "," + ca.s + "," + e;
            }
            else{
                ca.s = s + ","  + e;
            }
        }
        if(s >= lowerBound && s <= upperBound){
            //b - First included and last excluded - first should be in range
            cb = longestSubsequence(a, begin +1, end-1, s, upperBound);
            cb.size = cb.size + 1;
            cb.s =cb.s == ""? ""+s: s +","+ cb.s ;
        }
        if(e >= lowerBound && e <= upperBound){
            //c - First excluded and last included - last should be in range
            cc =  longestSubsequence(a, begin +1, end-1, lowerBound, e);
            cc.size = cc.size + 1;
            cc.s = cc.s == ""? ""+e: cc.s+","+e ;
        }
        //d - First excluded and last excluded - no condition
        cd = longestSubsequence(a, begin +1, end-1, upperBound, lowerBound);

        Result[] results = new Result[]{ca, cb, cc, cd};
        Arrays.sort(results);
//        if(!results[3].s.equalsIgnoreCase("")){
//            System.out.println(begin+"-"+end+"   >> "+ results[3].s);
//        }

        return results[3];
    }
}
