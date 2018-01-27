import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.In;


public class PercolationTest {

	@Test
	public void testPercolation() {
		In in = new In("inputs/input8-dups.txt");      // input file
        int n = in.readInt();         // n-by-n percolation system

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        
        assertEquals(perc.numberOfOpenSites(), 34);
        assertEquals(perc.percolates(), true);
        
	}

}
