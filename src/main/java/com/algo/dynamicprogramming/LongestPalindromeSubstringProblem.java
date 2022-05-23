package com.algo.dynamicprogramming;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromeSubstringProblem {


    public static void main(String[] args) {

//        char[] c = "sns".toCharArray();
//        char[] c = "abcbabcgfbabcba".toCharArray();
        char[] c = "abbcccbbbcaaccbababcbcabca".toCharArray();

        System.out.println(dpSolution(c));

    }

    private static String dpSolution(char[] c){

        String[][] cache = new String[c.length][c.length];
        intialiseCache(cache, c.length);
        String lp = dpTopDownLongestPalindrom(c, 0, c.length -1, cache);
        return lp;
    }
    private static void intialiseCache(String[][] cache, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                cache[i][j] ="";
            }

        }
    }

    public static String dpTopDownLongestPalindrom(char[] a, int iBegin, int iEnd, String[][] cache){
        if(cache[iBegin][iEnd] != "") {
            return cache[iBegin][iEnd];
        }

        if(iBegin > iEnd){
            return "";
        }

        int n = iEnd - iBegin + 1;

        if(n < 2){
            return String.valueOf(a[iBegin]);
        }


        if(a[iBegin] == a[iEnd]){
            String midPalin = dpTopDownLongestPalindrom(a, iBegin + 1, iEnd - 1, cache);
            if(midPalin.length() + 2 == n ) {
                cache[iBegin][iEnd] =  a[iBegin] + midPalin + a[iEnd];
                return cache[iBegin][iEnd];
            }
        }

        String startPalin = dpTopDownLongestPalindrom(a, iBegin , iEnd - 1, cache);
        String endPalin = dpTopDownLongestPalindrom(a, iBegin + 1, iEnd, cache);
        if(startPalin.length() > endPalin.length()) {
            cache[iBegin][iEnd] = startPalin;
            return cache[iBegin][iEnd];
        }

        else {
            cache[iBegin][iEnd] = endPalin;
            return endPalin;
        }
    }
}
