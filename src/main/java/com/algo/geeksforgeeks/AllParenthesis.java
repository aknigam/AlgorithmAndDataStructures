package com.algo.geeksforgeeks;

import java.util.*;

/**
 * This is dynamic programming at its best. I have coded this solution
 */
// https://practice.geeksforgeeks.org/problems/generate-all-possible-parentheses/1/?company[]=Walmart&company[]=Walmart&page=1&query=company[]Walmartpage1company[]Walmart
public class AllParenthesis {

    static int count =0;

    private Map<Integer, List<String>> cache = new HashMap();
    boolean cachingEnabled = true;

    public List<String> AllParenthesis(int n)
    {
        long startTime = System.currentTimeMillis();
        List<String> combinations = process( 2*n-1);

        long endTime = System.currentTimeMillis() ;
        long duration = endTime - startTime;
//        System.out.println("Time taken ->> "+duration);


        printResults(combinations);
        return combinations;
    }

    private void printResults(List<String> combinations) {
        for (String s: combinations) {
            System.out.println(s);
        }
        System.out.println(combinations.size());
        System.out.println(count);
    }



    /**

     end is the end index and 0 is the start index
     if n is the input then end = 2*n -1. E.g n= 3 then end = 5

     This is a DP problem and here is how it can be solved

     start by putting the  opening brace - '(' at 0th index and the closing brace - ')' at following indices:
     - 3, 5, ...... , end

     or

     start by putting the  opening brace - '(' at 0th index and the closing brace - ')' at the end and then following
     - end -2, end - 4, end - 6.... end - 2*i


     Both the above will result in some sub problems

     one sub-problem is within the braces - we call it mid
     and the other sub-problem is at the tail - we call it tail

     The final solution is obtained by combining the solutions of all the sub-problems


     */
    private List<String> process( int end) {
        if(cachingEnabled) {
            List<String> cachedResult = cache.get(end);
            if (cachedResult != null) {
                return cachedResult;
            }
        }
        count++;
        if(end == 1) {
            return Arrays.asList("()");
        }
        int start =0;
//        System.out.println("-->> "+end);

        LinkedList<String> combinations = new LinkedList<>();

        for (int i = end ; i > start; i = i -2) {
            count++;
            int midStart = start + 1;
            int midEnd = i - 1;
            int tailStart =-1;
            int tailEnd =-1;


            List<String> mids = Collections.emptyList();
            if(midEnd > midStart) {
                 mids = process( midEnd-midStart);
            }

            List<String> tails = Collections.emptyList();
            if(i < end) {
                tailStart = i+1;
                tailEnd = end;
                tails = process(tailEnd-tailStart);
            }
            if(!mids.isEmpty() && !tails.isEmpty()) {
                for (String mid : mids) {

                    for (String tail : tails) {
                        combinations.add("(" + mid + ")" + tail);
                    }
                }
            }
            if(mids.isEmpty()) {
                for (String tail : tails) {
                    combinations.push ("(" + ")" + tail);
                }
            }
            if(tails.isEmpty()) {
                for (String mid : mids) {
                    combinations.push("(" + mid + ")" );
                }
            }
        }

        if(cachingEnabled) {
            cache.put(end, combinations);
        }
        return combinations;

    }

    public static void main(String[] args) {
        new AllParenthesis().AllParenthesis(5);
    }
}
