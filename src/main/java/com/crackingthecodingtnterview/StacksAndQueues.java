package com.crackingthecodingtnterview;



/**
 * Created by a.nigam on 23/11/16.
 */
public class StacksAndQueues {


    public static void main(String[] args) {

        QueueUsingtwoStacks<String> q = new QueueUsingtwoStacks<>(10);

        q.enque("1");
        q.enque("2");
        q.enque("3");
        q.enque("4");
        q.enque("5");
        q.enque("6");
        q.enque("7");
        q.enque("8");
        q.enque("9");
        q.enque("10");
        q.enque("11");

        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());
        System.out.println((String) q.get());

    }


    static class ThreeArrayStack<E>{
        private Object[] a;
        // 0 to top1
        // top1 to top2
        // top2 to end
        int top1;
        int top2;

        ThreeArrayStack(int size){

        }




    }

    static class QueueUsingtwoStacks<E>{
        ArrayStack<E> s1;
        ArrayStack<E> s2;

        public QueueUsingtwoStacks(int size){

            s1 = new ArrayStack<>(size);
            s2 = new ArrayStack<>(size);

        }
        public void enque(E e){
            s1.push(e);
        }

        public <E> E get(){

            if(!s2.isEmpty()){
                return s2.pop();
            }
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }


            return s2.pop();
        }

    }

    static class ArrayStack<E>{

        private Object[] a;
        private int top = -1;
        int size = -1;

        public ArrayStack(int size){
            this.size = size;
            a = new Object[size];
            for (int i = 0; i < a.length; i++) {
                a[i] = null;
            }
        }


        public void push(E e){
            if(top == size -1){
                System.out.println("Stack full");
                return;
            }
            a[++top] = e;
        }

        public <E> E pop(){
            if(top == -1){
                System.out.println("Stack empty");
                return null;
            }
            return (E) a[top--];
        }

        public <E> E peek(){
            if(top == -1){
                System.out.println("Stack empty");
                return null;
            }
            return (E) a[top];
        }

        public void min(){

        }

        public boolean isEmpty(){
            return top == -1;
        }

    }

}
