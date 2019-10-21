package com.algo.graph;

/**
 * Created by a.nigam on 18/11/16.
 */
public class GraphFactory {


    public static Graph getMST24_2Graph() {
        AdjacencyListGraph gr = new AdjacencyListGraph(5);


        Vertex<String> s = new Vertex(0, "s");
        Vertex<String> t = new Vertex(1, "t");
        Vertex<String> x = new Vertex(2, "x");
        Vertex<String> y = new Vertex(3, "y");
        Vertex<String> z = new Vertex(4, "z");


        gr.addDirectedEdge(s, t, 3);
        gr.addDirectedEdge(s, y, 4);

        gr.addDirectedEdge(t, y, 2);
        gr.addDirectedEdge(y, t, 1);


        gr.addDirectedEdge(t, x, 6);
        gr.addDirectedEdge(y, x, 4);
        gr.addDirectedEdge(y, z, 6);
        gr.addDirectedEdge(x, z, 2);
        gr.addDirectedEdge(z, x, 7);
        gr.addDirectedEdge(z, s, 3);


        return gr;
    }


    public static Graph getMST23_5Graph() {
        AdjacencyListGraph gr = new AdjacencyListGraph(9);


        Vertex<String> a = new Vertex(0, "a");
        Vertex<String> b = new Vertex(1, "b");
        Vertex<String> c = new Vertex(2, "c");
        Vertex<String> d = new Vertex(3, "d");
        Vertex<String> e = new Vertex(4, "e");

        Vertex<String> f = new Vertex(5, "f");
        Vertex<String> g = new Vertex(6, "g");
        Vertex<String> h = new Vertex(7, "h");
        Vertex<String> i = new Vertex(8, "i");

        gr.addUndirectedEdge(a, b, 4);
        gr.addUndirectedEdge(b, c, 8);

        gr.addUndirectedEdge(c, d, 7);
        gr.addUndirectedEdge(c, f, 4);

        gr.addUndirectedEdge(d, e, 9);
        gr.addUndirectedEdge(d, f, 14);

        gr.addUndirectedEdge(e, f, 10);
        gr.addUndirectedEdge(f, g, 2);
        gr.addUndirectedEdge(g, h, 1);

        gr.addUndirectedEdge(h, a, 8);
        gr.addUndirectedEdge(h, b, 11);
        gr.addUndirectedEdge(h, i, 7);

        gr.addUndirectedEdge(i, c, 2);
        gr.addUndirectedEdge(i, g, 6);

        return gr;
    }

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
//        g.addUndirectedEdge(v2, v4);

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
//        g.addUndirectedEdge(v3, v4);

        return g;

    }

    public static Graph getGraph1() {
        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(6);


        Vertex u = new Vertex(0, "u");
        Vertex v = new Vertex(1, "v");
        Vertex w = new Vertex(2, "weight");
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


    /*

     -------------->                  -------------->
    |               |                |               |
    a ----> b ----> c ----> d -----> e ----> f ----> g ----> h ----> END
    |               |       |                 |               |
     -----> i ----->        |                  -------------->
    |                       |
    |                       |
     ----> j ---> k-->l---->

    a[1,24]	b[2,15]	c[3,14]	d[4,13]	e[5,12]	f[6,11]	g[7,10]	h[8,9]

    i[16,17]	j[18,23]	k[19,22]	l[20,21]

    	topologicalSort	[h, g, f, e, d, c, b, i, l, k, j, a, |]


     */

    public static Graph getGraph6() {
        Graph g = new AdjacencyListGraph(
                12);


        Vertex a = new Vertex(0, "a");
        Vertex b = new Vertex(1, "b");
        Vertex c = new Vertex(2, "c");
        Vertex d = new Vertex(3, "d");
        Vertex e = new Vertex(4, "e");
        Vertex f = new Vertex(5, "f");
        Vertex g1 = new Vertex(6, "g");
        Vertex h = new Vertex(7, "h");
        Vertex i = new Vertex(8, "i");
        Vertex j = new Vertex(9, "j");
        Vertex k = new Vertex(10, "k");
        Vertex l = new Vertex(11, "l");



        g.addDirectedEdge(a, b);
        g.addDirectedEdge(a, i);
            g.addDirectedEdge(i, c);
//        g.addDirectedEdge(j, a); introduces cycle
//        g.addDirectedEdge(g1, b);

        g.addDirectedEdge(a, j);
            g.addDirectedEdge(j, k);
                g.addDirectedEdge(k, l);
                    g.addDirectedEdge(l, d);
        g.addDirectedEdge(a, c);

        g.addDirectedEdge(b, c);

        g.addDirectedEdge(c, d);
        g.addDirectedEdge(d, e);

        g.addDirectedEdge(e, f);
        g.addDirectedEdge(e, g1);

        g.addDirectedEdge(f, g1);
        g.addDirectedEdge(f, h);

        g.addDirectedEdge(g1, h);

        g.addDirectedEdge(i, c);

        g.addDirectedEdge(j, k);



//        g.addDirectedEdge(b, e);
//        g.addDirectedEdge(c, d);

        return  g;
    }


    public static Graph getGraph5() {
        Graph g = new AdjacencyMatrixGraph(5);


        Vertex a = new Vertex(4, "a");
        Vertex b = new Vertex(3, "b");
        Vertex c = new Vertex(2, "c");
        Vertex d = new Vertex(1, "d");
        Vertex e = new Vertex(0, "e");



        g.addDirectedEdge(a, b);
        g.addDirectedEdge(a, d);
        g.addDirectedEdge(a, c);
        g.addDirectedEdge(b, d);
        g.addDirectedEdge(b, e);
        g.addDirectedEdge(c, d);
        g.addDirectedEdge(d, e);
        return  g;
    }


    private static Graph getGraph2() {
        Graph g = new AdjacencyMatrixGraph(5);


        Vertex p = new Vertex(0, "previous");
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

    public static Graph getMatrixAsGraph(int m, int n) {

        int size = m * n;
        Graph g = new AdjacencyListGraph(size);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

            }
        }


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
