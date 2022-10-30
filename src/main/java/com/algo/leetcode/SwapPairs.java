package com.algo.leetcode;

/*
https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class SwapPairs {


    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.swapPairs(ListNode.of(1, ListNode.of(2, ListNode.of(3, ListNode.of(4, null))))));
        System.out.println(s.swapPairs(ListNode.of(1, ListNode.of(2, ListNode.of(3, ListNode.of(4, ListNode.of(5, null)))))));
        System.out.println(s.swapPairs(ListNode.of(1, null)));
    }

    static class Solution {


        public ListNode swapPairs(ListNode head) {


            if(head == null || head.next == null) {
                return head;
            }


            ListNode first = head;
            ListNode prev = null;
            head = first == null ? null : first.next;
            int i=0;

            while (first != null) {
                prev = swapPair(prev, first, first.next);

                if(first == null ) {
                    break;
                }
                first = first.next;

            }

            return head;
        }

        private ListNode swapPair(ListNode prev, ListNode first, ListNode second) {


            if(second != null) {
                if(prev != null) {
                    prev.next = second;
                }
                first.next = second.next;
                second.next = first;
            }

            return first;
        }
    }
}
