package com.algo.graph;

/**
 * Created by a.nigam on 18/11/16.
 */
public class GraphFactory {



    public static Graph getGraph0() {
        AdjacencyListGraph g = new AdjacencyListGraph(5);


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

        return g;
    }

    static Graph getGraph(){
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

        return g;

    }

    public static Graph getGraph1() {
        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(6);


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
        return g;
    }



    private static Graph getGraph2() {
        Graph g = new AdjacencyMatrixGraph(5);


        Vertex p = new Vertex(0, "p");
        Vertex o = new Vertex(1, "o");
        Vertex r = new Vertex(2, "r");
        Vertex y = new Vertex(3, "y");
        Vertex v = new Vertex(4, "v");



        g.addDirectedEdge(p, o);
        g.addDirectedEdge(o, r);
        g.addDirectedEdge(r, y);
        g.addDirectedEdge(y, v);
        g.addDirectedEdge(o, v);
        return  g;
    }

    public static Graph getGraph4() {

        Graph g = new AdjacencyListGraph(8);


        Vertex a = new Vertex(2, "a");
        Vertex b = new Vertex(1, "b");
        Vertex c = new Vertex(0, "c");
        Vertex d = new Vertex(3, "d");
        Vertex e = new Vertex(4, "e");
        Vertex f = new Vertex(5, "f");
        Vertex g1 = new Vertex(6, "g");
        Vertex h = new Vertex(7, "h");


        g.addDirectedEdge(a, b);
        g.addDirectedEdge(b, c);
        g.addDirectedEdge(b, e);
        g.addDirectedEdge(b, f);
        g.addDirectedEdge(c ,d);
        g.addDirectedEdge(c ,g1);
        g.addDirectedEdge(d, c);
        g.addDirectedEdge(d, h);
        g.addDirectedEdge(h, h);
        g.addDirectedEdge(g1, h);
        g.addDirectedEdge(g1, f);
        g.addDirectedEdge(f, g1);
        g.addDirectedEdge(e, f);
        g.addDirectedEdge(e, a);


        return g;
    }

}
