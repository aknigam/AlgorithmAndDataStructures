package com.crackingthecodingtnterview;

import com.algo.linkedlist.Node;

/**
 * Created by a.nigam on 23/11/16.
 */
public class Chapter2LinkedLists {

    public static void main(String[] args) {
        Node d = new Node(3, new Node(2, new Node(5, new Node(6, null))));
        Node l = new Node(1, d);

        removeDuplicatesFromUnsortedLinkedList(d);
        System.out.println(l);
    }

    /**
     * http://umairsaeed.com/blog/2011/06/23/finding-the-start-of-a-loop-in-a-circular-linked-list/
     */
    public void loopStartInCircularList(){

    }
    // 2.4
    public void adddLists(){

    }

    public static void removeDuplicatesFromUnsortedLinkedList(Node d){

        if(d == null ){
            return;
        }

        Node next = d.next;
        if(next ==  null){
            return;
        }
        d.data = next.data;
        d.next = next.next;
        next.next = null;


    }

}
