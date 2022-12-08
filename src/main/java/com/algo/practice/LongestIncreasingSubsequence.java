package com.algo.practice;

import java.util.*;

public class LongestIncreasingSubsequence {


    public static void main(String[] args) {

        // the goals should be add the new element in the longest sequence as of now - of all eligible
        System.out.println(longestSubsequence(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
        System.out.println(longestSubsequence(new int[]{50,3,10,7,40,80}));
    }

    private static int longestSubsequence(int[] a) {


        List<DagNode> l= new ArrayList<>();
        Node<DagNode> start = new Node<>();

        for (int i = 0; i < a.length; i++) {
            insert(start, a[i]);
            sortList(l);
        }
        DagNode ss = l.get(0);
        System.out.println(ss);
        System.out.println(ss.maxLen +1);

        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        return ss.maxLen +1;

    }

    private static void sortList(List<DagNode> l) {
        Collections.sort(l, new Comparator<DagNode>() {
            @Override
            public int compare(DagNode o1, DagNode o2) {

                if (o1.maxLen == o2.maxLen) {
                    return 0;
                } else if (o1.maxLen > o2.maxLen) {
                    return -1;
                }
                return 1;
            }
        });
    }


    public static void insert(Node<DagNode> start, int e) {
//        sortList(endNodes);
        DagNode nn = DagNode.of(e);
        Iterator<DagNode> itr;

        Node<DagNode> node = start;

        while (node.hasNext()) {
            DagNode dn = node.next.val;

            if (dn.val < e) {
                dn.nextNode = nn;
                nn.prevNode = dn;
                nn.maxLen = dn.maxLen + 1;
                break;
            }

        }







//        endNodes.add(nn);


    }


    static class Node<T> {
        T val;
        Node<T> next;
        Node<T> prev;


        public boolean hasNext() {
            return next != null;
        }
    }

    static class DagNode {
        int val;
        List<DagNode> nextNodes;

        DagNode nextNode;
        DagNode prevNode;
        int maxLen = 0;

        public DagNode(int e) {
            val = e;
            nextNodes = new ArrayList<>();
        }

        public static DagNode of(int e) {
            return new DagNode(e);
        }

        @Override
        public String toString() {
            return "{" +val +
                    "["+(maxLen +1)+"], " + prevNode +
                    '}';
        }

    }
}
