package com.algo.graph;


import com.algo.util.ArrayUtils;
import com.algo.util.GraphUtils;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by a.nigam on 14/11/16.
 */
public abstract class Graph<E> {

    final int V; // no of vertices


    public Vertex<E>[] vlist;

    int time =0;
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

    public void DFS(){
        Iterator<Vertex<E>> itr = vertextIterator();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            v.color = 0;
        }
        time = 0;
        itr = vertextIterator();

        StringBuilder dfs =  new StringBuilder();
        StringBuilder parenthesisStructure =  new StringBuilder();
        while (itr.hasNext()){
            Vertex<E> v = itr.next();
            if(v.color == 0){
                dfsInternal(v, dfs, parenthesisStructure);
            }
        }

        System.out.println(dfs);
        System.out.println(parenthesisStructure);



    }

    protected void dfsInternal(Vertex<E> v, StringBuilder dfs, StringBuilder parenthesisStructure){

        parenthesisStructure.append("(").append(v.data);
        dfs.append(v.data).append("\t");
        v.color = 1;
        v.st = ++ time;
        v.d = time;
        List<Vertex<E>> adj = getAdjacentvertices(v);
        System.out.println(v+"-->\t"+adj);
        for (int i = 0; i < adj.size(); i++) {

            Vertex<E> u = adj.get(i);

            if(u.color == 0){
                u.p = v;

                dfsInternal(u, dfs, parenthesisStructure);

            }


        }
        v.color = 2;
        v.et = ++time;
        parenthesisStructure.append(v.data).append(")");


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


    public void _22_2_7_findDiameter(int s){

    }
    public void _22_2_7_BFS(int s){

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




    static int[][] geTransposeEffecint(int[][] a){
        int[][] b = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {

        }
        return b;
    }

    int[][] geTranspose(int[][] a){
        int[][] b = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    static void dfsDemo(){


        Graph g = new AdjacencyListGraph(5);


        Vertex v0 = new Vertex(0, 0);
        Vertex v1 = new Vertex(1, 1);
        Vertex v2 = new Vertex(2, 2);
        Vertex v3 = new Vertex(3, 3);
        Vertex v4 = new Vertex(4, 4);

        g.addUndirectedEdge(v0, v1);
        g.addUndirectedEdge(v0, v2);
        g.addUndirectedEdge(v1, v2);
        g.addUndirectedEdge(v1, v4);
        g.addUndirectedEdge(v1, v3);
        g.addUndirectedEdge(v2, v3);
        g.addUndirectedEdge(v3, v4);


        System.out.println(g);

        g.DFS();
        GraphUtils.printColors(g);
        GraphUtils.printDistance(g);
        GraphUtils.printBFSPrevious(g);
    }

    static void bfsdemo(){
        Graph g = new AdjacencyListGraph(5);


        Vertex v0 = new Vertex(0, 0);
        Vertex v1 = new Vertex(1, 1);
        Vertex v2 = new Vertex(2, 2);
        Vertex v3 = new Vertex(3, 3);
        Vertex v4 = new Vertex(4, 4);

        g.addUndirectedEdge(v0, v1);
        g.addUndirectedEdge(v0, v2);
        g.addUndirectedEdge(v1, v2);
        g.addUndirectedEdge(v1, v4);
        g.addUndirectedEdge(v1, v3);
        g.addUndirectedEdge(v2, v3);
        g.addUndirectedEdge(v3, v4);


        System.out.println(g);

        g.BFS(v2);
        GraphUtils.printColors(g);
        GraphUtils.printDistance(g);
        GraphUtils.printBFSPrevious(g);



        System.out.println("Print attributes");



        g.printBFSTreePath(v2, v0);
        g.printBFSTreePath(v2, v1);
        g.printBFSTreePath(v2, v2);
        g.printBFSTreePath(v2, v3);
        g.printBFSTreePath(v2, v4);

    }
    public static void dfsdemo1() {

        Graph g = new AdjacencyMatrixGraph(6);


        Vertex u = new Vertex(0, "u");
        Vertex v = new Vertex(1, "v");
        Vertex w = new Vertex(2, "w");
        Vertex x = new Vertex(3, "x");
        Vertex y = new Vertex(4, "y");
        Vertex z = new Vertex(5, "z");


        g.addDirectedEdge(u, v);
        g.addDirectedEdge(u, x);
        g.addDirectedEdge(x, v);
        g.addDirectedEdge(y, x);
        g.addDirectedEdge(v, y);
        g.addDirectedEdge(w, y);
        g.addDirectedEdge(w, z);
        g.addDirectedEdge(z, z);


        System.out.println(g);

        g.DFS();
        GraphUtils.printColors(g);
        GraphUtils.printDistance(g);
        GraphUtils.printBFSPrevious(g);
        GraphUtils.printTimes(g);
        GraphUtils.printBraceNotation(g);


    }

    public static void main(String[] args) {
        dfsdemo1();
    }



}
