import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;




public class FastCollinearPoints {
    
    private ArrayList<LineSegment> lineSegments;
    
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
        validateInputs(points);
        
        lineSegments = new ArrayList<LineSegment>();
        Point[] slopeOrderedPoints = points.clone();
        
        ArrayList<Point> thisSegmentPoints = new ArrayList<Point>();
        
        for (Point thisPoint: points) {
            
            Arrays.sort(slopeOrderedPoints, thisPoint.slopeOrder());
            
            for (int i = 1; i < points.length; i++) {
                // Condition for collinearity
                if (thisPoint.slopeTo(slopeOrderedPoints[i]) == thisPoint.slopeTo(slopeOrderedPoints[i-1])) {
                    thisSegmentPoints.add(slopeOrderedPoints[i]);
                } else {
                    if (thisSegmentPoints.size() >= 4) {
                        checkAndAddSegment(thisSegmentPoints);
                    }
                    // Clear existing elements & add the current points for next pass through
                    thisSegmentPoints.clear();
                    thisSegmentPoints.add(thisPoint);
                    thisSegmentPoints.add(slopeOrderedPoints[i]);
                }
            }
        }
    }
    
    // If the line segment formed by this set of collinear points is not already added,
    // add it to the list. 
    private void checkAndAddSegment(ArrayList<Point> pointsInSegment) {
        Point[] points = pointsInSegment.toArray(new Point[pointsInSegment.size()]);
        Arrays.sort(points);
        LineSegment thisSegment = new LineSegment(points[0], points[pointsInSegment.size() - 1]);
        
        // StdOut.println("_______________New Segment__________");
        for (LineSegment segment: lineSegments) {
            // StdOut.println( "Comparing : " + thisSegment.toString() + " to " + segment.toString());
            if (segment.toString().equals(thisSegment.toString())) {
                // StdOut.println("Equal Skipping");
                // Segment is already added. Skip adding this to list
                return;
            }
        }
        // StdOut.println("Not Found adding..");
        lineSegments.add(thisSegment);
    }
    
    
    
    public int numberOfSegments() {
        return lineSegments.size();
    }
    
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
    
    private void validateInputs(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i-1]) == 0) {
                throw new IllegalArgumentException("Invalid points in points list");
            }
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        In in = new In("./inputs/equidistant.txt");
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
        
        for (LineSegment segment : bcp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
