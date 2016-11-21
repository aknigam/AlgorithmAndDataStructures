package com.algo.tree;

/**
 * Created by a.nigam on 10/11/16.
 */
public abstract class GenericBinaryTreeNode<T> {


    @Override
    public String toString() {
        return getData().toString();
//        return "[" + (getLeft() == null ? "" : getLeft()) + " " + getData() + " " + (getRight() == null ? "" : getRight()) + "]";
    }

    protected abstract T getData();

    protected abstract GenericBinaryTreeNode<T> getLeft();

    protected abstract GenericBinaryTreeNode<T> getRight();
}