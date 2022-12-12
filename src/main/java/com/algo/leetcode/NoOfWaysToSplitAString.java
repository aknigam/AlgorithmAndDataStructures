package com.algo.leetcode;


/**
 * https://leetcode.com/problems/number-of-ways-to-split-a-string/
 */
public class NoOfWaysToSplitAString {

    public static void main(String[] args) {
        NoOfWaysToSplitAString solution = new NoOfWaysToSplitAString();

        System.out.println(solution.numWays("10000"));
//        System.out.println(solution.numWays("0000100010001000"));
    }

    public int numWays(String s) {



        char[] ca = s.toCharArray();
        int len = ca.length;
        if(len < 3){
            return 0;
        }


        Node head = null;
        Node current = null;
        int onesCount =0;
        for (int i = 0; i < ca.length; i++) {
            if(ca[i] == '1') {
                onesCount++;
                if(head == null) {
                    head = Node.of(i);
                    current = head;
                } else {
                    Node nn = Node.of(i);
                    current.setNext(nn);
                    current = nn;
                }
            }
        }

        if(onesCount == 0) {
            long count = 0;
            for (int i = len -1; i >= 2; i--) {
                count = count+ i-1;
            }
            return (int) count;

        }
        if(onesCount%3 != 0) {
            return 0;
        }
        int onesPerSection = onesCount / 3;

        int counterOne = 0;
        int counterTwo = 0;
        int a =0 , b =0;
        current = head;
        while (current != null) {
            if(counterOne < onesPerSection) {
                counterOne++;
                if(counterOne ==  onesPerSection) {
                    a = current.next.index - current.index;
                }
            } else if(counterTwo < onesPerSection){
                counterTwo++;
                if(counterTwo ==  onesPerSection) {
                    b = current.next.index - current.index;
                    break;
                }
            }
            current = current.next;

        }



        return a*b;
    }

    static class Node {
        int index;
        Node next;

        public Node(int index) {
            this.index = index;
        }

        public static Node of(int index) {
            return new Node(index);
        }

        public void setNext(Node nxt) {
            next = nxt;
        }

        @Override
        public String toString() {
            return "{" + index + ", " + next + '}';
        }
    }
}
