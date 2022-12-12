package com.algo.leetcode;



/*

https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutCharRepetition {

    public static void main(String[] args) {

        LongestSubstringWithoutCharRepetition solution = new LongestSubstringWithoutCharRepetition();

        String s  = "abcabcbb";
        solution.lengthOfLongestSubstring(s);

    }

    public int lengthOfLongestSubstring(String s) {
        char[] ca = s.toCharArray();


        int longest = 0;
        int currLongest = 0;

        for (int i = 0; i < ca.length; i++) {
            String current = String.valueOf(ca[i]);
        }


        return -1;
    }

}
