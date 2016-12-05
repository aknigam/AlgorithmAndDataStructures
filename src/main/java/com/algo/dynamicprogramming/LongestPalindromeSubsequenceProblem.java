package com.algo.dynamicprogramming;

/**
 * Created by a.nigam on 04/12/16.
 */
public class LongestPalindromeSubsequenceProblem {

    public static void main(String[] args) {

//        char[] c = "sns".toCharArray();
        char[] c = "charracter".toCharArray();
        String lp = topDownLongestPalindrom(c, 0, c.length -1);

        System.out.println(lp);
    }

    public static String topDownLongestPalindrom(char[] a, int iBegin, int iEnd){

        if(iBegin > iEnd){
            return "";
        }

        int n = iEnd - iBegin + 1;

        if(n < 2){
            return String.valueOf(a[iBegin]);
        }


        if(a[iBegin] == a[iEnd]){
            return a[iBegin] + topDownLongestPalindrom(a, iBegin+1 , iEnd-1)+ a[iEnd];
        }


        String p1 =  topDownLongestPalindrom(a, iBegin, iEnd - 1) ;
        String p2 =  topDownLongestPalindrom(a, iBegin + 1, iEnd ) ;

        return p1.length() > p2.length() ? p1 : p2;

    }
}
