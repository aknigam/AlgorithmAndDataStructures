package com.algo.string;

import java.util.Arrays;

/**
 * Created by a.nigam on 24/06/17.
 */
public class ZBoxAlgorithm {


    public static void main(String[] args) {
        char[] s = new char[]{'a','a','b','a','a','b','c','a','x','a','a','b','a','a','b','c','y'};
//        char[] s = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','previous','q'};
        int[] z = preProcesspattern(s).z;
        System.out.println(Arrays.toString(z)); // [0, 1, 0, 3, 1, 0, 0, 1, 0, 7, 1, 0, 3, 1, 0, 0, 0]

        System.out.println(isSubString("ajbaha", "jkhkjhkajbahahjhkjhjkhajbahakhkjhajbaha"));
        System.out.println(isSubString("ajbahahjh", "jkhkjhkajbahahjhkjhjkhajbahakhkjhajbaha"));

        System.out.println("isCircularRotation: "+isCircularRotation("defabc",  "abcdef"));
        System.out.println("isCircularRotation: "+isCircularRotation("ababab",  "bababa"));
        System.out.println("isCircularRotation: "+isCircularRotation("aaaaaa",  "aaaaaa"));

        System.out.println("isSomeCircularRotation: "+isSomeCircularRotation("catg",  "accatggc"));
        System.out.println("isSomeCircularRotation: "+isSomeCircularRotation("cat",  "accatggc"));
        System.out.println("isSomeCircularRotation: "+isSomeCircularRotation("cac",  "accatggc"));
        System.out.println("isSomeCircularRotation: "+isSomeCircularRotation("tggcacca",  "accatggc"));
        System.out.println("isSomeCircularRotation: "+isSomeCircularRotation("cab",  "accatggc"));

        System.out.println("suffixprefixMatching: "+suffixprefixMatching("cabddd",  "bdddccatggc"));
    }

    /**
     * Now give an O(n)-time algorithm that takes S and β as input, finds every maximal tandem array of β, and outputs
     * the pair (s, k) for each occurrence. Since maximal tandem arrays of a given base can overlap, a naive algorithm
     * would establish only an O(n2)-time bound.
     *
     *
     */
    static void tandemArrays(String s, String b)
    {


    }


    /**
     * 3.  Suffix-prefix matching. Give an algorithm that takes in two strings α and β, of lengths n and m, and finds
     * the longest suffix of α that exactly matches a prefix of β. The algorithm should run in O(n + m) time.
     *
     * Algo:-
     * 1) concatenate s = b + $ + a;
     * 2) as soon as r reaches end of s we are done. if r is not at the end at the end of interation then there is no sol.
     *
     */
    static String suffixprefixMatching(String a, String b)
    {
        String s = b +"$"+ a;
        ZBoxResult res = preProcesspattern(s.toCharArray());
        if(res.r == (s.length()-1))
        {
            return s.substring(res.l , res.r+1);
        }

        return "NA";
    }

    /**
     * Length of the string 'b' can be more than/equal to that of 'a'.
     *
     * b - is a circular string
     * a - is the given string
     */
    static boolean isSomeCircularRotation(String a, String b){

        if(a.length()> b.length())
            return false;
        if(a.length()== b.length())
            return isCircularRotation(a, b);

        String s = a +"$"+ b;
        int[] z = preProcesspatternCircular(s.toCharArray(), a.length()).z;
        for (int i = a.length()+1; i < s.length(); i++) {
            if(z[i] == a.length()){
//                System.out.println(true);
                return true;
            }
        }
        return false;

    }

    /**
     * 1.  Use the existence of a linear-time exact matching algorithm to solve the following problem in linear time.
     * Given two strings α and β determine if α is a circular (or cyclic) rotation of β, that is,
     *
     * a) if α and β have the same length and
     * b) α consists of a suffix of β followed by a prefix of β.
     *
     * For example, defabc is a circular rotation of abcdef. This is a classic problem with a very elegant solution.
     *
     * Also refer: http://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other/
     * @param a
     * @param b
     * @return
     */
    static boolean isCircularRotation(String a, String b){
        /*
        Find the prefix of a in b.
        Then compare remaining chars, they should be equal
         */

        if(a.length() != b.length())
        {
            return false;
        }

        String s = a +"$"+ b;
        int[] z = preProcesspattern(s.toCharArray()).z;

        int zmax = 0;
        int imax= -1;
        for (int i = a.length()+1; i < s.length(); i++) {
            if(z[i] > zmax){
                zmax = z[i];
                imax = i;
            }
        }

        if(zmax ==  a.length()){
            return true;
        }

        int ai = zmax;
        int bi = 0;


        char[] achar = a.toCharArray();
        char[] bchar = b.toCharArray();


        for (int k = 0; (ai +k) < achar.length; k++) {
            if(achar[ai + k] !=  bchar[bi + k]){
                return false;
            }
        }
        return true;


    }

