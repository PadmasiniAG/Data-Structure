/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private static final double CONF_95 = 1.96;
    private final int t;
    private final double mean_t, stddev_t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        final double[] p;
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException("Enter Valid Lattice Size and Trials");
        t = trials;
        p = new double[t];
        for (int i = 0; i < t; i++) {
            Percolation per = new Percolation(n);
            do {
                per.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            } while (!per.percolates());
            p[i] = (double) per.numberOfOpenSites()/(double) (n*n);
        }
        mean_t = StdStats.mean(p);
        stddev_t = StdStats.stddev(p);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean_t;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (t == 1)
            return Double.NaN;
        else
            return stddev_t;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean()- (stddev()*CONF_95/Math.sqrt(t)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean()+ (stddev()*CONF_95/Math.sqrt(t)));
    }

    public static void main(String[] args) {
        int nn = 0, tt = 0;

        try {
            // Parse the string argument into an integer value.
            nn = Integer.parseInt(args[0]);
            tt = Integer.parseInt(args[1]);
        } catch (IllegalArgumentException nfe) {
            // The first argument isn't a valid integer.  Print
            // an error message, then exit with an error code.
            System.out.println("The arguments must be an +ve integer.");
        }
        PercolationStats ps = new PercolationStats(nn, tt);
        System.out.println("mean   =   " + ps.mean());
        System.out.println("stddev   =   " + ps.stddev());
        System.out.println("95% confidence interval   =   [" + ps.confidenceLo() + ", " + ps.confidenceHi()
                                   + "]");
    }
}
