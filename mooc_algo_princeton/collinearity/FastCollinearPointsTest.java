import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class FastCollinearPointsTest {
    
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
    
    @Test
    public void testSingleLine() {
        Point[] points = readPoints("./inputs/input8.txt");
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(2, fcp.numberOfSegments());
    }
    

    @Test
    public void testGrid4() {
        Point[] points = readPoints("./inputs/grid4x4.txt");
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(32, fcp.numberOfSegments());
    }
}
