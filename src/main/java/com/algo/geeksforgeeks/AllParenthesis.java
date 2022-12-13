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

    public List<String> generateAllParenthesis(int n)
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
    private List<String> process( int endIndex) {
        if(cachingEnabled) {
            List<String> cachedResult = cache.get(endIndex);
            if (cachedResult != null) {
                return cachedResult;
            }
        }
        count++;
        if(endIndex == 1) {
            return Arrays.asList("()");
        }
        int start =0;
//        System.out.println("-->> "+end);

        LinkedList<String> combinations = new LinkedList<>();

        for (int i = endIndex ; i > start; i = i -2) {
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
            if(i < endIndex) {
                tailStart = i+1;
                tailEnd = endIndex;
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
            cache.put(endIndex, combinations);
        }
        return combinations;

    }

    /*
    // 0-1, 0-3, 0-5 .... 0-n
    // Note : this gives wrong results

    implemented again... same as above


     */
    public List<String> generateAllParenthesisTopDown(int end) {

        if( end == 1) {
            return Arrays.asList("()");
        }


        List<String> pairs = new ArrayList<>();
        for (int i = end; i >= 1; ) {


            List<String> pairsMid = Collections.emptyList();
            List<String> pairsEnd = Collections.emptyList();

            if(i >2) {
                pairsMid = generateAllParenthesisTopDown(i - 2);
            }

            if(i < end) {
                pairsEnd = generateAllParenthesisTopDown( end - i -1);
            }
            if(!pairsEnd.isEmpty() && !pairsMid.isEmpty()){
                for (String s : pairsMid) {
                    for (String e : pairsEnd) {
                        pairs.add("("+s+")"+e);
                    }
                }
            }
            else if(pairsEnd.isEmpty() ){
                for (String s : pairsMid) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("(").append(s).append(")");
                    pairs.add(sb.toString());
                }
            }
            else {
                for (String s : pairsEnd) {
                    pairs.add("()"+s);
                }
            }

            i = i -2;


        }
        return pairs;
    }

    public static void main(String[] args) {
        int size = 4;
//        new AllParenthesis().generateAllParenthesis(size);
        AllParenthesis solution = new AllParenthesis();
        List<String> allPairs = solution.generateAllParenthesisTopDown(2*size -1);
        solution.printResults(allPairs);
    }
}
