package com.algo.leetcode;

public class RotateImage {

    public static void main(String[] args) {


        int[][] matrix = new int[][]{{1,2,3,6},{4,5,6,8},{7,8,9,7},{7,8,9,7}};

        int n =matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        RotateImage sol = new RotateImage();
        sol.rotate(matrix, 0);


    }

    public void rotate(int[][] matrix, int start) {


        int n = matrix.length;
        if(start == n-1) {
            return;
        }


        swapFirsRowCol(matrix, start);
        System.out.println("\n");
        swapFirsRowCol(matrix, start+1);

//        rotate(matrix, start +1);



    }

    private void swapFirsRowCol(int[][] matrix, int start) {
        int n = matrix.length;


        for (int r = start; r < n - start; r++) {

            for (int c = start; c < n -start ; c++) {
                int rc = c;
                int cc = n- r-1;
                System.out.println("Swapping a["+r+","+c+"] with a["+ rc+","+ cc+"]");
            }
            break;

        }


    }
}
