package com.algo.dynamicprogramming;

import com.survey.Log;

/**
 * Refer: http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 */
public class CutRod {

    public static void main(String[] args) {
        int[] p = {1, 5, 8, 9 ,10 ,17 ,17 ,20 ,24 ,30};
//        int[] p  = {1  , 5  , 8  , 9  ,10  ,17 , 17, 20};
//        int[] p = {1, 5};
//        cutRodTopDown(p);

        cutRodTopDownApproach(p);
        cutRodBottomUpApproach(p);
    }

    private static int cutRodBottomUpApproach(int[] p) {
        Log.setLevel(Log.INFO);
        int[] r = new int[p.length+1];
        init(r);
        r[0] =0;

        int rodLength =  p.length;

        // incrementally find the max revenue for increasing lengths of rod
        for (int j = 0; j < rodLength; j++) {

            // following for loop is calculating the max revenue for the rod of length j
            // in bottom up
            // manner
            int maxRevJlen = Integer.MIN_VALUE;
            int p1len =0;
            int p2len =0;
            for (int i = 0; i <= j; i++) {
                int revenue =  r[i] + p[j-i];
                if(revenue > maxRevJlen) {
                    maxRevJlen = Math.max(maxRevJlen, r[i] + p[j - i]);
                    p1len =  i;
                    p2len = j -i+1;
                }
            }
            Log.debug("Max revenue for size "+(j+1)+"=> "+ maxRevJlen+" p1 ="+ p1len+", p2 len = "+ p2len );
            r[j+1] = maxRevJlen;

        }
        Log.info("Max revenue with bottom up approach => "+ r[p.length]);

        return r[p.length];


    }
//    r[n]  = max  (p[i]  + r[n-i]).  1<= i <= n
    private static int maxRevenueBottomUp(int[] p, int[] r, int rodLength) {
        int maxRev = 0;


        //len = i+1

        for (int i = 0; i < rodLength; i++) {
            r[i+1] = Math.max(  r[i] +   p[0], p[i]);

        }
        return maxRev;
    }


    private static void cutRodTopDownApproach(int[] p) {

        // r[0] => 0 as it is hypothetical situation where you make zero cuts

        Log.setLevel(Log.DEBUG);
        int[] r = new int[p.length+1];
        init(r);
        r[0] =0;

        int maxRev =  maxRevenueTopDown(p, r, p.length);
        Log.info("Top down => "+maxRev);
    }

    private static int maxRevenueTopDown(int[] p, int[] r, int rodLength) {


        if(r[rodLength] > -1) {
            return r[rodLength];
        }

        String prefix = getPrefix(rodLength, p.length);
        if(rodLength == 0) {
            return 0;
        }
        int maxRev = Integer.MIN_VALUE;
        int pieceOneLength = 0;
        int pieceTwoLength = 0;
        for (int i = 0; i < rodLength; i++) {

            Log.debug(prefix+ "Calculating Max revenue for rod length {"+ rodLength+"} ," +
                    " piece one length {"+ (i+1)+ "} and " +
                    "{ max revenue of rod length {"+ (rodLength -i-1)+"}");

            int maxRevenueSubProblem= p[i] + maxRevenueTopDown(p, r, rodLength- i - 1);
            if(maxRevenueSubProblem > maxRev) {
                maxRev = Math.max(maxRev, maxRevenueSubProblem);
                pieceOneLength =  i +1;
                pieceTwoLength =  rodLength - (i +1);
            }


        }
        r[rodLength] = maxRev;
        Log.info(prefix+"Max revenue for rod length of {"+rodLength+"} is = "+ maxRev +" Piece 1 => "+ pieceOneLength+", Piece 2 => "+ pieceTwoLength);
        return r[rodLength];
    }

    private static String getPrefix(int rodLength, int length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length - rodLength; i++) {
            s.append("\t");
        }
        return s.toString();
    }


    public static void cutRodTopDown(int[] p) {

        int[] r = new int[p.length];
        init(r);

        int[] peices = new int[p.length];
        init(peices);
        StringBuilder log = new StringBuilder();
        int rev = extendedMemoizedCutRodTopDown(p, p.length, log,r, peices);

        Log.info(rev+"\n"+log);

        for (int i = 0; i < r.length; i++) {
            int size = i;
            StringBuilder sb = new StringBuilder();
            while (size>0){
                sb.append(peices[size]).append("-");
                size = size - peices[size];
            }

            Log.info("Size\t"+(i+1)+"\t"+String.valueOf(r[i])+"\t"+sb);
        }




    }

    private static void init(int[] r) {
        for (int i = 0; i < r.length; i++) {
            r[i] = -1;
        }
    }



    static int cutRodBottomUp(int price[], int n){

        return -1;

    }
    static int cutRod(int price[], int n)
    {
        if (n <= 0)
            return 0;
        int max_val = Integer.MIN_VALUE;

        // Recursively cut the rod in different pieces and
        // compare different configurations
        for (int i = 0; i<n; i++)
            max_val = Math.max(max_val,
                    price[i] + cutRod(price, n-i-1));

        return max_val;
    }

    /*
    Outputs the revenue and sizes of the rod as well.
     */
    static int extendedMemoizedCutRodTopDown(int price[], int n, StringBuilder log, int[] r, int[] peices){

        Log.info("Processing\t"+n);
        if (n <= 0)
            return 0;

        int max_val = Integer.MIN_VALUE;

        // Recursively cut the rod in different pieces and
        // compare different configurations

        int size =-1;
        for (int i = 0; i<n; i++) {
//            log.append("n="+n+"\ti="+i+"	P("+(i+1)+"="+price[i]+")+ R(" + (n- i -1)+")\t,\t");
            int rev =-1;

            if((n-i-1 -1 )>0){
                rev = r[n-i-1 -1];
            }
            if(rev == -1) {
                rev = extendedMemoizedCutRodTopDown(price, n - i - 1, log, r,peices);
            }else {
//                Log.info("Skipped\t"+(n-i-1));
            }

            if(max_val < price[i] + rev) {
                max_val = Math.max(max_val, price[i] + rev);
                size = i+1;

            }


        }
        if(peices[n-1] == -1)
            peices[n-1] = size;
        append(log,String.valueOf(size)+"("+n+")");
        r[n-1] =  max_val;
        return max_val;
    }

    private static void append(StringBuilder log, String size) {
        log.append(size).append("\t");
    }

    static int memoizedCutRodTopDown(int price[], int n, StringBuilder log, int[] r)
    {
        Log.info("Processing\t"+n);
        if (n <= 0)
            return 0;

        int max_val = Integer.MIN_VALUE;

        // Recursively cut the rod in different pieces and
        // compare different configurations

        for (int i = 0; i<n; i++) {
//            log.append("n="+n+"\ti="+i+"	P("+(i+1)+"="+price[i]+")+ R(" + (n- i -1)+")\t,\t");
            int rev =-1;

            if((n-i-1 -1 )>0){
                rev = r[n-i-1 -1];
            }
            if(rev == -1) {
                rev = memoizedCutRodTopDown(price, n - i - 1, log, r);
            }else {
//                Log.info("Skipped\t"+(n-i-1));
            }

            if(max_val < price[i] + rev) {
                max_val = Math.max(max_val, price[i] + rev);
                log.append(i).append("\t");

            }


        }
        r[n-1] =  max_val;
        return max_val;

    }

}
