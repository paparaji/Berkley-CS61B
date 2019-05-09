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
        boolean exist_column = false;
        for (int[] r : grid) {
            for (int c : r) {
                if (c == 1) {
                    count++;
                }
            }
        }
        int temp = 0;
        UnionFind Bubbles = new UnionFind(row * column);
        for (int i = 0; i < darts.length; i++) {
            if (darts[i][0] == 0) {
                temp = count;
            } else {
                temp = count - 1;
            }
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
                                        exist = true;
                                        break;
                                    }
                                }
                            }
                            if (x != 0 && grid[k][x - 1] == 1 && !exist) {
                                for (int y = 0; y < column; y++) {
                                    if (Bubbles.connected(grid[0][y], k * column + x - 1)) {
                                        Bubbles.union(k * column + x, k * column + x - 1);
                                        exist = true;
                                        break;
                                    }
                                }
                            }
                            if (x != column - 1 && grid[k][x + 1] == 1 && !exist) {
                                for (int y = 0; y < column; y++) {
                                    if (Bubbles.connected(grid[0][y], k * column + x + 1)) {
                                        Bubbles.union(k * column + x, k * column + x + 1);
                                        exist = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (exist) {
                            exist_column = true;
                        }
                        exist = false;
                    }
                    if (!exist_column) {
                        break;
                    }
                    exist_column = true;
                }
                grid[darts[i][0]][darts[i][1]] = 1;
                for (int k = 0; k < column; k++) {
                    if (grid[0][k] == 1) {
                        temp = temp - Bubbles.sizeOf(k);
                    }
                }
                result[i] = temp;
                temp = count - 1;
                Bubbles = new UnionFind(row * column);
            }
        }
        return result;
    }
}
