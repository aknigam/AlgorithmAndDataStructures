package com.algo.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Created by a.nigam on 21/11/16.
 */
public class BinaryTreeProblems {

    public static void main(String[] args) {
        int[] a ={1, 2, 3, 4, 5, 6, 7};
        BinaryTreeNode t = BinarySearch.drawBinaryTreeOfHeight(a, 2, 0, a.length-1, "");
        BTreePrinter.printTreeNode(t);
        Deque<Integer> s = new ArrayDeque<>(a.length);
        StringBuffer sb = new StringBuffer();
        convertTreeIntoStackPostOrderRecursive(t, s, sb);
        System.out.println("Traversal:\t"+sb);
        Iterator<Integer> itr = s.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }

    private static void convertTreeIntoStackInOrderNonRecursive(BinaryTreeNode root, Deque s, StringBuffer sb ) {

        if(root == null){
            return;
        }

        Deque<BinaryTreeNode> st =  new ArrayDeque<>();
        while(true){

            if(root!= null){
                st.push(root);
                root = root.left;
            }
            else{
                if(st.isEmpty()){
                    break;
                }
                root = st.pop();
                s.push(root.data);
                sb.append(root.data).append("\t");
                root = root.right;
            }


        }



    }

    private static void convertTreeIntoStackInOrderRecursive(BinaryTreeNode node, Deque s, StringBuffer sb ) {
        if(node == null){
            return;
        }
        convertTreeIntoStackInOrderRecursive(node.left, s, sb);
        s.push(node.data);
        sb.append(node.data).append("\t");
        convertTreeIntoStackInOrderRecursive(node.right, s, sb);

    }

    private static void convertTreeIntoStackPostOrderRecursive(BinaryTreeNode node, Deque s, StringBuffer sb ) {
        if(node == null){
            return;
        }

        convertTreeIntoStackPostOrderRecursive(node.left, s, sb);
        convertTreeIntoStackPostOrderRecursive(node.right, s, sb);
        sb.append(node.data).append("\t");
    }

    private static void convertTreeIntoStackPreOrderRecursive(BinaryTreeNode node, Deque s) {
        if(node == null){
            return;
        }
        s.push(node.data);
        convertTreeIntoStackPreOrderRecursive(node.left, s);
        convertTreeIntoStackPreOrderRecursive(node.right, s);

    }
}
