package com.algo.dynamicprogramming;

import com.algo.util.ArrayUtils;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

/**
 * Created by a.nigam on 22/02/17.
 */
public class SprintPlanning {

    private static List<F[]> combinations = new ArrayList<>();



    static class F {
        int dev;
        int qe;
        String desc;
        boolean v=false;
        public F(String description, int d, int q){
            desc = description;
            dev =d;
            qe =q;
        }

        @Override
        public String toString() {
            return desc+"("+dev+","+ qe+")";
        }
    }

    static class N{
        F f;
        N[] c;
        boolean v=false;

        public N(F f) {
            this.f =f;
        }

        @Override
        public String toString() {
            if(c != null)
                return f.toString()+"<"+c.length+"> ";
            else
                return f.toString();
        }
    }
    public static void main(String[] args) {

        F[] f = {
                new F("a", 5, 2)
                ,new F("b", 5, 1)
                ,new F("c",6, 2)
                ,new F("d",5, 3)
                ,new F("e",4, 3)
                ,new F("f",2, 1)
                ,new F("g",1, 1)

                };

        N node = new N(new F("o", 0,0));
        createTree(f, node, f.length);
//        System.out.println(node);
        StringBuilder s = new StringBuilder();
        print(node);


        int bestFinishTime = Integer.MAX_VALUE;
        for (int i = 0; i < combinations.size(); i++) {
            int f1 = findFinshTime(combinations.get(i));
            bestFinishTime = bestFinishTime > f1 ? f1 : bestFinishTime;
        }

        System.out.println("Sum of dev\t"+sumDev(f));
        System.out.println("Sum of qe\t"+sumQe(f));
        System.out.println("Best finsih time\t"+ bestFinishTime);

    }

    private static int sumQe(F[] f) {
        int sum = 0;
        for (int i = 0; i < f.length; i++) {
            sum = sum+ f[i].qe;
        }
        return sum;
    }

    private static int sumDev(F[] f) {
        int sum = 0;
        for (int i = 0; i < f.length; i++) {
            sum = sum+ f[i].dev;
        }
        return sum;
    }


    public static void createTree(F[] f, N node, int s){

        node.c = new N[s];
        N n= null;
        int j =0;
        for (int i = 0; i < f.length; i++) {
            if(f[i].v){
                continue;
            }
            n = new N(f[i]);
            node.c[j++] = n;
            f[i].v = true;
            if(s >1 )
                createTree(f, n, s-1);

            f[i].v = false;

        }


    }

    public static int findFinshTime(F[] features) {


        Integer[] ft = new Integer[features.length];

        ArrayUtils.initArraygeneric(ft, 0);

        int f = 0;
        int dt = 0;

        for (int i = 0; i < features.length; i++) {
            dt = dt + features[i].dev;
            f = Math.max(dt , f) + features[i].qe;
            ft[i] = f;
        }



        ArrayUtils.printGeneric(ft);
        StringBuilder df = new StringBuilder();
        char c = 'a';
        for (int i = 0; i < features.length; i++) {
            for (int j = 0; j < features[i].dev; j++) {
                df.append(features[i].desc);

            }
            c++;

        }
        StringBuilder qsf = new StringBuilder();

        int a =0;
        int b= 0;
        c = 'A';
        for (int i = 0; i < features.length; i++) {

            b = ft[i] - features[i].qe;
            // a to b add _
            // b to ft add -

            for (int j = a; j < b; j++) {
                qsf.append("_");
            }
            for (int j = b; j < ft[i]; j++) {
                qsf.append(features[i].desc.toUpperCase());
            }
            c++;


            a = ft[i];



        }

        System.out.println("Finish time\t"+ f+"\t\t"+ArrayUtils.printGeneric(features, 1, features.length));
//        ArrayUtils.printGeneric(features, 1, features.length);
//        System.out.println(df);
//        System.out.println(qsf);

        return f;


    }

    private static void print(N node) {

        Stack q = new Stack();
        print(node , q);

    }

    private static void print(N node, Stack st) {
        if(node == null){
            return;
        }
        st.push(node.f);
        if(node.c == null){
            printStack(st);

        }else {
            for (int i = 0; i < node.c.length; i++) {
                print(node.c[i], st);
            }
        }
        st.pop();
    }
    static int k;
    private static void printStack(Stack st) {
        k++;
//        System.out.println(k+" - S\t"+st);
        F[] f = new F[st.size()];
        st.copyInto(f);
        combinations.add(f);

    }


}
