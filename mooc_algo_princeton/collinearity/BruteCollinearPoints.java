import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class BruteCollinearPoints {
    
    private List<LineSegment> lineSegments;
    
    public BruteCollinearPoints(Point[] pointsSource) {
        // finds all line segments containing 4 points
        if (pointsSource == null) {
            throw new IllegalArgumentException("Inputs points list contains less than 4 points");
        }
        
        for (Point point: pointsSource) {
            if (point == null) {
                throw new IllegalArgumentException("Invalid null point recieved");
            }
        }
        
        lineSegments = new ArrayList<LineSegment>();
        
        // Create a local copy of the points  
        Point[] points = pointsSource.clone();
        int pointsCount = points.length;
       
        // Sort all the so that its easier to draw line segments with the given API. 
        // if no local copy should be made, sort 4 points before creating line segment
        Arrays.sort(points);
        
        // Validate input. If any point is null or if there are duplicate points,
        // throw new IllegalArgumentException
        validateInputs(points);
        
        double slope_i_j, slope_i_k, slope_i_l;
        
        for (int i = 0; i < pointsCount - 3; i++) {
            for (int j = i + 1; j < pointsCount - 2; j++) {
                for (int k = j + 1; k < pointsCount - 1; k++) {
                    for (int l = k + 1; l < pointsCount; l++) {
                        slope_i_j = points[i].slopeTo(points[j]);
                        slope_i_k = points[i].slopeTo(points[k]);
                        slope_i_l = points[i].slopeTo(points[l]);
                        
                        if ((slope_i_j == slope_i_k) && (slope_i_k == slope_i_l)) {
                            LineSegment thisSegment = new LineSegment(points[i], points[l]);
                            lineSegments.add(thisSegment);
                        }
                    }
                }
            }
        }
    }
    
    public int numberOfSegments() {
        return lineSegments.size();
    }
    
    public LineSegment[] segments() {
        // the line segments
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
        In in = new In("./inputs/input10.txt");
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
        
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        
        for (LineSegment segment : bcp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
 