package com.algo.leetcode;

/*

https://leetcode.com/problems/integer-to-roman/

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II.
The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.intToRoman(44));
    }

    static class Solution {

        public String intToRoman(int num) {

            StringBuilder roman = new StringBuilder();
            int balance = num;

            while (balance > 0) {
                int range = -1;


                String balanceRoman = "";

                if(balance >= 1000) {
                    range = 1000;
                    balanceRoman = "M";
                } else if(balance >= 900){
                    balanceRoman = "CM";
                    balance = balance - 900;
                } else if(balance >= 500) {
                    range = 500;
                    balanceRoman = "D";
                } else if(balance >= 400){
                    balanceRoman = "CD";
                    balance = balance - 400;
                } else if(balance >= 100) {
                    range = 100;
                    balanceRoman = "C";
                } else if(balance >= 90){
                    balanceRoman = "XC";
                    balance = balance - 90;
                } else if(balance >= 50) {
                    range = 50;
                    balanceRoman = "L";
                } else if(balance >= 40){
                    balanceRoman = "XL";
                    balance = balance - 40;
                } else if(balance >= 10) {
                    range = 10;
                    balanceRoman = "X";
                } else if(balance >= 9){
                    balanceRoman = "IX";
                    balance = balance - 9;
                } else if(balance >= 5) {
                    range = 5;
                    balanceRoman = "V";
                } else if(balance >= 4){
                    balanceRoman = "IV";
                    balance = balance - 4;
                } else {
                    range = 1;
                    balanceRoman = "I";
                }

                if(range != -1) {
                    int multiples = balance / range;

                    for (int i = 0; i < multiples; i++) {
                        roman.append(balanceRoman);
                    }

                    balance = balance % range;
                } else {
                    roman.append(balanceRoman);
                }
            }


            return roman.toString();
        }
    }
}
