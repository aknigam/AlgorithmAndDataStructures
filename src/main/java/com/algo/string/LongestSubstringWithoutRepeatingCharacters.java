package com.algo.string;

public class LongestSubstringWithoutRepeatingCharacters {


    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters m = new LongestSubstringWithoutRepeatingCharacters();
        m.lengthOfLongestSubstring("au");
        m.lengthOfLongestSubstring("abcabdebb");
        m.lengthOfLongestSubstring("abcabcbb");
        m.lengthOfLongestSubstring("s");
        m.lengthOfLongestSubstring("");
        m.lengthOfLongestSubstring(" ");

    }

    public int lengthOfLongestSubstring(String s) {

        if(s.length() == 0) {
            System.out.println(-1 +" "+ -1 + " , len = "+ 0);
            return 0;
        }
        char[] input = s.toCharArray();
        int st =0 , et = 0;
        int maxLen =1;

        int cs =0 , ce = 0;
        int currlen = 1;

        int[] keyVal = getIndex();

        int size =  input.length;

        for (int i = 0; i < size; i++) {

            int indx =  input[i] - 32;
            int indexVal = keyVal[indx];
            if(indexVal < cs) {
                keyVal[indx] =  i;
                ce = i;

            }
            else {
                keyVal[indx] =  i;
                currlen = ce - cs  + 1;

                if(currlen > maxLen){
                    st = cs ; et = ce;
                    maxLen = currlen;
                }

                cs = indexVal + 1;
                ce = i;
            }
        }

        currlen = ce - cs  + 1;

        if(currlen > maxLen){
            st = cs ; et = ce;
            maxLen = currlen;
        }

        System.out.println(s + " >>> " +st +" "+ et + " , len = "+ maxLen);
        return maxLen;

    }

    public int[] getIndex() {
        int[] i = new int[96];
        for (int j = 0; j < 96; j++) {
            i[j] = -1;
        }
        return i;
    }
}
