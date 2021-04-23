package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int size;
    private double[] results;
    private int t;
    private double mean;
    private double std;

    private void openNext(Percolation p) {
        int row = StdRandom.uniform(size);
        int col = StdRandom.uniform(size);
        while (p.isOpen(row, col)) {
            row = StdRandom.uniform(size);
            col = StdRandom.uniform(size);
        }
        p.open(row, col);
    }

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        t = T;
        results = new double[T];

        for (int i = 0; i < T; i = i + 1) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                openNext(p);
            }
            results[i] = (double) p.numberOfOpenSites() / (double) N / (double) N;
        }

        mean = 0;
        for (int i = 0; i < T; i = i + 1) {
            mean = mean + results[i];
        }
        mean = mean / T;

        std = StdStats.stddev(results);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return std;
    }

    public double confidenceLow() {
        return mean - 1.96 * std / Math.sqrt((double) t);
    }

    public double confidenceHigh() {
        return mean + 1.96 * std / Math.sqrt((double) t);
    }
}
