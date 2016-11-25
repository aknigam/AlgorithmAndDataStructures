package com.crackingthecodingtnterview;

import com.algo.util.ArrayUtils;

import java.util.Arrays;

/**
 * Created by a.nigam on 22/11/16.
 */
public class chapter1 {

    private static boolean debug = false;

    public static void main(String[] args) {
        int[][] a = {
            {0,2,3},
            {3,1,5},
            {6,7,8}
        };
        ArrayUtils.print2DIntArray(a);
        setRowAndColumnToZero(a);
    }

    public static void setRowAndColumnToZero(int[][] a){

        int len = a.length;
        boolean[] row = new boolean[len];
        ArrayUtils.initBoolArray(row, false);
        boolean[] col = new boolean[len];
        ArrayUtils.initBoolArray(col, false);

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len; j++) {
                if(a[i][j] == 0){
                    row[i] = true;
                    col[i] = true;
                }
            }
        }

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len; j++) {
                if( row[i] == true || col[j] == true){
                    a[i][j] = 0;
                }
            }
        }
        ArrayUtils.print2DIntArray(a);

    }

    public static void rotatematrixInPlace(String[][] a){


        int l = a.length-1;
        for (int i = l; i >= 0 ; i--) {

            for (int j = 0 ; j < i; j++) {

                String h = a[i][l - j];
                String v = a[j][l - i];
                ArrayUtils.swap2dArrayElements(a, i, l-j , j, l-i, debug);
            }
        }
        if(debug)
            ArrayUtils.print2DStringArray(a);
        // flip

        for (int r = 0; r < a.length/2; r++) {
            for (int c = 0; c < a.length; c++) {
                ArrayUtils.swap2dArrayElements(a, r, c, l - r, c, debug);
            }

        }
        System.out.println("In place rotated array -->");
        ArrayUtils.print2DStringArray(a);



    }
    public static String[][] rotatematrix(String[][] a){
        String[][] ro = new String[a.length][a.length];

        int rn, cn;
        for (int r = 1; r <= a.length; r++) {
            for (int c = 1; c <= a.length ; c++) {
                rn = c;
                cn = a.length - r +1;
                ro[rn-1][cn-1] = a[r-1][c-1];
            }

        }
        System.out.println("New rotated matrix ---------->");
        ArrayUtils.print2DStringArray(ro);
        return ro;
    }

    //1.5
    public static void replaceFun(char[] str ){

        if(str.length == 0){
            return;
        }

        int spaceCount = 0;
        // count no of spaces
        for (int i = 0; i < str.length; i++) {
            if(str[i] == ' '){
                spaceCount++;
            }
        }

        char[] replacedStr = new char[str.length + spaceCount * 2];


        for (int i = 0, j = 0; i < str.length; i++) {
            if(str[i] == ' '){
                replacedStr[j++] = '%';
                replacedStr[j++] = '2';
                replacedStr[j++] = '0';
            }
            else{
                replacedStr[j++] = str[i];
            }
        }
        System.out.println(Arrays.toString(str));
        System.out.println(Arrays.toString(replacedStr));


    }
    // 1.4
    public static boolean areAnagrams(String s1, String s2){

        if( s1 == null && s2 == null || s1.length() ==0 && s2.length() == 0){
            System.out.println("Empty or null strings");
            return true;
        }
        if(( s1 == null && s2 != null) || ( s2 == null && s1 != null) ){
            System.out.println("One string is null");
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1 !=  len2){
            System.out.println("String lengths do not match");
            return false;
        }

        int[] letters = new int[256];
        int numOfUniqueChars = 0;
        int numOfCompletedChars = 0;

        for (int i = 0; i < s1.length(); i++) {
            if(letters[s1.charAt(i)] ==  0 ){
                ++numOfUniqueChars;
            }
            ++letters[s1.charAt(i)];
        }
        // DEBUG MESSAGES START
        if(debug) {
            System.out.println("No. of unique chars:\t" + numOfUniqueChars);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < letters.length; i++) {
                sb.append(letters[i]).append(" ");
            }
            System.out.println(sb);
        }
        // DEBUG MESSAGES END
        for (int i = 0; i < s2.length(); i++) {
            if(letters[s2.charAt(i)] == 0){
                System.out.println("New character found ");
                return false;
            }
            --letters[s2.charAt(i)];
            if(letters[s2.charAt(i)] == 0){
                ++numOfCompletedChars;
                if(numOfCompletedChars == numOfUniqueChars){
                    System.out.println("Unique char count matches completed char count");
                    return i == (s2.length()-1);
                }
            }

        }
        System.out.println("No. of completed chars:\t"+ numOfCompletedChars);
        return false;
    }

    /**
     * There is another better implementation
     * @param str
     */
    public static void _1_3_removeDuplicatesFromString(char[] str){

        if(str == null || str.length ==1 ){
            return;
        }

        int len = str.length;

        int tail = 1;

        for (int i = 0; i < len; i++) {
            int j;
            for ( j = 0; j < tail; j++) {
                if(str[j] == str[i]) {
                    break;
                }
                if( j == tail){
                    str[tail] = str[i];
                    ++tail;
                }
            }
        }
        str[tail] =  0;

    }
    public static void _1_1_hasUniqueCharachers(String str) {
        int checker =0;
        System.out.println(str.length());
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if(debug) {
                System.out.println("-----------------");
                System.out.println((val) + "\tVal\t");
                System.out.println(Integer.toBinaryString(checker) + "\tChecker\t");
            }
            int x = 1 << val;
            int c = checker & x;
            if(debug) {
                System.out.println(Integer.toBinaryString(x) + "\tx = 1 << " + val + "\t");
                System.out.println(Integer.toBinaryString(c) + "\tc = checker & " + "(" + Integer.toBinaryString(x) + ")" + x + "\t");
            }
            if(c > 0){
                System.out.println(str.charAt(i)+ "\tDuplicate found: ");
            }else{
                System.out.println(" \tunique");
            }
            checker |= (1 << val);
            System.out.println(Integer.toBinaryString(checker)+ "\tchecker |= (1 << "+val+") \t");


        }
    }


    /**
     ********************************************************************************************************
     *
     *                                    D   E   M   O   S
     *
     ********************************************************************************************************
     */

    public static void rotateMatrixDemo(String[] args) {

        String[][] a = new String[][]{
                {"a11","a12","a13"},
                {"a21","a22","a23"},
                {"a31","a32","a33"}
        };
        System.out.println("Original array---->");
        ArrayUtils.print2DStringArray(a);


        a = rotatematrix(a);
        a = rotatematrix(a);
        a = rotatematrix(a);
        a = rotatematrix(a);
        rotatematrixInPlace(a);

    }


}
