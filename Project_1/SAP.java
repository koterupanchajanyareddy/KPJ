import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
public class SAP {
    private final Digraph graph;
    public SAP(Digraph g) {
        this.graph = g;
    }
    //length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (!(check(v) || check(w))) {
            throw new IllegalArgumentException();
        }
        BreadthFirstDirectedPaths bfd1 = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfd2 = new BreadthFirstDirectedPaths(graph, w);
        int length = -1;
        int min = Integer.MAX_VALUE;
        if (ancestor(v,w) == -1)  {
            return -1;
        }
        for (int i = 0; i < graph.V(); i++) {
            if (bfd1.hasPathTo(i) && bfd2.hasPathTo(i)) {
                length = bfd1.distTo(i) + bfd2.distTo(i);
                if (min > length) {
                min = length;
            }
            }
        }
        
        return min;
    }
    private boolean check(int a) {
        if (a<0 || a>=graph.V()) {
            return false;
        }
        return true;
    }
    //a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v,int w) {
        if (!(check(v) || check(w))) {
            throw new IllegalArgumentException();
        }
        BreadthFirstDirectedPaths bfd1 = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfd2 = new BreadthFirstDirectedPaths(graph, w);
        int ancestor = -1;
        int length = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (bfd1.hasPathTo(i) && bfd2.hasPathTo(i)) {
                length = bfd1.distTo(i) + bfd2.distTo(i);
                if (min > length) {
                min = length;
                ancestor= i;
            }
            }
            
        }
        return ancestor;
    }
    //length of shortes ancestral path between any vertec in  vand any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        int min = Integer.MAX_VALUE;
        if (ancestor(v, w) == -1) {
            return -1;
        }
        for (int i : v) {
            if (!check(i)){
                throw new IllegalArgumentException();
            }
            for (int j : w) {
                if (!check(j)){
                    throw new IllegalArgumentException();
                }
                int a =length(i, j);
                if (min > a && a!= -1) {
                    min = a;
                }
            }
        }
        return min;
    }
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        int min = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i : v) {
            if (!check(i)){
                throw new IllegalArgumentException();
            }
            for (int j : w) {
                if (!check(j)){
                    throw new IllegalArgumentException();
                }
                int temp = length(i, j);
                if (temp < min && temp != -1) {
                    ancestor = ancestor(i, j);
                    min = temp;
                }
            }
        }
        return ancestor;
    }
    public static void main(String[] args) {
    }
}