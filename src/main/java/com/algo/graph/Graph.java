package com.algo.graph;


import com.algo.util.ArrayUtils;
import com.algo.util.GraphUtils;


import java.util.*;

import static com.algo.graph.GraphFactory.*;

/**
 * Created by a.nigam on 14/11/16.
 * http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/GraphAlgor/strongComponent.htm
 */
public abstract class Graph<E> {

    private static final String BY_INDEX_ITERAION = "BY_INDEX";
    int type = -1; // 0 matrix, 1 list

    final int V; // no of vertices


    public Vertex<E>[] vlist;

//    int time =0;
    public Graph(int noOfvertices) {
        V = noOfvertices;
        vlist = new Vertex[noOfvertices];
        ArrayUtils.initArray(vlist, null);
    }

    public abstract void addUndirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex);

    public abstract void addDirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex);

    public abstract void addUndirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex, int weight);

    public abstract void addDirectedEdge(Vertex<E> sourceVertex, Vertex<E> destVertex, int weight);

    protected abstract List<Vertex<E>> getAdjacentvertices(Vertex<E> v);

    public Iterator<Vertex<E>> vertextIterator(){
        return Arrays.asList(vlist).iterator();
    }

    /**
    First index in the 2-D array represents the row
     */

    static class DFSAlgoAttributes<E>{
        StringBuilder dfs =  new StringBuilder();
        StringBuilder parenthesisStructure =  new StringBuilder();
        int time =0;
        int cc =0;

        LinkedList<Vertex<E>> topologicalSort = new LinkedList<Vertex<E>>();

        List<String> te = new ArrayList<String>();
        List<String> be = new ArrayList<String>();
        List<String> fe = new ArrayList<String>();
        List<String> ce = new ArrayList<String>();

        //attributes for 22.4-2
        int startIndex =-1;
        int targetindex =-1;
        int pathCount =0;


        public String iterationOrder = BY_INDEX_ITERAION;
        public Iterator<Vertex<E>> itr;

        public Iterator<Vertex<E>> getItr() {
            return itr;
        }
    }

    public DFSAlgoAttributes DFS(DFSAlgoAttributes request){

        Iterator<Vertex<E>> itr = vertextIterator();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            v.color = 0; // WHITE
        }


        //attributes for 22.4-2
//        attr.startIndex =0;
//        attr.targetindex = 4;

        if(request.getItr() == null) {
            itr = vertextIterator();
        }
        else{
            itr = request.getItr();
        }

        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            if(v.index == -1)
                continue;;
            if(v.color == 0){
                request.cc++;

                dfsInternal(v, request);
                request.topologicalSort.add(new Vertex<String>(-1, "|"));
            }
        }

        System.out.println(request.dfs);
        System.out.println(request.parenthesisStructure);
        System.out.println(request.topologicalSort);

        System.out.println(request.pathCount);
        System.out.println("Edges start");
        System.out.println("T-E\t"+request.te.toString());
        System.out.println("F-E\t"+request.fe.toString());
        System.out.println("B-E\t"+request.be.toString());
        System.out.println("C-E\t"+request.ce.toString());
        System.out.println("Edges end");

        return request;

    }



    protected void dfsInternal(Vertex<E> v, DFSAlgoAttributes attr){

        v.cc = attr.cc;
        attr.parenthesisStructure.append("(").append(v.data);
        attr.dfs.append(v.data).append("\t");
        v.color = 1; // GRAY
        v.st = ++ attr.time;
        v.d = attr.time;

        List<Vertex<E>> adj = getAdjacentvertices(v);
        System.out.println(v+"-->\t"+adj);
        for (int i = 0; i < adj.size(); i++) {

            Vertex<E> u = adj.get(i);
            if(u.color == 0) {
                // TREE edge
                attr.te.add(v.data+"-"+u.data);
                u.p = v;
                dfsInternal(u, attr);
            }
            else if(u.color == 1) {
                // BACK edge
                attr.be.add(v.data+"-"+u.data);
            }
            else if(u.color == 2) {
                if(v.cc != u.cc){
                    // CROSS edge
                    attr.ce.add(v.data+"-"+u.data);
                }
                else{
                    // FORWARD edge
                    attr.fe.add(v.data+"-"+u.data);
                }
            }
            //attributes for 22.4-2
            /*
            if(u.index == attr.targetindex){
                attr.pathCount++;
            }
            */
        }
        v.color = 2; // BLACK
        v.et = ++attr.time;
        attr.parenthesisStructure.append(v.data).append(")");

        attr.topologicalSort.add(v);


    }

    public void BFS(Vertex<E> s){

        if(!GraphUtils.isValidVertex(V, s.index)){
            return;
        }


        LinkedList<Vertex<E>> q =  new LinkedList<Vertex<E>>();

        StringBuilder bfs = new StringBuilder();


        s.color = 1; // MARKED AS G
        s.d = 0;
        q.add(s);

        while (!q.isEmpty()){
            System.out.printf("Queue-> "+ q);
            Vertex<E> v = q.poll();

//            color[vlist] = 1; // marked G
            bfs.append(v).append("\t");
            List<Vertex<E>> adj =  getAdjacentvertices(v);
            for (int i = 0; i < adj.size(); i++) {
                Vertex<E> vrtx = adj.get(i);
                int u = vrtx.index;

                if(vrtx.color == 0) {
                    vrtx.color = 1;
                    vrtx.d  = v.d+1;
                    vrtx.p = v;
                    q.add(vrtx);
                }
            }
            v.color = 2; // marked B
        }




        System.out.println("\nBFS");
        System.out.println(bfs);





    }


    protected void printBFSTreePath(Vertex<E> s, Vertex<E> d){

        StringBuilder sb = new StringBuilder();
        while(true){
            sb.append(d).append("\t");
            d = d.p;
            if(d == null || d.index == -1){
                break;
            }
        }
        System.out.println(sb);


    }




    public Graph<E> getTranspose(){

        Graph<E> t = new AdjacencyListGraph<E>(vlist.length);

        if(type == 0){
            return this;
        }

        for (int i = 0; i < vlist.length; i++) {
            Vertex<E> v = vlist[i];

            List<Vertex<E>> adj = getAdjacentvertices(v);
            for (Vertex<E> u : adj){
                t.addDirectedEdge(u, v);
            }

        }

        return t;

    }

    public void stronglyConnectedComponents(){

        DFSAlgoAttributes dfs1Result = DFS(new DFSAlgoAttributes());



        Graph<E> gt = getTranspose();

        DFSAlgoAttributes<E> dfs2Request = new DFSAlgoAttributes<E>();
        dfs2Request.itr = dfs1Result.topologicalSort.descendingIterator();
        System.out.println("SCG -->> !!!!!");
        gt.DFS(dfs2Request);



    }

    /**
     ********************************************************************************
     *
     *          M   A   I   N       M   E   T   H   O   D
     *
     ********************************************************************************
     */

    public static void main(String[] args) {
        Graph g = getGraph0();
        g.BFS(g.vlist[0]);



    }


    public static void dfsmain(String[] args) {
        Graph g = getGraph4();


        System.out.println(g);

        g.DFS(new DFSAlgoAttributes());
        GraphUtils.printColors(g);
        GraphUtils.printDistance(g);
        GraphUtils.printBFSPrevious(g);
        GraphUtils.printTimes(g);
        GraphUtils.printBraceNotation(g);
        GraphUtils.printCC(g);
    }



    public void _22_2_7_findDiameter(int s){

    }
    public void _22_2_7_BFS(int s){

    }


}
