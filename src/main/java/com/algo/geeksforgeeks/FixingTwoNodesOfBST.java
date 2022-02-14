package com.algo.geeksforgeeks;


import java.util.LinkedList;

public class FixingTwoNodesOfBST {

    Node biggerWrongNode;
    Node smallerWrongNode;
    public  void inOrderTreeWalk(Node node, StringBuilder sb, LinkedList<Node> io){


        if(node == null){
            return;
        }

        inOrderTreeWalk(node.left, sb, io);
        sb.append(node.data).append(",\t");

        if(io.size() > 0 ){
            Node prev = io.getLast();
            if(node.data < prev.data) {
//                System.out.println(String.format("Found %d %d", node.data, prev.data));
                /*
                the bigger node will be found first as we are traversing in increasing order
                 */
                if(biggerWrongNode == null) {
                    biggerWrongNode = prev;
                }
                else{
                    smallerWrongNode = node;
                }
            }
        }
        io.add(node);

        inOrderTreeWalk(node.right, sb, io);


    }



    public Node correctBST(Node node) {
        StringBuilder sb = new StringBuilder();

        LinkedList<Node> io = new LinkedList<>();

        inOrderTreeWalk(node, sb, io);
//        System.out.println("Order before swap: "+sb);

        if(biggerWrongNode != null && smallerWrongNode != null) {
//            System.out.println("Nodes to be swapped are: " + biggerWrongNode.data + " & " + smallerWrongNode.data);

            int bd = biggerWrongNode.data;
            biggerWrongNode.data = smallerWrongNode.data;

            smallerWrongNode.data = bd;
        }


        sb = new StringBuilder();
        inOrderTreeWalk(node, sb, new LinkedList<>());


//        System.out.println("Order after swap: "+sb);

        return node;
    }


    public static void main(String[] args) {
        Node node = buildTree();

        FixingTwoNodesOfBST f = new FixingTwoNodesOfBST();


        f.correctBST(node);
    }
    private static Node buildTree() {
        Node node = new Node(10);
        Node five = new Node(5);
        node.left = five;
        five.left = new Node(2);
        five.right = new Node(20);
        node.right = new Node(8);
        return node;
    }


}
