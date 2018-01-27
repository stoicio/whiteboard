import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private static final double THRESHOLD_FACTOR = 1.96;
    
    private final int gridSize,
                      totalCells;

    private final double meanValue, stddevValue, confidenceLoValue, confidenceHiValue;
    
    /*
     * perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int size, int trials) {
        
        if ((size < 1) || (trials < 1)) {
            throw new IllegalArgumentException("Gridsize and Trial Count should atleast be 1");
        }
        
        int trialsCount = trials;
        gridSize = size;
        totalCells = size * size;
        
        double[] trialResults = new double[trialsCount];
        
        for (int count = 0; count < trialsCount; count++) {
            trialResults[count] = doMonteCarloSimulation();
        }

        meanValue = StdStats.mean(trialResults);
        stddevValue = StdStats.stddev(trialResults);

        double thresholdTerm = (THRESHOLD_FACTOR  * stddevValue) / Math.sqrt(trialsCount);

        confidenceLoValue = meanValue - thresholdTerm;
        confidenceHiValue = meanValue + thresholdTerm;
    }
    
    /*
     * Runs a percolation experiment and returns the ratio of number of 
     * open cells to number of total cells;
     */
    private double doMonteCarloSimulation() {
        Percolation container = new Percolation(gridSize);
        int row, col;
        while (!container.percolates()) { // Loop till the container percolates
            row = StdRandom.uniform(1, gridSize+1); // Random number between [1, gridSize]
            col = StdRandom.uniform(1, gridSize+1); // Random number between [1, gridSize
            container.open(row, col);
        }
        return container.numberOfOpenSites() / ((double) totalCells);
    }
    
    public double mean() {
        // sample mean of percolation threshold
        return meanValue;
    }
    
    public double stddev() {
        // sample standard deviation of percolation threshold
        return stddevValue;
    }

    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        return confidenceLoValue;
    }
    
    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return confidenceHiValue;
    }

    public static void main(String[] args) {
        int gridSize = 0, numTrials = 0;
        if (args.length > 0) {
            try {
                gridSize = Integer.parseInt(args[0]);
                numTrials = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Arguments gridSize and trialsCount must be an integer.");
                return;
            }
        } else {
            System.out.format("Invalid number of args. %n%n Usage : java PercolationStats <gridSize> <trialsCount>");
            return;
        }
        
        PercolationStats stats = new PercolationStats(gridSize, numTrials);
        System.out.format("mean                    = %f%n", stats.mean());
        System.out.format("stddev                  = %f%n", stats.stddev());
        System.out.format("95%% confidence interval = [%f, %f]%n", stats.confidenceLo(), stats.confidenceHi());

    }

}
