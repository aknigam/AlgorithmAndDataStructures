package com.algo;

public class AppleOrchard {


    public static void main(String[] args) {

    }

    /**
     * If gap + 1 == K or L then check the valuesIf gap + 1 == K or L then check the values
     *
     * s=  max(K, L)
     * if gap+1 >= s
     * then add 1st element to first 's' elements and check the sum
     * recompute sum if required and move forward
     *
     *
     *
     *
     *
     *
     *
     *
     * @param a - represents apple orchard
     * @param k - no of trees allocated to 1st person
     * @param l - no of trees allocated to 2nd person
     * @return max no. of apples that can be picked by 2 people combined
     */
    public int maxApples(int a[], int k, int l){

        int size = a.length;
        if(size < k || size < l) {
            return  -1;
        }
        if(size < (k + l)) {
            return  -1;
        }

        if(size == (k + l)) {
            return  sumArray(a, 0, a.length);
        }


        int s =  k + l;





        return -1;
    }

    private int sumArray(int[] a, int start, int end) {
        return 0;
    }

}
