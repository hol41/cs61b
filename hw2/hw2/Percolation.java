package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF blocksUn;
    private WeightedQuickUnionUF topBlocksUn;
    private boolean[][] blocks;
    private int nOpen;
    private int size;
    private int top;
    private int bot;


    private int numberMap(int row, int col) {
        return row * size + col;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        nOpen = 0;
        top = size * size;
        bot = size * size + 1;

        blocks = new boolean[size][size];
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                blocks[i][j] = false;
            }
        }

        blocksUn = new WeightedQuickUnionUF(size * size + 2);
        topBlocksUn = new WeightedQuickUnionUF(size * size + 1);
        for (int i = 0; i < size; i = i + 1) {
            blocksUn.union(top, numberMap(0, i));
            blocksUn.union(bot, numberMap(size - 1, i));
            topBlocksUn.union(top, numberMap(0, i));

        }
    }

    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        blocks[row][col] = true;
        nOpen = nOpen + 1;
        if (row - 1 >= 0) {
            if (isOpen(row - 1, col)) {
                blocksUn.union(numberMap(row, col), numberMap(row - 1, col));
                topBlocksUn.union(numberMap(row, col), numberMap(row - 1, col));

            }
        }

        if (row + 1 < size) {
            if (isOpen(row + 1, col)) {
                blocksUn.union(numberMap(row, col), numberMap(row + 1, col));
                topBlocksUn.union(numberMap(row, col), numberMap(row + 1, col));
            }
        }

        if (col + 1 < size) {
            if (isOpen(row, col + 1)) {
                blocksUn.union(numberMap(row, col), numberMap(row, col + 1));
                topBlocksUn.union(numberMap(row, col), numberMap(row, col + 1));
            }
        }

        if (col - 1 >= 0) {
            if (isOpen(row, col - 1)) {
                blocksUn.union(numberMap(row, col), numberMap(row, col - 1));
                topBlocksUn.union(numberMap(row, col), numberMap(row, col - 1));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return blocks[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return topBlocksUn.connected(top, numberMap(row, col)) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return nOpen;
    }

    public boolean percolates() {
        if (size > 1) {
            return blocksUn.connected(top, bot);
        } else {
            return isOpen(0, 0);
        }
    }
}
