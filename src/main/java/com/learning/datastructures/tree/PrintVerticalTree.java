package com.learning.datastructures.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/
 */


public class PrintVerticalTree {


    public static void main(String[] args) {
        Node root = new Node(1);
        root .left = new Node(2);
        root .right = new Node(3);
        root .left .left = new Node(4);
        root .left .right = new Node(5);
        root .right .left = new Node(6);
        root .right .right = new Node(7);
        root .right .left .right = new Node(8);
        root .right .right .right = new Node(9);
        PrintVerticalTree printer = new PrintVerticalTree();
        printer.printVerticalOrder(root);

    }
    public void printVerticalOrder(Node tree){
        Map<Integer, Node> hdNodeMap =  new HashMap<Integer, Node>();
        markNodes(hdNodeMap, tree);

    }

    private void markNodes(Map<Integer, Node> hdNodeMap, Node tree) {

    }
}
