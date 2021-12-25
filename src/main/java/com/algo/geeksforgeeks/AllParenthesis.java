package com.algo.geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://practice.geeksforgeeks.org/problems/generate-all-possible-parentheses/1/?company[]=Walmart&company[]=Walmart&page=1&query=company[]Walmartpage1company[]Walmart
public class AllParenthesis {


    public List<String> AllParenthesis(int n)
    {
        List<String> combinations = new ArrayList<>();
        process("","",0, 2*n-1, combinations);
        return Collections.emptyList();
    }

    private String process(String prefix, String postFix, int start, int end, List<String> combinations) {

        if(end -1 == start) {
            String comb = prefix + "()"+postFix;
            System.out.println(String.format("Start %d to end %d , combination: %s", start, end, comb));
            return comb;
        }

        for (int i = end ; i > start; i = i -2) {
            String pf = prefix + "(";
            String ef = ")" + postFix;

            int midStart = start + 1;
            int midEnd = i - 1;
            int tailStart =-1;
            int tailEnd =-1;

            if(midEnd > midStart) {
                String mid = process(pf, ef, midStart, midEnd, combinations);
            }

            String tail ="";
            if(i < end) {
                tailStart = i+1;
                tailEnd = end;
                tail = process("","",tailStart, tailEnd, combinations);
            }



//            System.out.println(prefix+"("+mid+")"+ef+tail+ postFix);

        }


        return "";

    }

    public static void main(String[] args) {
        new AllParenthesis().AllParenthesis(3);
    }
}
