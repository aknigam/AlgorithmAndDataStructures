package com.algo.string;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by a.nigam on 01/12/16.
 */
public class TrieBuilder {

    private static boolean debug = true;

    public static void main(String[] args) {

        String[] keys = new String[]{
                "A", "to", "tea", "ted", "ten", "i", "in", "inn", "anand", "aanchal","nisha"
        } ;

        Trie t = buildTrie(keys);

        t.printKeysLexicographically();

    }

    public static Trie buildTrie(String[] keys){
        Trie t = new Trie();
        for (int i = 0; i < keys.length; i++) {
            t.addKey(keys[i]);
        }
        if(debug) {
            StringBuilder sb = new StringBuilder();
            printTrie(t.root, 0, sb);
            System.out.println(sb);
        }

        return t;
    }

    private static void printTrie(Trie.TrieNode t, int level, StringBuilder sb) {
        if(t == null){
            return;
        }

        for (int i = 0; i < t.edges.size(); i++) {
            sb.append(")------ "+ t.edges.get(i).value()+" ------(");
            printTrie(t.edges.get(i).child, level+1 , sb);
        }
//        System.out.println(sb);
        sb.append(level+"\n");
        for (int i = 0; i < (level-1) * 17; i++) {
            sb.append(" ");
        }


    }

    static class Trie{

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


        TrieNode root;
        public Trie(){
            root = new TrieNode("");
        }

        public void addKey(String s){

            if(s == null || s.trim().isEmpty()){
                return;
            }

            TrieNode node = root;

            for (int j = 0; j < s.length(); j++) {

                char c = s.charAt(j);

                List<TrieEdge> e = node.edges; // these edges are in the sorted order
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
                    TrieEdge edge = new TrieEdge(c);
                    if(j == s.length() -1){
                        edge.child = new TrieNode(s);
                    }else {
                        edge.child = new TrieNode("");
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

        private void printKeysLexicographicallyInternal(TrieNode root, StringBuilder sb) {
            if(root.prefix != "")
                sb.append(root.prefix).append(",");
            for (int i = 0; i < root.edges.size(); i++) {
                printKeysLexicographicallyInternal(root.edges.get(i).child, sb);
            }
        }

        static class TrieNode{
            String prefix;
            List<TrieEdge> edges = new ArrayList<>();

            public TrieNode(String s) {
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

        static class TrieEdge{
            char c;
            TrieNode child;

            public TrieEdge(char c) {
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
