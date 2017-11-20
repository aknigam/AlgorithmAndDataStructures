package com.algo.graph;


import com.algo.util.ArrayUtils;
import com.algo.util.GraphUtils;


import java.util.*;

import static com.algo.graph.GraphFactory.*;

/**
 * Created by a.nigam on 14/11/16.
 * http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/GraphAlgor/strongComponent.htm
 * All-pair shortest path problem explaination: https://home.cse.ust.hk/~dekai/271/notes/L13/L13.pdf
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

    public abstract List<Vertex<E>> getAdjacentvertices(Vertex<E> v);

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

        List<String> treeEdge = new ArrayList<String>();
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
            v.color = VertexColor.White; // WHITE
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
                continue;
            if(v.color == VertexColor.White){
                request.cc++;

                dfsInternal(v, request);
                request.topologicalSort.add(new Vertex<String>(-1, "|"));
            }
        }
        System.out.println("DFS results -----------------");
        System.out.println(request.dfs);
        System.out.println(request.parenthesisStructure);
        System.out.println("topologicalSort\t"+request.topologicalSort);

        System.out.println(request.pathCount);
        System.out.println("Edges start");
        System.out.println("Tree Edges\t"+request.treeEdge.toString());
        System.out.println("F-E\t"+request.fe.toString());
        System.out.println("B-E\t"+request.be.toString());
        System.out.println("C-E\t"+request.ce.toString());
        System.out.println("Edges end");

        return request;

    }



    protected void dfsInternal(Vertex<E> v, DFSAlgoAttributes attr){

        v.connectedComponents = attr.cc;
        attr.parenthesisStructure.append("(").append(v.data);
        attr.dfs.append(v.data).append("\t");
        v.color = VertexColor.Gray; // GRAY
        v.startTime = ++ attr.time;
        v.distance = attr.time;

        List<Vertex<E>> adj = getAdjacentvertices(v);
        System.out.println(v+"-->\t"+adj);
        for (int i = 0; i < adj.size(); i++) {

            Vertex<E> u = adj.get(i);
            if(u.color == VertexColor.White) {
                // TREE edge
                attr.treeEdge.add(v.data+"-"+u.data);
                u.previous = v;
                // recursive call ---------------------------------------------start>>
                dfsInternal(u, attr);
                // recursive call ---------------------------------------------end>>
            }
            else if(u.color == VertexColor.Gray) {
                // BACK edge
                attr.be.add(v.data+"-"+u.data);
            }
            else if(u.color == VertexColor.Black) {
                if(v.connectedComponents != u.connectedComponents){
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
        v.color = VertexColor.Black; // BLACK
        v.endTime = ++attr.time;
        attr.parenthesisStructure.append(v.data).append(")");

        attr.topologicalSort.add(v);


    }

    public void dagSingleSourceShortestPath(){
        /*
        DAG-SHORTEST-PATHS .G; w; s/
        1 topologically sort the vertices of G
        2 INITIALIZE-SINGLE-SOURCE.G;s/
            for each vertex u, taken in topologically sorted order
                for each vertex v E G:Adj[u]
                    relax(u, v, w)
         */
    }
    public void singleSourceSHortestPath(Vertex<E> s){




    }
    public void BFS(Vertex<E> s){

        if(!GraphUtils.isValidVertex(V, s.index)){
            return;
        }

        DFSAlgoAttributes<E> attr = new DFSAlgoAttributes<E>();

        LinkedList<Vertex<E>> q =  new LinkedList<Vertex<E>>();

        StringBuilder bfs = new StringBuilder();


        s.color = VertexColor.Gray; // MARKED AS G
        s.distance = 0;
        q.add(s);

        while (!q.isEmpty()){
            System.out.println("Queue-> "+ q);
            Vertex<E> v = q.poll();

//            color[vlist] = 1; // marked G
            bfs.append(v).append("\t");
            List<Vertex<E>> adj =  getAdjacentvertices(v);
            for (int i = 0; i < adj.size(); i++) {
                Vertex<E> u = adj.get(i);

                if(u.color == VertexColor.White){
//                if(u.distance == Integer.MIN_VALUE) {
                    u.color = VertexColor.Gray;
                    u.distance = v.distance +1;
                    u.previous = v;
                    q.add(u);

                    attr.treeEdge.add(v.data+"-"+u.data);
                }
                else if(u.color == VertexColor.Gray){
                    attr.ce.add(v.data+"-"+u.data);
                }
            }
            v.color = VertexColor.Black; // marked B
        }




        System.out.println("\nBFS");
        System.out.println(bfs);

        System.out.println("Edges start");
        System.out.println("T-E\t"+attr.treeEdge.toString());
        System.out.println("F-E\t"+attr.fe.toString());
        System.out.println("B-E\t"+attr.be.toString());
        System.out.println("C-E\t"+attr.ce.toString());
        System.out.println("Edges end");





    }


    protected void printBFSTreePath(Vertex<E> s, Vertex<E> d){

        StringBuilder sb = new StringBuilder();
        while(true){
            sb.append(d).append("\t");
            d = d.previous;
            if(d == null || d.index == -1){
                break;
            }
        }
        System.out.println(sb);


    }






    public void stronglyConnectedComponents(){

        DFSAlgoAttributes dfs1Result = DFS(new DFSAlgoAttributes());



        Graph<E> gt = getTranspose();

        DFSAlgoAttributes<E> dfs2Request = new DFSAlgoAttributes<E>();
        dfs2Request.itr = dfs1Result.topologicalSort.descendingIterator();
        System.out.println("SCG -->> !!!!!");
        gt.DFS(dfs2Request);



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

    public void MinimumSpanningTree(Vertex<E> s){

        int totalWeight = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < vlist.length; i++) {
            vlist[i].color = VertexColor.White;
//            vlist[i].distance = Integer.MAX_VALUE;
            vlist[i].previous = null;
        }

        PriorityQueue<Edge<E>> q = new PriorityQueue<>();

//        vlist[0].color = 1;
        s.distance = 0;
        s.previous = null;
        q.add(new Edge<E>((Vertex<E>) null, s, -1));
        Vertex<E> p = null;
        while(!q.isEmpty()){

            System.out.println(p+"- "+ q);
            Edge<E> e = q.poll();
            Vertex<E> v = e.dest;
            if(v.color == VertexColor.Black) {
                continue;
            }
            if(p !=  null) {
                v.previous = p;
                v.distance = p.distance + e.weight;
            }
            p = v;
            if(e.src== null) {
                sb.append(v.data).append("["+v.distance +"]").append("(-)").append("\t");
                totalWeight = totalWeight + e.weight;
            }else{
                sb.append(v.data).append("["+v.distance +"]").append("(" + (e.src == null ? "-" : e.src.data) + ")").append("\t");
                totalWeight = totalWeight + e.weight;
            }

            List<Vertex<E>> adj = getAdjacentvertices(v);
            for (int i = 0; i < adj.size(); i++) {

                Vertex<E> u = adj.get(i);
                if(u.color == VertexColor.White ){
                    u.previous = v;
                    q.add(new Edge<E>(v, u, u.weight.get(v)));
                }
            }
            v.color = VertexColor.Black;

        }

        System.out.println(sb);
        System.out.println(totalWeight);
    }

    /**
     ********************************************************************************
     *
     *          M   A   I   N       M   E   T   H   O   D
     *
     ********************************************************************************
     */

    public static void mainMinimumSpanningTree(String[] args) {
        Graph g = getMST23_5Graph();
        g.MinimumSpanningTree(g.vlist[0]);

//        g.BFS(g.vlist[0]);
//        dfsmain(args);






    }


    public static void mainBFS(String[] args) {

        Graph g = new AdjacencyMatrixGraph(5);


        Vertex a = new Vertex(0, "a");
        Vertex b = new Vertex(1, "b");
        Vertex c = new Vertex(2, "c");
        Vertex d = new Vertex(3, "distance");
        Vertex e = new Vertex(4, "e");



        g.addDirectedEdge(a, b);
        g.addDirectedEdge(a, d);
        g.addDirectedEdge(a, c);
        g.addDirectedEdge(b, d);
        g.addDirectedEdge(b, e);
        g.addDirectedEdge(c, d);
        g.addDirectedEdge(d, e);


        System.out.println(g);

        g.BFS(a);

    }
    // a    b	distance	e	c
    // a    b   c   distance   e
    public static void main(String[] args) {

        Graph g = getGraph6();


        System.out.println(g);

        g.DFS(new DFSAlgoAttributes());

        GraphUtils.printColors(g);
        GraphUtils.printDistance(g);
        GraphUtils.printBFSPrevious(g);
        GraphUtils.printTimes(g);
        GraphUtils.printBraceNotation(g);
        GraphUtils.printCC(g);

        System.out.println("Strongly connected components ----------->");
        g.stronglyConnectedComponents();
    }



    public void _22_2_7_findDiameter(int s){

    }
    public void _22_2_7_BFS(int s){

    }


}
