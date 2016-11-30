package com.crackingthecodingtnterview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 25/11/16.
 */
public class Recursion {


    public static void main(String[] args) {
        System.out.println(0xf/8); //-268435457
        System.out.println(Integer.toBinaryString(0xFFFFFFF));
//        System.out.println(Integer.MAX_VALUE+"\t"+Integer.toBinaryString(Integer.MAX_VALUE));
//        System.out.println(Integer.MAX_VALUE+"\t"+Integer.toHexString(Integer.MAX_VALUE));
//        System.out.println(Integer.MIN_VALUE+"\t"+Integer.toBinaryString(Integer.MIN_VALUE));
//        System.out.println(Integer.MIN_VALUE+"\t"+Integer.toHexString(Integer.MIN_VALUE));
    }

    public static void printParenthesisPairs(){

    }

    private static List<String> getAllpermutations(String s) {
        if(s == null){
            return null;
        }

        if(s.length() == 1){
            List<String> p = new ArrayList<>();
            p.add(s);
            return p;
        }

        char f = s.charAt(0);
        List<String> lo = new ArrayList<>();
        List<String> li = getAllpermutations(s.substring(1));

        for (int j = 0; j < li.size(); j++) {
            String y = li.get(j);
            for (int k = 0; k <= y.length(); k++) {
                lo.add(insertCharAt(y, f, k));
            }

        }




        return lo;
    }

    private static String insertCharAt(String y, char f, int k) {
        String start = y.substring(0, k);
        String end = y.substring(k);
        return start + f + end;
    }

    public static int generatFibonaciNumberRecursive(int index){

        if(index == 1){
            return 0;
        }
        if(index == 2){
            return 1;
        }

        return generatFibonaciNumberRecursive(index -1) + generatFibonaciNumberRecursive(index -2);
    }
    public static int generatFibonaciNumberIterative(int index){

        if(index == 1){
            return 0;
        }
        if(index == 2){
            return 1;
        }

        int p1 = 0;
        int p2 = 1;
        int temp;
        for (int i = 3; i <= index; i++) {
            temp = p2;
            p2 = p1 + p2;
            p1 = temp;
        }
        return p2;

    }
}
