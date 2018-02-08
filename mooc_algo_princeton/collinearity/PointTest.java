import static org.junit.Assert.*;

import org.junit.Test;


public class PointTest {

    Point p = new Point(0, 0);
    Point q1 = new Point(0, 2);
    Point q2 = new Point(2, 0);
    Point q3 = new Point(3, 4);
    Point q4 = new Point(4, 3);
    Point q5 = new Point(-4, 3);

    @Test
    public void testSlopeFunction() {
        assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q1), 0.001);
        assertEquals(0.0, p.slopeTo(q2), 0.0);
        assertEquals(1.3333, p.slopeTo(q3), 0.001);
        assertEquals(0.75, p.slopeTo(q4), 0.001);
        assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(p), 0.001);
        
        assertEquals(Double.POSITIVE_INFINITY, q1.slopeTo(p), 0.001);
        assertEquals(0.0, q2.slopeTo(p), 0.0);
        assertEquals(1.3333, q3.slopeTo(p), 0.001);
        assertEquals(-0.75, q5.slopeTo(p), 0.001);
    }
    
    @Test
    public void testCompare() {
        assertEquals(0, p.compareTo(p));
        assertTrue(p.compareTo(q1) < 0);
        assertTrue(p.compareTo(q2) < 0);
        assertTrue(p.compareTo(q3) < 0);
        assertTrue(p.compareTo(q4) < 0);
        
        assertTrue(q1.compareTo(p) > 0);
        assertTrue(q2.compareTo(p) > 0);
        assertTrue(q3.compareTo(p) > 0);
        assertTrue(q4.compareTo(p) > 0);
    }

}
