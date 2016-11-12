package com.algo;

/**
 * Created by a.nigam on 10/11/16.
 */
public abstract class GenericTreeNode<T> {


    @Override
    public String toString() {
        return "[" + (getLeft() == null ? "" : getLeft()) + " " + getData() + " " + (getRight() == null ? "" : getRight()) + "]";
    }

    protected abstract T getData();

    protected abstract GenericTreeNode<T> getLeft();

    protected abstract GenericTreeNode<T> getRight();
}