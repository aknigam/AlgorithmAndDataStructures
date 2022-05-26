package com.algo.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class UniformIntegers {


    public static void main(String[] args) {

        UniformIntegers ui = new UniformIntegers();

        ui.getUniformIntegerCountInInterval(75, 300);

    }

    public int getUniformIntegerCountInInterval(long a, long b) {
        if(b < a) {
            return 0;
        }

        List<Long> nums = new ArrayList<>();
        int vals = 0;

        long remainder = 0;
        long digits = 0;
        long divider = 1;
        while( true ){
            remainder = a / divider;
            digits ++;
            if(remainder < 10) {
                break;
            }
            divider = divider * 10;

        }

        System.out.println(digits);
        System.out.println(remainder);

        long num = 0;
        for (int i = 0; i < digits; i++) {
            num = (long) (num + remainder * Math.pow(10, i));
        }
        vals++;
        nums.add(num);

        if(num > b) {
            return vals;
        }





        System.out.println(nums);



        // Write your code here
        return 0;
    }
}
