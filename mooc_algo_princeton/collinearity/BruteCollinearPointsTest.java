import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPointsTest {
    
    private Point[] readPoints(String filePath) {
        // read the N points from a file
        In in = new In(filePath);
        int N = in.readInt();
        Point[] points = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt()    ;
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        return points;
    }
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testSingleLine() {
        Point[] points = readPoints("./inputs/input8.txt");
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        for (LineSegment segment: bcp.segments()) {
            StdOut.println(segment.toString());
        }
        assertEquals(2, bcp.numberOfSegments());
        
    }

}
