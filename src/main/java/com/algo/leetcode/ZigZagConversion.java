package com.algo.leetcode;

public class ZigZagConversion {


    public static void main(String[] args) {
        Solution s = new Solution();
        String c = s.convert("AB", 1);

        System.out.println(c);
    }
    static class Solution {
        public String convert(String s, int numRows) {

            char[] ca = s.toCharArray();
            int len = s.length();
            RowLinkedList[] rows = new RowLinkedList[numRows];
            int j = 0;
            int direction = 0;
            for (int c = 0, r = 0; ; ) {
                for (; ;) {

                    if (c == 0) {
                        rows[r] = new RowLinkedList(ca[j++]);
                    }
                    else {
                        rows[r].append(ca[j++]);
                    }

                    if ((direction == 0 && r + 1 == numRows)
                            || (direction == 1 && r == 0)) {
                        direction = direction == 0 ? 1 : 0;
                    }

                    if(numRows > 1) {
                        r = direction == 0 ? r + 1 : r - 1;
                    }
                    if(direction == 1) {
                        c++;
                        break;
                    }
                    if(j >= len) {
                        break;
                    }


                }
                if(j >= len) {
                    break;
                }



            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numRows ; i++) {
                if(rows[i] != null) {
                    sb.append(rows[i].sb);
                }
            }

            return sb.toString();
        }

        static class RowLinkedList {
            char s;
            StringBuilder sb = new StringBuilder();

            void append(char c){
                sb.append(c);
            }

            public RowLinkedList(char c) {
                s =c;
                sb.append(c);
            }

            @Override
            public String toString() {
                return sb.toString();
            }
        }
    }
}
