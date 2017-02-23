package com.algo.dynamicprogramming;

import com.algo.util.ArrayUtils;

/**
 * Created by a.nigam on 22/02/17.
 */
public class SprintPlanning {

    public static void main(String[] args) {
        int[] d = {7,5,13,4,5,6};
        int[] q = {4,3,6,2,3,3};

        findFinshTime(d, q);
    }

    public static int findFinshTime(int[] d, int[] q) {


        Integer[] ft = new Integer[d.length];

        ArrayUtils.initArraygeneric(ft, 0);

        int f = 0;
        int dt = 0;

        for (int i = 0; i < d.length; i++) {
            dt = dt + d[i];
            f = Math.max(dt , f) + q[i];
            ft[i] = f;
        }

        System.out.println("Finish time\t"+ f);

        ArrayUtils.printGeneric(ft);
        StringBuilder df = new StringBuilder();
        char c = 'a';
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i]; j++) {
                df.append(c);

            }
            c++;
//            df.append(i);
        }
        StringBuilder qsf = new StringBuilder();

        int a =0;
        int b= 0;
        c = 'A';
        for (int i = 0; i < d.length; i++) {

            b = ft[i] - q[i];
            // a to b add _
            // b to ft add -

            for (int j = a; j < b; j++) {
                qsf.append("_");
            }
            for (int j = b; j < ft[i]; j++) {
                qsf.append(c);
            }
            c++;
//            qsf.append("|");

            a = ft[i];



        }

        System.out.println(df);
        System.out.println(qsf);

        return f;


    }




}
