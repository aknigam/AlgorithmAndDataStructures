package com.algo.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/*

https://leetcode.com/discuss/interview-question/1555073/facebook-phone-count-uniform-numbers-in-range

A uniform integer is the one which as same digits in all the positions like 2, 22, 33 777777
 */
public class UniformIntegers {


    public static void main(String[] args) {

        UniformIntegers ui = new UniformIntegers();

        ui.getUniformIntegerCountInInterval(75, 900);

    }

    public int getUniformIntegerCountInInterval(long a, long b) {
        if(b < a) {
            return 0;
        }

        List<Long> nums = new ArrayList<>();
        int vals = 0;

        long digitValue = 0;
        long digits = 0;
        long divider = 1;
        while( true ){
            digitValue = a / divider;
            digits ++;
            if(digitValue < 10) {
                break;
            }
            divider = divider * 10;
        }

        System.out.println(digits);
        System.out.println(digitValue);

        while (true) {
            long num = getUniformNumberWithDigits(digitValue, digits);
            if (num > b) {
                break;
            }
            vals++;
            nums.add(num);
            // e.g go from 77 to 88
            if(digitValue < 10) {
                digitValue++;
            }
            // got from 99 to 111
            if(digitValue == 9) {
                digits++;
                digitValue = 1;
            }


        }

        System.out.println(nums);
        // Write your code here
        return vals;
    }

    private static long getUniformNumberWithDigits(long remainder, long digits) {
        long num = 0;
        for (int i = 0; i < digits; i++) {
            num = (long) (num + remainder * Math.pow(10, i));
        }
        return num;
    }
}
