package com.algo.leetcode;

import java.util.List;


/*

https://leetcode.com/problems/remove-nth-node-from-end-of-list/

pa
Refer https://www.geeksforgeeks.org/delete-nth-node-from-the-end-of-the-given-linked-list/ for solution
 */
public class RemoveNthFromEnd {


    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {


            ListNode cn = head;
            ListNode ps = head;
            ListNode pss = head;

            int i = 1;
            while (true) {

                cn = cn.next;
                if(cn == null) {
                    break;
                }
                ++i;
                if(i%n == 0) {
                    pss = ps;
                    ps = cn;
                }
            }

            return null;
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
