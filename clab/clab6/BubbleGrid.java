import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        int count = 0;
        int row = grid.length;
        int column = grid[0].length;
        int[] result = new int[darts.length];
        boolean exist = false;
        boolean exist_bubble = false;
        for (int[] r : grid) {
            for (int c : r) {
                if (c == 1) {
                    count++;
                }
            }
        }
        int temp = count;
        UnionFind Bubbles = new UnionFind(row * column);
        for (int i = 0; i < darts.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (grid[darts[i][0]][darts[i][1]] == 0) {
                    result[i] = 0;
                } else {
                    grid[darts[i][0]][darts[i][1]] = 0;
                    for (int k = 1; k < row; k++) {
                        for (int x = 0; x < column; x++) {
                            if (grid[k][x] == 1) {
                                if (grid[k - 1][x] == 1) {
                                    for (int y = 0; y < column; y++) {
                                        if (Bubbles.connected(grid[0][y], (k - 1) * column + x)) {
                                            Bubbles.union(k * column + x, (k - 1) * column + x);
                                            exist_bubble = true;
                                        }
                                    }
                                }
                                if (x != 0 && grid[k][x - 1] == 1) {
                                    for (int y = 0; y < column; y++) {
                                        if (Bubbles.connected(grid[0][y], k * column + x - 1)) {
                                            Bubbles.union(k * column + x, k * column + x - 1);
                                            exist_bubble = true;
                                        }
                                    }

                                }
                                if (x != column - 1 && grid[k][x + 1] == 1) {
                                    for (int y = 0; y < column; y++) {
                                        if (Bubbles.connected(grid[0][y], k * column + x + 1)) {
                                            Bubbles.union(k * column + x, k * column + x + 1);
                                            exist_bubble = true;
                                        }
                                    }
                                }
                            }
                            if (exist_bubble) {
                                exist = true;
                            } else {
                                grid[k][x] = 0;
                            }
                        }
                        if (!exist) {
                            break;
                        }
                    }
                    grid[darts[i][0]][darts[i][1]] = 1;
                    if (grid[darts[i][0]][darts[i][1]] == 1) {
                        for (int y = 0; y < column; y++) {
                            if (Bubbles.connected(grid[0][y], (darts[i][0] - 1) * column + darts[i][1])) {
                                Bubbles.union(darts[i][0] * column + darts[i][1], (darts[i][0] - 1) * column + darts[i][1]);
                            }
                        }
                    }
                    if (darts[i][1] != 0 && grid[darts[i][0]][darts[i][1] - 1] == 1) {
                        for (int y = 0; y < column; y++) {
                            if (Bubbles.connected(grid[0][y], darts[i][0] * column + darts[i][1] - 1)) {
                                Bubbles.union(darts[i][0] * column + darts[i][1], darts[i][0] * column + darts[i][1] - 1);
                            }
                        }

                    }
                    if (darts[i][1] != column - 1 && grid[darts[i][0]][darts[i][1] + 1] == 1) {
                        for (int y = 0; y < column; y++) {
                            if (Bubbles.connected(grid[0][y], darts[i][0] * column + darts[i][1] + 1)) {
                                Bubbles.union(darts[i][0] * column + darts[i][1], darts[i][0] * column + darts[i][1] + 1);
                            }
                        }
                    }
                }
            }
            for (int k = 0; k < column; k++) {
                if (grid[0][k] == 1) {
                    temp = temp - Bubbles.sizeOf(k);
                }
            }
            result[i] = temp;
            exist = false;
            Bubbles = new UnionFind(row * column);
        }
        return result;
    }
}
