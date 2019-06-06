package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

public class PercolationStats {

    private double[] result;

    private int length;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.result = new double[T];
        this.length = N;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < T; i++) {
            int count = 0;
            Percolation temp = pf.make(N);
            while (!temp.percolates()) {
                temp.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
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

    public static void main(String[] args) {
        Stopwatch temp = new Stopwatch();
        PercolationStats temp2 = new PercolationStats(250, 200, new PercolationFactory());
        System.out.println(temp.elapsedTime());
    }
}
