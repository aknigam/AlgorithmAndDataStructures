package com.crackingthecodingtnterview;

/**
 * Created by a.nigam on 26/11/16.
 */
public class Moderate {

    private static boolean debug = true;

    public static void main(String[] args) {
        System.out.println(getMax(10, 12));
    }


    public static int getMax(int a , int b){

        int c = a - b;
        int k = (c >> 31) & 0x1;
        if(debug){
            System.out.println(Integer.toBinaryString(c >> 31));
        }
        return a- k*c;


    }

    public static int numZerosInFactorial(int n){

        if(n < 10){
            if(n >=5){
                return 1;
            }
            return 0;
        }
        int k = n / 10;
        int l = n % 10;
        int numZeros = k*2;

        if (l >= 5) {
            return numZeros+ 1;
        }
        return numZeros;

    }
}
