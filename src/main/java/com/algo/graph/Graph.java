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
    private static boolean debug = true;
    int type = -1; // 0 matrix, 1 list

    final int V; // no of vertices

    private DFSAlgoAttributes results;


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
        List<String> backEdeges = new ArrayList<String>();
        List<Edge> backEdges = new ArrayList<>();
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

    public DFSAlgoAttributes performDFS(){
        results = new DFSAlgoAttributes();
        return DFS(results);
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
//                request.topologicalSort.add(new Vertex<String>(-1, "|"));
            }
        }
        debug("DFS results -----------------");
        debug(request.dfs);
        debug(request.parenthesisStructure);
        debug("topologicalSort\t"+request.topologicalSort);

        debug(request.pathCount);
        debug("Edges start");
        debug("Tree Edges\t"+request.treeEdge.toString());
        debug("F-E\t"+request.fe.toString());
        debug("B-E\t"+request.backEdeges.toString());
        debug("C-E\t"+request.ce.toString());
        debug("Edges end");

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
        debug(v+"-->\t"+adj);
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
                attr.backEdeges.add(v.data+"-"+u.data);
                attr.backEdges.add(new Edge<>(v, u, -1));
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
            debug("Queue-> "+ q);
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




        debug("\nBFS");
        debug(bfs);

        debug("Edges start");
        debug("T-E\t"+attr.treeEdge.toString());
        debug("F-E\t"+attr.fe.toString());
        debug("B-E\t"+attr.backEdeges.toString());
        debug("C-E\t"+attr.ce.toString());
        debug("Edges end");





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
        debug(sb);


    }






    public void stronglyConnectedComponents(){

        DFSAlgoAttributes dfs1Result = DFS(new DFSAlgoAttributes());



        Graph<E> gt = getTranspose();

        DFSAlgoAttributes<E> dfs2Request = new DFSAlgoAttributes<E>();
        dfs2Request.itr = dfs1Result.topologicalSort.descendingIterator();
        debug("SCG -->> !!!!!");
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

            debug(p+"- "+ q);
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

        debug(sb);
        debug(totalWeight);
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


        debug(g);

        g.BFS(a);

    }
    // a    b	distance	e	c
    // a    b   c   distance   e
    public static void main(String[] args) {

        Graph g = getGraph6();


        debug(g);

//        DFSAlgoAttributes attributes = g.performDFS();

        List be = g.getBackEdges();
        info("Back edges -> \n"+be);
        GraphUtils.printColors(g);
        GraphUtils.printDistance(g);
        GraphUtils.printBFSPrevious(g);
        GraphUtils.printTimes(g);
        GraphUtils.printBraceNotation(g);
        GraphUtils.printCC(g);

        debug("Strongly connected components ----------->");
//        g.stronglyConnectedComponents();
    }

    public List<Edge<E>> getBackEdges() {
        if(results == null) {
            performDFS();
        }
        List backEdges = results.backEdges;
        if(backEdges != null && backEdges.size() > 0) {
            debug("graph has cycle");
            return backEdges;
        }
        else{
            debug("graph does not have cycle");
        }


        return backEdges;
    }

    public LinkedList<Vertex<E>> getTopologicalSort() {
        return  results.topologicalSort;
    }

    public boolean hasCycle() {
        if(results == null) {
            performDFS();
        }
        if(results.backEdges != null && results.backEdges.size() > 0) {
            debug("graph has cycle");
        }
        else{
            debug("graph does not have cycle");
        }
        return  results.backEdges != null && results.backEdges.size() >0;


    }

    private static void debug(Object val) {
        if(debug)
        System.out.println(val);
    }

    private static void info(Object val) {
        System.out.println(val);
    }


    public void _22_2_7_findDiameter(int s){

    }
    public void _22_2_7_BFS(int s){

    }

    public DFSAlgoAttributes getResults() {
        return results;
    }
}
