import java.util.NoSuchElementException;

public class UnionFind {

    // TODO - Add instance variables?
    private int[] node;
    private int[] size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        node = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            node[i] = -1;
            size[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 | vertex > node.length - 1) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        return size[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        if (node[v1] == -1) {
            return -size[v1];
        } else {
            return node[v1];
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (v1 != v2){
            v1 = find(v1);
            v2 = find(v2);
            if (sizeOf(v1) > sizeOf(v2)) {
                node[v2] = v1;
                size[v1] += size[v2];
            } else {
                node[v1] = v2;
                size[v2] += size[v1];
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int temp = vertex;
        int temp2 = 0;
        while (parent(temp) > 0) {
            temp = parent(temp);
        }
        while (parent(vertex) > 0) {
            temp2 = node[vertex];
            node[vertex] = temp;
            vertex = temp2;
        }
        return temp;
    }
}
