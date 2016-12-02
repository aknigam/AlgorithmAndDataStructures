package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 02/12/16.
 */
public class RadixTreeBuilder {



    private static boolean debug = true;

    public static void main(String[] args) {

        String[] keys = new String[]{
                "A", "to", "tea", "ted", "ten", "i", "in", "inn", "anand", "aanchal","nisha"
        } ;

        RadixTree t = buildRadixTree(keys);

        t.printKeysLexicographically();

    }

    public static RadixTree buildRadixTree(String[] keys){
        RadixTree t = new RadixTree();
        for (int i = 0; i < keys.length; i++) {
            t.addKey(keys[i]);
        }
        if(debug) {
            StringBuilder sb = new StringBuilder();
            printRadixTree(t.root, 0, sb);
            System.out.println(sb);
        }

        return t;
    }

    private static void printRadixTree(RadixTree.RadixTreeNode t, int level, StringBuilder sb) {
        if(t == null){
            return;
        }

        for (int i = 0; i < t.edges.size(); i++) {
            sb.append(")------ "+ t.edges.get(i).value()+" ------(");
            printRadixTree(t.edges.get(i).child, level+1 , sb);
        }
//        System.out.println(sb);
        sb.append(level+"\n");
        for (int i = 0; i < (level-1) * 17; i++) {
            sb.append(" ");
        }


    }

    static class RadixTree {

        @Override
        public String toString() {
            return root.toString() ;
        }

        /**
         * 1. Root node is empty
         * 2. Internal nodes - can be represented as linked list of child nodes/edges
         *  a) All the internal nodes must have a node value which is a prefix or the actual word. It is the concatenation of edge values in
         *  the path which leads to this node.
         *  b) All the internal nodes must have a a set of edges. Each edge will represent on character
         * 3. Leaf nodes only have the node value.
         */


        RadixTreeNode root;
        public RadixTree(){
            root = new RadixTreeNode("");
        }

        public void addKey(String s){

            if(s == null || s.trim().isEmpty()){
                return;
            }

            RadixTreeNode node = root;

            for (int j = 0; j < s.length(); j++) {

                char c = s.charAt(j);

                List<RadixTreeEdge> e = node.edges; // these edges are in the sorted order
                int i;
                boolean alreadyExists = false;


                for (i = 0; i < e.size(); i++) {

                    if(e.get(i).value() == c){
                        alreadyExists = true;
                        node = e.get(i).child;
                        break;
                    }
                    else if(e.get(i).value() > c){
                        break;
                    }
                }
                if(!alreadyExists){
                    RadixTreeEdge edge = new RadixTreeEdge(c);
                    if(j == s.length() -1){
                        edge.child = new RadixTreeNode(s);
                    }else {
                        edge.child = new RadixTreeNode("");
                    }

                    e.add(i, edge);
                    node = edge.child;
                }
            }

        }

        public void printKeysLexicographically() {
            StringBuilder sb = new StringBuilder();
            printKeysLexicographicallyInternal(root, sb);
            System.out.println(sb);
        }

        private void printKeysLexicographicallyInternal(RadixTreeNode root, StringBuilder sb) {
            if(root.prefix != "")
                sb.append(root.prefix).append(",");
            for (int i = 0; i < root.edges.size(); i++) {
                printKeysLexicographicallyInternal(root.edges.get(i).child, sb);
            }
        }

        static class RadixTreeNode {
            String prefix;
            List<RadixTreeEdge> edges = new ArrayList<>();

            public RadixTreeNode(String s) {
                prefix = s;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(prefix).append(" --> ");

                for (int i = 0; i < edges.size(); i++) {
                    sb.append(edges.get(i));
                }

                return prefix ;
            }
        }

        static class RadixTreeEdge {
            char c;
            RadixTreeNode child;

            public RadixTreeEdge(char c) {
                this.c = c;
            }

            public char value(){
                return c;
            }


            @Override
            public String toString() {
                return String.valueOf(c);
            }
        }



    }
}
