package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

public class PercolationStats {

    private double[] result;

    private int length;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.result = new double[T];
        this.length = N;
        for (int i = 0; i < T; i++) {
            int count = 0;
            Percolation temp = pf.make(N);
            while (!temp.percolates()) {
                int temp1 = StdRandom.uniform(0, N);
                int temp2 = StdRandom.uniform(0, N);
                if (temp.isOpen(temp1, temp2)){
                    continue;
                }
                temp.open(temp1, temp2);
                count += 1;
            }
            result[i] = (double) count / (double) (N * N);
        }
    }

    /**
     * Return the mean of statistics
     */
    public double mean() {
        return StdStats.mean(result);
    }

    /**
     * Return the stand distribution
     */
    public double stddev() {
        return StdStats.stddev(result);
    }

    /**
     * Return the low bound of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(length);
    }

    /**
     * Return the high bound of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(length);
    }

    private static void main(String[] args) {
        int a = StdRandom.uniform(0, 10);
        int b = StdRandom.uniform(0, 10);
        System.out.println(a);
        System.out.println(b);
    }
}
