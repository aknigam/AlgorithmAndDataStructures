package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 02/12/16.
 */
public class RadixTreeBuilder {



    private static boolean debug = false;

    public static void main(String[] args) {

        String[] keys = new String[]{
//                "A", "to", "tea", "ted", "ten", "i", "in", "inn", "anand", "aanchal","nisha"
                "romane", "romanus", "romulus", "rubens", "ruber", "rubicon", "rubicundus"
        } ;

        RadixTree t = buildRadixTree(keys);
        System.out.println(t.lookupIterative("ru"));
        System.out.println(t.lookupIterative("roma"));
        System.out.println(t.lookupIterative("romane"));
        System.out.println(t.lookupIterative("romane1"));
        System.out.println(t.lookupIterative("tromane"));


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
            sb.append(")------ "+ t.edges.get(i).value()+"["+t.edges.get(i).child.prefix+"]-------(");
            printRadixTree(t.edges.get(i).child, level+1 , sb);
        }

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

        public boolean lookup(String s){
            return lookUpInternal(s, root);
        }

        private boolean lookUpInternal(String s, RadixTreeNode node) {
            if(node == null || node.edges.size() == 0){
                return false;
            }

            for (int i = 0; i < node.edges.size(); i++) {
                RadixTreeEdge edge = node.edges.get(i);

                String edgeVal = edge.value();

                if(edgeVal.length() >= s.length() && edgeVal.indexOf(s) != -1){
                    return true;
                }
                else if(s.indexOf(edgeVal)!= -1){
                    return lookUpInternal(s.substring(edgeVal.length()), edge.child);
                }
            }
            return false;
        }

        public boolean lookupIterative(String s){

            String key = s;
            RadixTreeNode node = root;
            List<RadixTreeEdge> edges;

            while (node != null && node.edges.size() >0){

                edges = node.edges;
                int j;
                for (j = 0; j < edges.size(); j++) {
                    RadixTreeEdge edge = node.edges.get(j);
                    String edgeVal = edge.value();

                    if(edgeVal.length() >= key.length() && edgeVal.indexOf(key) != -1){
                        return true;
                    }
                    else if(key.indexOf(edgeVal)!= -1){
                        node = edge.child;
                        key = key.substring(edgeVal.length());
                        break;
                    }

                }
                if(j == edges.size()){
                    return false;
                }

            }
            return false;
        }


        public void addKey(String key) {

            if (key == null || key.trim().isEmpty()) {
                return;
            }

            RadixTreeNode node = root;
            String prefix = node.prefix;


            for (int j = 0; j < key.length(); ) {


                char c = key.charAt(j);

                List<RadixTreeEdge> e = node.edges; // these edges are in the sorted order
                int i;
                boolean alreadyExists = false;

                RadixTreeEdge edge = null;
                for (i = 0; i < e.size(); i++) {
                    edge = e.get(i);
                    if (edge.value().charAt(0) == c) {
                        alreadyExists = true;
                        break;
                    } else if (edge.value().charAt(0) > c) {
                        break;
                    }
                }
                if (alreadyExists) {
                    String edgeVal = edge.value();
                    if (edgeVal.equals(key)) {
                        return;
                    }
                    int k;
                    for (k = 1; k < edgeVal.length() && k < key.length(); k++) {
                        if (edgeVal.charAt(k) != key.charAt(k)) {
                            break;
                        }
                    }
                    // case 1
                    if ((k <= (edgeVal.length() - 1)) && (k <= (key.length() - 1))) {
                        edge.setValue(edgeVal.substring(0, k));

                        RadixTreeEdge e1 = new RadixTreeEdge(edgeVal.substring(k));
                        e1.child = edge.child;
                        RadixTreeNode insertedNode = null;

                        if(k == ((prefix+key).length() -1)) {
                            insertedNode = new RadixTreeNode(prefix + edge.value());
                        }else {
                            insertedNode = new RadixTreeNode("");
                        }
                        edge.child = insertedNode;

                        RadixTreeEdge e2 = new RadixTreeEdge(key.substring(k));
                        e2.child = new RadixTreeNode(prefix+key);
                        if (edgeVal.charAt(k) < key.charAt(k)) {
                            insertedNode.addEdge(e1);
                            insertedNode.addEdge(e2);
                        } else {
                            insertedNode.addEdge(e2);
                            insertedNode.addEdge(e1);
                        }
                    }
                    // case 2
                    else if (k <= (edgeVal.length() - 1)) {


                        edge.setValue(edgeVal.substring(0, k));

                        RadixTreeEdge e1 = new RadixTreeEdge(edgeVal.substring(k));
                        e1.child = edge.child;
                        RadixTreeNode insertedNode = new RadixTreeNode(prefix + edgeVal.substring(k));
                        edge.child = insertedNode;
                        insertedNode.addEdge(e1);


                    }
                    // case 3
                    else {
                        // need to move down the tree
                        node = edge.child;
                        key = key.substring(k);
                        prefix = prefix+edge.value();

                        continue;

                    }

                    return;


                } else {
                    RadixTreeEdge edge1 = new RadixTreeEdge(key);
                    edge1.child = new RadixTreeNode(key);
                    e.add(i, edge1);


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

            public void addEdge(RadixTreeEdge e1) {

                edges.add(e1);
            }
        }

        static class RadixTreeEdge {
            String c;
            RadixTreeNode child;

            public RadixTreeEdge(String c) {
                this.c = c;
            }

            public String value(){
                return c;
            }


            @Override
            public String toString() {
                return String.valueOf(c);
            }

            public void setValue(String value) {
                this.c = value;
            }
        }



    }
}
