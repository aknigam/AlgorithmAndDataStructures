package com.algo.dynamicprogramming;

/**
 * Created by a.nigam on 04/12/16.
 * ITL, 15.4 Longest common subsequence
 */
public class LongestCommonSubSequenceProblem {

    private static boolean debug = true;
    private static String[][] cache;

    public static void main(String[] args) {

        String x = "10010101";
        String y = "010110110";

        cache = new String[y.length()][x.length()];
        String lcs = topDownMemorizedLCS(x.toCharArray(), 0, y.toCharArray(), 0);
        if(lcs.trim().equals("")){
            System.out.println("No common subsequence");
        }else
            System.out.println(lcs);

    }

    public static String topDownMemorizedLCS(char[] x, int iXBegin, char[]y, int iYBegin){
        System.out.println("Call \t["+ iYBegin+","+iXBegin+"]");
        if(iXBegin >= x.length || iYBegin >= y.length){
            return "";
        }
        if(cache[iYBegin][iXBegin] != null){
            if(debug)
                System.out.println("Cache hit\t["+ iYBegin+","+iXBegin+"]");
            return cache[iYBegin][iXBegin];
        }

        StringBuilder sb = new StringBuilder();
        int n = x.length - iXBegin;
        int m = y.length - iYBegin;
        if(n == 0 || m == 0){
            return "";
        }
        String subSeq;
        if (x[iXBegin] == y[iYBegin]) {
            subSeq = sb.append(x[iXBegin]).append(topDownMemorizedLCS(x, iXBegin + 1, y, iYBegin + 1)).toString();
            cache[iYBegin][iXBegin] = subSeq;
            return subSeq;
        }
        String a = topDownMemorizedLCS(x, iXBegin + 1, y, iYBegin);
        String b = topDownMemorizedLCS(x, iXBegin, y, iYBegin + 1);
        subSeq =  a.length() > b.length() ? a : b;
        cache[iYBegin][iXBegin] = subSeq;
        return subSeq;

    }

    public static String bottomUpLCS(char[] x, int iXBegin, char[]y, int iYBegin){
        return "";
    }

}
