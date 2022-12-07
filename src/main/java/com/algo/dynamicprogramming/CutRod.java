package com.algo.dynamicprogramming;

/**
 * Refer: http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 */
public class CutRod {

    public static void main(String[] args) {
//        int[] p = {1, 5, 8, 9 ,10 ,17 ,17 ,20 ,24 ,30};
        int[] p  = {1  , 5  , 8  , 9  ,10  ,17 , 17, 20};
//        int[] p = {1, 5};
//        cutRodTopDown(p);

        cutRodTopDownApproach(p);
    }

    private static void cutRodTopDownApproach(int[] p) {

// r[0] => 0 as it is hypothetical situation where you make zero cuts


        int[] r = new int[p.length+1];
        init(r);
        r[0] =0;

        int maxRev =  maxRevenue(p, r, p.length);
        System.out.println("Top down => "+maxRev);
    }

    private static int maxRevenue(int[] p, int[] r, int rodLength) {


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

            System.out.println(prefix+ "Calculating Max revenue for rod length {"+ rodLength+"} ," +
                    " piece one length {"+ (i+1)+ "} and " +
                    "{ max revenue of rod length {"+ (rodLength -i-1)+"}");

            int maxRevenueSubProblem= p[i] + maxRevenue(p, r, rodLength- i - 1);
            if(maxRevenueSubProblem > maxRev) {
                maxRev = Math.max(maxRev, maxRevenueSubProblem);
                pieceOneLength =  i +1;
                pieceTwoLength =  rodLength - (i +1);
            }


        }
        r[rodLength] = maxRev;
        System.out.println(prefix+"Max revenue for rod length of {"+rodLength+"} is = "+ maxRev +" Piece 1 => "+ pieceOneLength+", Piece 2 => "+ pieceTwoLength);
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

        System.out.println(rev+"\n"+log);

        for (int i = 0; i < r.length; i++) {
            int size = i;
            StringBuilder sb = new StringBuilder();
            while (size>0){
                sb.append(peices[size]).append("-");
                size = size - peices[size];
            }

            System.out.println("Size\t"+(i+1)+"\t"+String.valueOf(r[i])+"\t"+sb);
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

        System.out.println("Processing\t"+n);
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
//                System.out.println("Skipped\t"+(n-i-1));
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
        System.out.println("Processing\t"+n);
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
//                System.out.println("Skipped\t"+(n-i-1));
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
