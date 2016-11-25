package com.crackingthecodingtnterview;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by a.nigam on 24/11/16.
 */
public class BitManipulation {

    private static boolean debug = false;

    public static void main(String[] args) {

        int[] bitSums = new int[7];
        BitInteger[] b = new BitInteger[32];
        for (int i = 0; i < 32; i++) {
            b[i] = new BitInteger(i+1);
        }
        findMissingInteger(b);


    }

    static class BitInteger{
        private final String s;
        int i;
        public BitInteger(int i){
            this.i = i;
            s = Integer.toBinaryString(i);
        }

        public int bitAt(int index){
            if((s.length() -1) < index)
                return 0;
            return s.charAt(s.length() -1 -index) == '0'? 0 : 1;
        }

        @Override
        public String toString() {
            return String.valueOf(i);
        }
    }

    public static int findMissingInteger(BitInteger[] a) {
        System.out.println(Integer.toBinaryString(a.length));
//        return findMissingInteger(a, Integer.toBinaryString(a.length).length());
        return findMissingInteger(a, 0);

    }

    private static int findMissingInteger(BitInteger[] a, int column) {
        if(column < 0){
            return 0;
        }

        ArrayList<BitInteger> zeros = new ArrayList<>();
        ArrayList<BitInteger> ones = new ArrayList<>();

        for (BitInteger b :a){
            if(b.bitAt(column) == 0){
                ones.add(b);
            }
            else{
                zeros.add(b);
            }
        }

        if(zeros.size() >= ones.size()){
            return (findMissingInteger(a, column -1)) << 1 | 0;
        }else{
            return (findMissingInteger(a, column -1)) << 1 | 1;
        }
    }

    public static void findMissingIntegerOld(BitInteger[] a){

        int[] bitSums = new int[32];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < 4; j++) {
                bitSums[j]= bitSums[j] + a[i].bitAt(j);
            }
            if(debug)
                System.out.println((i+1) +"\t"+ Arrays.toString(bitSums));
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSums.length; i++) {
            if(bitSums[i] == 0){
                sb.append(bitSums[i]);
            }
            if(bitSums[i] == 15){
                sb.append(0);
            }
            if(bitSums[i] == 16){
                sb.append(1);
            }
        }

        System.out.println(sb);


    }
    public static int swapOddEvenBits(int a){
        int l = (a & 0xaaaaaaaa) >> 1;
        int r = (a & 0x55555555) << 1;
        return  l | r;
    }

    public static int bitSwapRequired(int a, int b){

        int c1 = 0;
        while (a > 0){
            c1 = c1 + (a & 1);
            a = a >> 1;
        }

        int c2 = 0;
        while (b > 0){
            c2 = c2 + (b & 1);
            b = b >> 1;
        }



        return Math.abs(c1 - c2);

    }

    public static String toBinaryString(int n){
        String s = "";
        while (n > 0){
            int r = n % 2;
            n = n >> 1;
            s = r+ s;
            if(debug){
                System.out.println("------------------------");
                System.out.println(Integer.toBinaryString(r));
                System.out.println(Integer.toBinaryString(n ));
                System.out.println(s);
            }
        }

        return s;
    }
    public static int updateBits(int n, int m, int i, int j){

        int max = ~0;
        if(debug){
            System.out.println(Integer.toBinaryString(1 << j));
            System.out.println(Integer.toBinaryString((1 << j) -1));
            System.out.println(Integer.toBinaryString(~0 -(1 << j) -1));
        }
        int left = max - ((1 << j) - 1);

        int right = ((1 << i ) - 1);

        if(debug){
            System.out.println(Integer.toBinaryString(1 << i));
            System.out.println(Integer.toBinaryString((1 << i) -1));
        }

        int mask =  left | right;

        if(debug){
            System.out.println(Integer.toBinaryString(mask));
            System.out.println(Integer.toBinaryString((m << i)));

        }

        return (n & mask) | (m << i);

    }
}
