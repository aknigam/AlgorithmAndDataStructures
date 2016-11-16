package com.algo.tree;



/**
 * Created by a.nigam on 09/11/16.
 */
public class BinaryTreeNode extends GenericBinaryTreeNode<Integer> {

    Integer data;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(Integer i) {
        data =i;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
