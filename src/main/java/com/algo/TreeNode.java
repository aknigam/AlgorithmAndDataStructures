package com.algo;

import java.util.Comparator;

/**
 * Created by a.nigam on 09/11/16.
 */
public class TreeNode extends GenericTreeNode <Integer> {

    Integer data;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    TreeNode left;
    TreeNode right;

    public TreeNode(Integer i) {
        data =i;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
