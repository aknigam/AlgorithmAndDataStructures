package com.algo.string;

/**
 * Created by a.nigam on 01/12/16.
 */
public class SubString {



    public static void main(String[] args) {
        char[] sub = "abcd".toCharArray();
        char[] p =  "abcabdcd".toCharArray();

        boolean isSubStr = isSubString(p, sub);
        System.out.println("1 algo\t"+isSubStr);
        isSubStr = isSubSequence(sub, p , sub.length, p.length);
        System.out.println("2 algo\t"+isSubStr);
    }

    /**
     * http://www.geeksforgeeks.org/given-two-strings-find-first-string-subsequence-second/
     */
    static boolean isSubSequence(char str1[], char str2[], int m, int n)
    {
        int j = 0; // For index of str1 (or subsequence

        // Traverse str2 and str1, and compare current character
        // of str2 with first unmatched char of str1, if matched
        // then move ahead in str1
        for (int i=0; i<n&&j<m; i++) {
            System.out.println("alt\t"+ i+"\t"+j);
            if (str1[j] == str2[i])
                j++;
        }

        // If all characters of str1 were found in str2
        return (j==m);
    }

    private static boolean isSubString(char[] p, char[] sub) {

        if(sub.length == 0){
            return false;
        }
        if(sub.length > p.length){
            return false;
        }


        for (int i = 0; i < p.length; ) {

            int j;
            for (j = 0; j < sub.length; ) {
                if(p[i] != sub[j]){
                    break;
                }
                j = j + 1;
                i = i + 1;
            }
            System.out.println(i+"\t"+j);
            if(j == sub.length){
                return true;
            }
            if(j == 0){
                i++;
            }

        }


        return false;
    }
}