    static boolean isSubString(String p , String t){

        if(p.length()> t.length())
            return false;

        String s = p +"$"+ t;
        int[] z = preProcesspattern(s.toCharArray()).z;
        for (int i = p.length()+1; i < s.length(); i++) {
            if(z[i] == p.length()){
//                System.out.println(true);
                return true;
            }
        }
        return false;
    }

    private static ZBoxResult preProcesspattern(char[] s) {


        int r =0; // its an index
        int l=0;  // its an index
        int k = 0; // its an index i from beginning
        int b =0 ; // this is the length
        int z[] = new int[s.length];
        init(z);
        int n = 0;

        for (int i = 1; i < s.length -1; i++) {

            if(i == 1 || i > r) {
                int j = 0;
                while ((i + j)< s.length && s[i + j] == s[j]) {
                    j++;n++;
                }
                n++;
                z[i] = j;

                r = i + j -1;
                l = i;

                continue;
            }

            else
            {
                k = i - l ;
                b = r - i +1; // beta
                if(z[k] < b)
                {
                    z[i] = z[k];
                }
                else
                {
                    int j = 0;
                    while((r + 1+ j) < s.length && s[r + 1+ j] == s[b+1+ j]){
                        j++;n++;
                    }
                    n++;
                    z[i] = r + 1 + j - i;
                    r = r + j;
                    l = i;
                }
            }

        }



//        System.out.println(n+", length = "+ s.length);
//        print(z, l , r, s);

        return  new ZBoxResult(z, r, l);


    }

    /**
     * 2.  Similar to Exercise 1, give a linear-time algorithm to determine whether a linear string α is a substring of
     * a circular string β. A circular string of length n is a string in which character n is considered to precede
     * character 1 (see Figure 1.6). Another way to think about this problem is the following. Let image be the linear
     * string obtained from β starting at character 1 and ending at character n. Then α is a substring of circular
     * string β if and only if α is a substring of some circular rotation of image.
     *
     * @param s
     * @param lenthOfFirstString
     * @return
     */
    static ZBoxResult preProcesspatternCircular(char[] s, int lenthOfFirstString) {

        int r =-1; // its an index
        int l=0;  // its an index
        int k = 0; // its an index i from beginning
        int b =0 ; // this is the length
        int z[] = new int[s.length];
        init(z);
        int n = 0;

        for (int i = 1; i < s.length ; i++) {

            if(i == 1 || i > r) {

                int prefixIndex =0;
                int subIndex = i;
                l = i;
                while ( prefixIndex < lenthOfFirstString && s[subIndex] == s[prefixIndex]) {

                    z[i]++;
                    subIndex++;
                    prefixIndex++;
                    if((subIndex) == s.length){
                        subIndex = lenthOfFirstString+1;
                    }
                    r++; // r can go out of s length
                    n++;
                }
                n++;

                // l can be more than r
                continue;
            }

            else
            {
                k = i - l ;
                b = r - i +1; // beta
                if(z[k] < b)
                {
                    z[i] = z[k];
                }
                else
                {

                    int prefixIndex =b +1;
                    int subIndex = r +1;
                    if(subIndex >= s.length)
                    {
                        subIndex = subIndex - s.length;
                    }
                    z[i] = b;
                    int j = 0;
                    l = i;
                    while(prefixIndex < lenthOfFirstString && s[subIndex] == s[prefixIndex])
                    {
                        z[i]++;
                        subIndex++;
                        prefixIndex++;
                        if((subIndex) == s.length){
                            subIndex = lenthOfFirstString+1;
                        }
                        r++;
                    }
                    n++;

                }
            }

        }


//        System.out.println(n+", length = "+ s.length);
//        print(z, l , r, s);
        return  new ZBoxResult(z, r, l);



    }

    private static void init(int[] z) {
        for (int i = 0; i < z.length; i++) {
            z[i] = 0;
        }
    }

    private static void print(int[] z, int l, int r, char[] s) {
//        System.out.println("r="+ r);
//        System.out.println("l="+ r);
        for (int i = 0; i < z.length; i++) {
            System.out.println("z("+(i+1)+")="+z[i]+" , "+ s[i]);
        }

    }

    static class ZBoxResult{
        int z[];
        int r;
        int l;

        public ZBoxResult(int[] z, int r, int l) {
            this.z = z;
            this.l =l;
            this.r =r;
        }
    }
}
