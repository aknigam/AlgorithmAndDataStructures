package com.crackingthecodingtnterview;

import com.algo.tree.BTreePrinter;
import com.algo.tree.BinaryTreeNode;

import java.util.*;


public class TreeAndGraph {

    private static boolean debug = false;
    private static BinaryTreeNode randomSelectedNode1;
    private static int randomNodeSelectionCriteria1 = 6;

    private static BinaryTreeNode randomSelectedNode2;
    private static int randomNodeSelectionCriteria2 = 13;

    public static void main(String[] args) {
        demoCommonAncestor();
        demoInOrderSuccessor();
    }



    /**
     * Not necessarily a binary tree
     */
    public static BinaryTreeNode commonAncestor(BinaryTreeNode n1, BinaryTreeNode n2){

        // keep walking up unless common parent is found

        LinkedList<BinaryTreeNode> h1 = new LinkedList<>();
        LinkedList<BinaryTreeNode> h2 = new LinkedList<>();

        BinaryTreeNode p1 = null;
        BinaryTreeNode p2 = null;

        while (true){
            p1 = n1.getParent();
            p2 = n2.getParent();
            if(p1 == p2){
                return p1;
            }

            if(p1 != null){
                // search in h2. return if found otherwise offer in h1
                Iterator<BinaryTreeNode> itr = h2.iterator();
                while (itr.hasNext()){
                    if(itr.next() == p1){
                        return p1;
                    }
                }
                h1.offer(p1);
                n1 = p1;
            }


            else if(p2 != null){
                Iterator<BinaryTreeNode> itr = h1.iterator();
                while (itr.hasNext()){
                    if(itr.next() == p2){
                        return p2;
                    }
                }
                h2.offer(p2);
                n2 = p2;
            }
            else {
                break;
            }
            System.out.println(h1);
            System.out.println(h2);
        }

        return null;
    }



    public static BinaryTreeNode inOrderSuccessor(BinaryTreeNode t){
        if(t == null){
            return null;
        }

        BinaryTreeNode parent = t.getParent();
        BinaryTreeNode minInRightSubTree = t.getRight();

        while (true){
            if(minInRightSubTree == null){
                break;
            }
            if(minInRightSubTree.getLeft()!= null) {
                minInRightSubTree = minInRightSubTree.getLeft();
            }
            else{
                break;
            }
        }

        if(minInRightSubTree != null){
            return  minInRightSubTree;
        }
        if(parent == null){
            return null;
        }


        while (parent!= null) {
            if (parent.getLeft() == t) {
                return parent;
            }
            t = parent;
            parent = parent.getParent();
        }


        return null;
    }

    public static void findlevelLinkedLists(BinaryTreeNode t){

        StringBuilder sb = new StringBuilder();
        if(t == null){
            return;
        }
        List<LinkedList<BinaryTreeNode>> levelLists = new ArrayList<>();
        Deque<BinaryTreeNode> q = new LinkedList<>();
        q.offer(t);
        while (!q.isEmpty()){

            LinkedList<BinaryTreeNode> ll = new LinkedList<>();
            while (!q.isEmpty()) {
                BinaryTreeNode n = q.poll();
                ll.offer(n);
                sb.append(n.getData()).append("\t");
            }
            levelLists.add(ll);
            Iterator<BinaryTreeNode> itr = ll.iterator();
            while (itr.hasNext()){
                BinaryTreeNode x = itr.next();
                if(x.getLeft()!= null)
                    q.offer(x.getLeft());
                if(x.getRight()!= null)
                    q.offer(x.getRight());
            }

        }

        System.out.println(sb);
        for (LinkedList<BinaryTreeNode> ll : levelLists){
            System.out.println(ll);
        }



    }
    public static BinaryTreeNode createMinimalBST(int[] a, int s, int e, boolean parentRef){
        if(debug)
            System.out.println(s+" "+ e);
        if(s>e){
            return null;
        }
        int mid = (e + s)/2;

        BinaryTreeNode t = new BinaryTreeNode(a[mid]);
        if(a[mid] == randomNodeSelectionCriteria1){
            randomSelectedNode1 = t;
        }
        if(a[mid] == randomNodeSelectionCriteria2){
            randomSelectedNode2 = t;
        }
        BinaryTreeNode l = createMinimalBST(a, s, mid - 1, parentRef);
        if(l != null) {
            l.setParent(t);
            t.setLeft(l);
        }
        BinaryTreeNode r = createMinimalBST(a, mid + 1, e, parentRef);
        if(r!= null) {
            r.setParent(t);
            t.setRight(r);
        }

        return t;

    }

    public static boolean isBalancedTree(BinaryTreeNode t){

        if(t == null){
            return true;
        }

        return !((maxDepth(t) - minDepth(t))> 1);
    }

    public static int maxDepth(BinaryTreeNode t){

        if(t == null){
            return -1;
        }

        return 1+ Math.max(maxDepth(t.getLeft()), maxDepth(t.getRight()));
    }

    public static int minDepth(BinaryTreeNode t){

        if(t == null){
            return -1;
        }

        return 1+Math.min(minDepth(t.getLeft()), minDepth(t.getRight()));
    }


    private static void demoInOrderSuccessor() {
        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        BinaryTreeNode t = createMinimalBST(a, 0, a.length - 1, true);
        BinaryTreeNode b = randomSelectedNode1;
        while (b != null) {
            b = inOrderSuccessor(b);
            if(b != null)
                System.out.println(b.getData());
        }
    }

    private static void demoCommonAncestor() {
        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        BinaryTreeNode t = createMinimalBST(a, 0, a.length - 1, true);
        BTreePrinter.printTreeNode(t);

        BinaryTreeNode c = commonAncestor(randomSelectedNode1, randomSelectedNode2);
        if(c!=null)
            System.out.println("Common anncestor: "+ c.getData());
        else
            System.out.println("No common ancestor");
    }
}

