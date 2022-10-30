package com.algo.leetcode;

/*
https://leetcode.com/problems/add-two-numbers/


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

 */
public class AddTwoNumbers {


    public static void main(String[] args) {
        Solution s = new Solution();


        int num1= 42;
        Solution.ListNode l1 = s.numToList(9, 3);
        //1,9,9,9,9,9,9,9,9,9
        Solution.ListNode l2 = s.numToList(999999991, 10);

        Solution.ListNode sum = s.addTwoNumbers(l1, l2);

        Solution.Value sumVal = s.getNumber(sum);
        System.out.println(sumVal.num);


    }
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


            Value value1 = getNumber(l1);
            Value value2 = getNumber(l2);


            int num1 = value1.num;
            int num2 = value2.num;

            int sum = num1 + num2;
            int digits = Math.max(value1.digits , value2.digits)+ 1;

            return numToList(sum, digits);

        }

        public ListNode numToList(int number, int digits) {
            if(digits == 0){
                return null;
            }
            if(digits == 1) {
                return new ListNode(number);
            }

            int place = 1;
            int base = 10;

            ListNode node = null;
            ListNode head = null;


            for (int i = 0; i < digits; i++) {

                int divisor = (int) Math.pow(base, place* (i+1));

                int val = (number%divisor)/((int) Math.pow(base, place* (i))) ;

                if(i == digits -1 && val == 0) {
                    break;
                }

                if(node == null) {
                    node = new ListNode(val);
                }
                else {
                    node.next = new ListNode(val);
                    node = node.next;
                }

                if(i == 0) {
                    head = node;
                }

            }
            return head;
        }

        public Value getNumber(ListNode l1) {

            if(l1 == null) {
                return new Value(0,0);
            }
            int number = l1.val;
            int place = 1;
            int base = 10;

            ListNode node = l1;
            while (node.next != null) {
                ListNode nxt = node.next;
                if(nxt == null) {
                    break;
                }
                number = number + nxt.val * ((int)Math.pow(base, place));
                place++;

                node = nxt;
            }

            return new Value(number, place);
        }

        static class Value{
            int num;
            int digits;

            public Value(int number, int digits) {
                num =number;
                this.digits = digits;
            }
        }

        static class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }
    }



}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
