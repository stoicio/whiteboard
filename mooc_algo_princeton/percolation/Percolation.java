import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    
   
    private static final boolean OPEN = true;
    
    private boolean doesPercolate = false;
    private boolean[] grid;
    private final WeightedQuickUnionUF unionFind;
    private final int topNodeIdx, 
                      bottomNodeIdx;
    private final int gridSize;
    private final String outOfBoundsError;
    private int numOpenSites = 0;
   
    /*
     * creates a n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("Grid Size cannot be less than 1");
        }

        gridSize = n;
        grid = new boolean[n*n];
        topNodeIdx = (n*n);
        bottomNodeIdx = (n*n) + 1;
        // n*n for all cells in grid, 1 each for virtual top and bottom
        unionFind = new WeightedQuickUnionUF((n*n) + 2);
        outOfBoundsError = String.format("Row / Column values must be within 1 and %d", gridSize);
    }

    
    /*
     * Given a 2D coordinate, converts the coordinate to a corresponding position in 
     * a flattened array
     */
    private int subToInd(int row, int col) {
        if ((row < 1) || (row > gridSize) || (col < 1) || (col > gridSize)) {
            throw new IllegalArgumentException(outOfBoundsError);
        }
        // Convert to zero indexed values
        row--;
        col--;
        return (row*gridSize) + col;
    }

    /*
     * open site (row, col) if its not 'open' already
     */
    public void open(int row, int col) {
        int thisNodeIdx = subToInd(row, col),
            neighbourIdx = -1;

        if (grid[thisNodeIdx] == OPEN) {
            // NO WORK TO BE DONE HERE
            return;
        }

        boolean skipMoveUp = false, 
                skipMoveDown = false;

        grid[thisNodeIdx] = OPEN; // Set the node to OPEN
        numOpenSites += 1; // Keep track of number of nodes open


        if (row == 1) {
            unionFind.union(thisNodeIdx, topNodeIdx);
            skipMoveUp = true;

        }

        if (row == gridSize) {
            unionFind.union(thisNodeIdx, bottomNodeIdx);
            skipMoveDown = true;
        }


    if (!skipMoveUp) {
        neighbourIdx = subToInd(row - 1, col);
        if (grid[neighbourIdx] == OPEN) {
            unionFind.union(neighbourIdx, thisNodeIdx);
        }			
    }

        if (!skipMoveDown) {
            neighbourIdx = subToInd(row + 1, col);
            if (grid[neighbourIdx] == OPEN) {
                unionFind.union(neighbourIdx, thisNodeIdx);
            }
        }

        if (col != 1) {
            neighbourIdx = subToInd(row, col - 1);
            if (grid[neighbourIdx] == OPEN) {
                unionFind.union(neighbourIdx, thisNodeIdx);
            }
        }

        if (col != gridSize) {
            neighbourIdx = subToInd(row, col + 1);
            if (grid[neighbourIdx] == OPEN) {
                unionFind.union(neighbourIdx, thisNodeIdx);
            }
        }

    }

    /**
     * Returns true of the given cell is open & false otherwise
     */
    public boolean isOpen(int row, int col) {
        int thisNode = subToInd(row, col);
        return grid[thisNode];
    }

    /*
     * Returns true of the given cell is open & false otherwise
     */
    public boolean isFull(int row, int col) {
        int thisNode = subToInd(row, col);
        return unionFind.connected(topNodeIdx, thisNode);
    }

    /*
     * Returns number of open sites in the grid
     */
    public int numberOfOpenSites() {
        return numOpenSites;

    }

    /*
     * Returns true is the system percolates. False Otherwise 
     */
    public boolean percolates() {
        if (!doesPercolate) {
            doesPercolate = unionFind.connected(topNodeIdx, bottomNodeIdx); 
        }
        return doesPercolate;
    }


    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
