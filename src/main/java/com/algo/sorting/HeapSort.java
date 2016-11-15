package com.algo.sorting;

import java.util.LinkedList;

/**
 *
 */
public class HeapSort extends AbstractBaseSort{


    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        int[] a = {27,17,3,16,13,10,1,5,7,12,4,8,9,0};
//        int[] a = {45, 7, 6, 56, 4,5,78} ;// , 43, 57, 7, 9, 89};
        hs.sort(a);



    }

    public void buildMaxheap(int[] a){

        int len = a.length;

        for (int i = (len / 2); i >=0; i--) {
            System.out.println("max heapify: "+ i);
            maxheapify(a, i, a.length);
            printArray(a);

        }

    }

    public void maxheapify(int[] a, int index , int heapSize){

        int p = index;
        int l =  2*p + 1;
        int r =  2*p + 2;
        int largest;

        while (l < heapSize || r < heapSize) {

//            System.out.println("Root - left - right:\t\t"+a[p]+"("+p+")\t"+ a[l]+"("+l+") \t"+a[r]+"("+r+")");
            if (l < heapSize && a[l] > a[p]) {
                largest = l;
            }else{
                largest = p;
            }

            if (r < heapSize && a[r] > a[largest]) {
                largest =r;
            }
            if(largest == p){
                break;
            }
            int temp =  a[largest];
            a[largest] = a[p];
            a[p] = temp;
            p = largest;
            l =  2*p + 1;
            r =  2*p + 2;
            continue;
        }

    }



    @Override
    protected void sort(int[] a) {
        buildMaxheap(a);
        printArray(a);
        for (int i = a.length -1 ; i >= 0; i--) {
            swap(a, i, 0);
            maxheapify(a, 0, i);
        }
        printArray(a);

    }


    /*
    ********************************************************************************************
            UTIL FUNCTIONS
    ********************************************************************************************
     */
    public void minheapify(int[] a, int index ){

    }

    public int getWidth(int[] a){

        int wp = (int) (Math.log10(a.length)/Math.log10(2));
        int w = (int) Math.pow(2, wp);

//        System.out.println(a.length +"\t"+wp+ "\t"+w);


        return w;
    }

    public void printBFS(int[] a){

        LinkedList<Integer> s  = new LinkedList<Integer>();
        s.offer(0);

        int w = getWidth(a);
        int l = a.length;

        StringBuilder sb= new StringBuilder();

        int e;
        while (!s.isEmpty()){
            int size = s.size();
            int[] temp = new int[size*2];
            int i = 0;
            addTabs(sb, w);

            while (!s.isEmpty()) {
                e = s.poll();

                w = w/2;
                sb.append(a[e]).append("\t");


                int leftChildIndex =2*e +1;
                int rightChildIndex =2*e +2;

                if(leftChildIndex < l) {
                    temp[i++] = leftChildIndex;

                }
                if(rightChildIndex < l) {
                    temp[i++] = rightChildIndex;
                }

            }
            sb.append("\n");
            for (int j = 0; j < i; j++) {
                s.offer(temp[j]);
            }



        }

        System.out.println(sb);

    }

    private void addTabs(StringBuilder sb, int w) {
        for (int i = 0; i < w / 2; i++) {
            sb.append("\t.");
        }
    }

}
