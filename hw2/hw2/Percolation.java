package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF map;

    private WeightedQuickUnionUF map2;

    private boolean[][] openMap;

    private int length;

    private int openSize = 0;

    /**
     * Initialize the map
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.map = new WeightedQuickUnionUF(N * N);
        this.map2 = new WeightedQuickUnionUF(N * N);
        for (int i = 1; i < N; i++) {
            map.union(0, i);
            map2.union(0, i);
        }
        for (int i = (N - 1) * N + 1; i < N * N; i++) {
            map2.union((N - 1) * N, i);
        }
        this.openMap = new boolean[N][N];
        this.length = N;
    }

    /**
     * Open the block of the map
     */
    public void open(int row, int col) {
        if (row > length - 1 || col > length - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!openMap[row][col]) {
            openSize += 1;
            openMap[row][col] = true;
            if (row != 0) {
                if (openMap[row - 1][col]) {
                    map.union((row - 1) * length + col, row * length + col);
                    map2.union((row - 1) * length + col, row * length + col);
                }
            }
            if (col != 0) {
                if (openMap[row][col - 1]) {
                    map.union(row * length + col - 1, row * length + col);
                    map2.union(row * length + col - 1, row * length + col);
                }
            }
            if (col != length - 1) {
                if (openMap[row][col + 1]) {
                    map.union(row * length + col, row * length + col + 1);
                    map2.union(row * length + col, row * length + col + 1);
                }
            }
            if (row != length - 1) {
                if (openMap[row + 1][col]) {
                    map.union((row + 1) * length + col, row * length + col);
                    map2.union((row + 1) * length + col, row * length + col);
                }
            }
        }
    }

    /**
     * Return whether the block has been opened
     */
    public boolean isOpen(int row, int col) {
        if (row > length - 1 || col > length - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return openMap[row][col];
    }

    /**
     * Return whether the block is connected to the top
     */
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        if (row > length - 1 || col > length - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return map.connected(0, row * length + col);
    }

    /**
     * Return the number of blocks that have been opened
     */
    public int numberOfOpenSites() {
        return this.openSize;
    }

    /**
     * Return whether the map is percolated
     */
    public boolean percolates() {
        return map2.connected(0, (length - 1) * length);
    }

    public static void main(String[] args) {

    }
}
