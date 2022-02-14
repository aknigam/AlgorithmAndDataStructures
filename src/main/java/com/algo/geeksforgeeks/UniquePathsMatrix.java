package com.algo.geeksforgeeks;

/*
Refer: https://practice.geeksforgeeks.org/problems/number-of-unique-paths5339/1/?company[]=Walmart&company[]=Walmart&page=1&query=company[]Walmartpage1company[]Walmart#

Path is defined as a straight line
 */
public class UniquePathsMatrix
{

    public static int NumberOfPath(int rows, int cols)
    {
        if(rows <= 0 || cols <= 0) {
            return 0;
        }
        if(rows == 1 ){
            return 1;
        }
        if(cols == 1 ) {
            return 1;
        }

        return  NumberOfPath(rows-1, cols) + NumberOfPath(rows, cols -1);
    }

    public static void main(String[] args) {
        int paths = NumberOfPath(2, 2);
        System.out.println(paths);
    }
}
