package com.crackingthecodingtnterview;

/**
 * Created by a.nigam on 25/11/16.
 */
public class Recursion {


    public static void main(String[] args) {
        System.out.println(generatFibonaciNumberIterative(13));
        System.out.println(generatFibonaciNumberRecursive(13));
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
