package com.algo.tree;


import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearch<T extends Integer> {

	public static void main(String[] args) throws InterruptedException {

//        System.out.println((int) Math.pow(2, 4)-1);
//        int[] a ={1,4,6,45,78,90, 91, 95, 98, 99, 100, 102, 103, 104, 105};
//        int[] a ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
//          int[] a ={1, 4, 5, 10, 16, 17, 21};
        int[] a ={1, 2, 3, 4, 5, 6, 7};
//        System.out.printf(String.valueOf(adjustHeightIfLess(a, 2, 0, 16)));

//        BTreePrinter.printTreeNode(drawBinaryTreeOfHeight(a, 30, 0, a.length-1, "root"));
//        BinaryTreeNode n = drawBinaryTreeOfHeight(a, 3, 0, a.length - 1, "root");
//        System.out.println(n);
//        BTreePrinter.printTreeNode(n);
//        BTreePrinter.printTreeNode(drawBinaryTreeOfHeight(a, 2, 0, a.length-1, "root"));

        BinaryTreeNode n = drawBinaryTreeOfHeight(a, 2, 0, a.length - 1, "root");
        BTreePrinter.printTreeNode(n);

        System.out.println("In order traversal start ");
        inOrderTreeWalkNonRecursive(n);

        StringBuilder sb = new StringBuilder();
        inOrderTreeWalk(n , sb);
        System.out.println(sb);

        System.out.println("In order traversal end ");
        System.out.println("\n\nPre order traversal start ");

        preOrderTreeWalkNonRecursive(n);

        sb = new StringBuilder();
        preOrder(n , sb);
        System.out.println(sb);
        System.out.println("\n\nPre order traversal end ");


//        BTreePrinter.printTreeNode(drawBinaryTreeOfHeight(a, 3, 0, a.length-1, "root"));
//        BTreePrinter.printTreeNode(drawBinaryTreeOfHeight(a, 4, 0, a.length-1, "root"));
//        BTreePrinter.printTreeNode(drawBinaryTreeOfHeight(a, 5, 0, a.length-1, "root"));
//        BTreePrinter.printTreeNode(drawBinaryTreeOfHeight(a, 6, 0, a.length-1, "root"));

    }

    public static void buildDummyBinaryTree(){
        BinaryTreeNode n= new BinaryTreeNode(1);
        n.data = 1;
    }


    /**
     int[] a = {1,4,6,45,78,90, 91, 95, 98, 99, 100};
     BinaryTreeNode n = drawBinaryTreeOfHeight(a, 1, 0, a.length-1);
     System.out.println(n);
     BTreePrinter.printTreeNode(n);
     * @param a
     * @param height
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static BinaryTreeNode drawBinaryTreeOfHeight(int[] a, int height, int startIndex, int endIndex, String debug){
        // System.out.println(debug+"\tH\t"+height+"\tS\t"+startIndex+"\tE\t"+endIndex);
        // first sort the array
        if(startIndex>endIndex){
            System.out.println("\t\tNull");
            return null;
        }
        if(startIndex == endIndex){
            return new BinaryTreeNode(a[startIndex]);
        }

        height = adjustHeightIfLess(a, height, startIndex, endIndex);
        if(height <= 0){
            return null;
        }

        if(heightTooLarge(a, height, startIndex, endIndex)){
            System.out.println("Height too large");
            height =  (endIndex - startIndex);
        }

        int rootIndex = startIndex + (int) Math.pow(2, height)-1;
        if(rootIndex> endIndex){
            rootIndex =  startIndex;
        }
        System.out.println(debug+"\tH\t"+height+"\tS\t"+startIndex+"\tE\t"+endIndex+"\t|\tR\t"+ rootIndex+"("+a[rootIndex]+")\tLN\t["+0+","+(rootIndex-1)+"]\tRN\t["+(rootIndex+1)+ ","+(endIndex)+"]");

        BinaryTreeNode n = new BinaryTreeNode(a[rootIndex]);
        n.setLeft( drawBinaryTreeOfHeight(a , height -1, startIndex, rootIndex -1, "Left"));
        n.setRight( drawBinaryTreeOfHeight(a , height -1, rootIndex+1, endIndex, "Right"));

        return  n;
    }


    private static int adjustHeightIfLess(int[] a, int height, int startIndex, int endIndex) {
        int minHeight = (int) (Math.log10(endIndex - startIndex)/Math.log10(2));
        if(height<minHeight){
            return minHeight;
        }
        return height;
    }

    private static boolean heightTooLarge(int[] a, int height, int startIndex, int endIndex) {
        return height>(endIndex -startIndex);
    }

    public static int countTrees(int noOfNodes){
        if(noOfNodes<=1){
            return 1;
        }

        int sum = 0;
        for (int i = 1; i <= noOfNodes; i++) {
            int leftCount = countTrees(i-1);
            int rightCount = countTrees(noOfNodes - i);

            sum =  sum + leftCount*rightCount;
        }
        return sum;
        
    }
    
    public void generateBinaryTree(int[] a, int rootIndex){

    }

    // left -> right -> root
    public static void postOrderTreeWalkNonRecursive(BinaryTreeNode root, StringBuilder sb) {

        /*

        left
        right
        root

         */
        if (root == null) {
            return;
        }
        Deque<BinaryTreeNode> st = new ArrayDeque<>();
        boolean peek = false;
        while (true) {

            if(root != null){
                st.push(root);
                root = root.left;
            }
            else{
                if(st.isEmpty()){
                    break;
                }

                if(peek == true){
                    root = st.pop();
                    sb.append(root.data).append(" ");
                }
                else {
                    root = st.peek();
                    peek = true;
                    root = root.right;
                }

            }
            System.out.println(sb);
        }

    }
    // root -> left -> right
    public static void preOrderTreeWalkNonRecursive(BinaryTreeNode root) {

        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return;
        }
        Deque<BinaryTreeNode> st = new ArrayDeque<>();


        while (true) {

            if(root!= null){
                sb.append(root.data).append(" ");
                st.push(root);
                root = root.left;
            }
            else{

                if(st.isEmpty()){
                    break;
                }
                root = st.pop();
                root = root.right;

            }
        }

        System.out.println(sb);

    }
    public static void preOrder(BinaryTreeNode root, StringBuilder sb) {
        if(root==null){
            return;
        }
        sb.append(root.data).append(" ");
        preOrder(root.left, sb);
        preOrder(root.right, sb);

    }
    // left -> root -> right
    public static void inOrderTreeWalkNonRecursive(BinaryTreeNode root) {

        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return;
        }
        Deque<BinaryTreeNode> st = new ArrayDeque<>();

        while (true) {

            if(root != null){

                st.push(root);
                root = root.left;
            }
            else{
                if(st.isEmpty()){
                    break;
                }
                root = st.pop();
                sb.append(root.data).append("\t");
                root = root.right;

            }
        }
        System.out.println(sb);

    }
    public static void inOrderTreeWalk(BinaryTreeNode node, StringBuilder sb){

        if(node == null){
            return;
        }

        inOrderTreeWalk(node.left, sb);
        sb.append(node.data).append(",\t");
        inOrderTreeWalk(node.right, sb);


    }




    public static BinaryTreeNode dummyTree() {
		BinaryTreeNode node = new BinaryTreeNode(2);
		buildTree(2, 2,node, node.data);
		return node;
	}

	public static void buildTree(int i, int depth, BinaryTreeNode node, Integer data) {

		if (depth < 0) {
			return;
		}

		node.left = new BinaryTreeNode(i - 1);
		node.right = new BinaryTreeNode(i + 1);
        System.out.println(depth+"\t\t"+node);
        buildTree(i - 1, depth - 1, node.left, (Integer) node.data);
		buildTree(i + 1, depth - 1, node.right, (Integer) node.data);
	}

//	public boolean isBST2(BinaryTreeNode root) {
//		return (isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
//
//	}

	/**
	 * Efficient BST helper -- Given a node, and min and max values, recurs down
	 * the tree to verify that it is a BST, and that all its nodes are within
	 * the min..max range. Works in O(n) st -- visits each node only once.
	 */
	private boolean  isBST2(BinaryTreeNode node, T min, T max) {
		if (node == null) {
			return (true);
		} else {
			// left should be in range min...node.data
			boolean leftOk = isBST2((BinaryTreeNode)node.left, min, (T) node.data);
			// if the left is not ok, bail out
			if (!leftOk)
				return (false);
			// right should be in range node.data+1..max
            T a = (T)T.valueOf( node.data.intValue() + 1);
			boolean rightOk = isBST2((BinaryTreeNode) node.right, a, max);
			return (rightOk);
		}
	}



}
