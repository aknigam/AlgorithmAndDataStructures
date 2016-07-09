package com.algo;

public class BinarySearch {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("I am running.");
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + " running.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}, "DD U M M Y");
		t.start();
		t.join();
	}

	// public static String printTree(TreeNode node) {
	// System.out.println();
	// return null;
	// }

	public static TreeNode dummyTree() {
		TreeNode node = new TreeNode(5);
		buildTree(5, 5, node.left, node.right);
		return node;
	}

	public static void buildTree(int i, int delpth, TreeNode left, TreeNode right) {
		if (delpth < 0) {
			return;
		}
		left = new TreeNode(i - 1);
		left.left = new TreeNode(i);
		right = new TreeNode(i + 1);
		buildTree(i - 1, delpth - 1, left.left, left.right);
		buildTree(i + 1, delpth - 1, right.left, right.right);
	}

	public boolean isBST2(TreeNode root) {
		return (isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

	}

	/**
	 * Efficient BST helper -- Given a node, and min and max values, recurs down
	 * the tree to verify that it is a BST, and that all its nodes are within
	 * the min..max range. Works in O(n) time -- visits each node only once.
	 */
	private boolean isBST2(TreeNode node, int min, int max) {
		if (node == null) {
			return (true);
		} else {
			// left should be in range min...node.data
			boolean leftOk = isBST2(node.left, min, node.data);
			// if the left is not ok, bail out
			if (!leftOk)
				return (false);
			// right should be in range node.data+1..max
			boolean rightOk = isBST2(node.right, node.data + 1, max);
			return (rightOk);
		}
	}

	static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;

		public TreeNode(int i) {
			data = i;
		}

		@Override
		public String toString() {
			return "[" + left + " " + data + " " + right + "]";
		}
	}

}
