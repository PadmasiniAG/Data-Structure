/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] opensite;
    private final WeightedQuickUnionUF mygrid;
    private final WeightedQuickUnionUF mygridbackwash;
    private int count = 0;
    private final int size;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("index " + n + " is greater than 0 ");
        }
        this.opensite = new boolean[n][n];
        this.size = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.opensite[i][j] = false;
            }
        }

        mygrid = new WeightedQuickUnionUF((n*n)+2); // n2 is top node and n2+1 is bottom node
        mygridbackwash = new WeightedQuickUnionUF((n*n)+1); // n2 is top node and no bottom node
    }

    private void validate(int p, int q) {
        int n = size;
        if (p <= 0 || p > n || q <= 0 || q > n) {
            throw new IllegalArgumentException("index " + p +" & "+ q + " is not between 1 and " + n);
        }
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int n = size;
        if (isOpen(row, col))  // already opened so do nothing
            return;
        opensite[row - 1][col - 1] = true;
        count++;

        if (row != 1) {
            if (isOpen(row - 1, col)) {  // open
                mygrid.union((row - 1)*n+(col - 1), (row - 2)*n+(col - 1));
                mygridbackwash.union((row - 1)*n+(col - 1), (row - 2)*n+(col - 1));
            }
        }
        else {
            mygrid.union(n * n, (row - 1)*n+(col - 1)); // connect to top virtual node
            mygridbackwash.union(n * n, (row - 1)*n+(col - 1)); } // connect to top virtual node

        if (row != n) {
            if (isOpen(row + 1, col)) {  // open
                mygrid.union((row - 1)*n+(col - 1), (row)*n+(col - 1));
                mygridbackwash.union((row - 1)*n+(col - 1), (row)*n+(col - 1));
            }
        }
        else
            mygrid.union((n * n) + 1, (row - 1)*n+(col - 1)); // connect to bottom virtual node

        if (col != 1) {
            if (isOpen(row, col - 1)) { // open
                mygrid.union((row - 1)*n+(col - 1), (row - 1)*n+(col - 2));
                mygridbackwash.union((row - 1)*n+(col - 1), (row - 1)*n+(col - 2));
            }
        }
        if (col != n) {
            if (isOpen(row, col + 1)) { // open
                mygrid.union((row - 1)*n+(col - 1), (row - 1)*n+(col));
                mygridbackwash.union((row - 1)*n+(col - 1), (row - 1)*n+(col)); }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return opensite[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int n = size;
        return mygridbackwash.connected((row-1)*n+(col-1), n*n); // due to backwash problem use mygridbackwash instead of mygrid
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates()
    {
        int n = size;
        return mygrid.connected((n*n)+1, n*n);
    }

    public static void main(String[] args) {
        System.out.println(" Percolation.java ");
    }
}
