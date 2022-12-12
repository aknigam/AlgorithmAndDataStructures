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
            ListNode prevFirst = null;
            // after swapping the second node becomes head. So saving the new head after swapping
            head = first == null ? null : first.next;
            int i=0;

            while (first != null) {
                prevFirst = swapPair(prevFirst, first, first.next);

                if(first == null ) {
                    break;
                }
                first = first.next;

            }

            return head;
        }

        private ListNode swapPair(ListNode prevFirst, ListNode first, ListNode second) {


            if(second != null) {
                // by now the previous first is the second and it needs to point the first after swapping
                if(prevFirst != null) {
                    prevFirst.next = second;
                }
                first.next = second.next;
                second.next = first;
            }

            return first;
        }
    }
}
