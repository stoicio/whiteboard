import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;




public class FastCollinearPoints {
    
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();
    
    public FastCollinearPoints(Point[] pointsSource) {
        if (pointsSource == null) {
            throw new IllegalArgumentException("Input array of points cannot be empty");
        }
        
        for (Point point: pointsSource) {
            if (point == null) {
                throw new IllegalArgumentException("Invalid null point recieved");
            }
        }
        
        // Sort points according to their coordinates & check for null / duplicate points
        Point[] points = pointsSource.clone();
        Arrays.sort(points);
        
        for (int i = 1; i < points.length; i++) {
            // Check Duplicates
            if (points[i].compareTo(points[i-1]) == 0) {
                    throw new IllegalArgumentException("Duplicate values found in points list");
            }     
        }
        
        Point[] sortedPts;
        
        for (int i = 0; i < points.length - 3; i++) { // Current Origin point
            sortedPts = points.clone();
            // Sort auxillary array before and after the current origin point
            Arrays.sort(sortedPts, 0, i, sortedPts[i].slopeOrder());
            Arrays.sort(sortedPts, i + 1, points.length, sortedPts[i].slopeOrder());
            
            for (int head = i + 1, tail = i + 2; tail < points.length; head = tail++) {
                double currSlope = sortedPts[i].slopeTo(sortedPts[head]);
                while ( (tail < points.length) &&
                        (Double.compare(currSlope, sortedPts[i].slopeTo(sortedPts[tail])) == 0)) {
                    // Move the tail till we reach the end or till we find a slope that is different
                    // than the slope between the current origin and the first point being checked
                    tail++;
                }
                
                if (tail - head >= 3) {
                    // If this segment is not already added, then create a line segment with these points
                    double prevSlope = Double.NEGATIVE_INFINITY; // Initialize to a degenerate case
                    int prevHead = 0;
                    
                    // If we had already found a segment with the same slope, then the first point of the 
                    // array should contain atleast one point with the same slope.
                    while (prevHead < i) {
                        prevSlope = sortedPts[i].slopeTo(sortedPts[prevHead]);
                        if (prevSlope >= currSlope) break; // Cross over point
                        prevHead++;
                    }
                    
                    // If prevSlope is same as currSlope then this is a overlapping segment already added
                    if (prevSlope != currSlope) {
                        lineSegments.add(new LineSegment(sortedPts[i], sortedPts[tail - 1]));
                    }
                }
            }
        }
    }
    
    public int numberOfSegments() {
        return lineSegments.size();
    }
    
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        In in = new In("./inputs/input56.txt");
        int numPoints = in.readInt();
        Point[] points = new Point[numPoints];
        
        for (int i = 0; i < numPoints; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        FastCollinearPoints bcp = new FastCollinearPoints(points);
        StdOut.println("Number of Segments " + Integer.toString((bcp.numberOfSegments())));
        for (LineSegment segment : bcp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
