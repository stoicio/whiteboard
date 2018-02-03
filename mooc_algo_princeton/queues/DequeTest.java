import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class DequeTest {
    
    @Test
    public void testBasic() {
        Deque <Integer> testDeque = new Deque<Integer>();
        testDeque.addFirst(10);
        testDeque.addLast(12);
        assertEquals(2, testDeque.size());
        assertEquals(Integer.valueOf(10), testDeque.removeFirst());
        assertEquals(Integer.valueOf(12), testDeque.removeFirst());
        assertEquals(true, testDeque.isEmpty());
        assertEquals(0, testDeque.size());   
        
        testDeque.addLast(10);
        testDeque.addFirst(12);
        assertEquals(2, testDeque.size());
    }
    
    @Test
    public void testIterationBasic() {
        Deque <Integer> testDeque = new Deque<Integer>();
        testDeque.addFirst(10);
        testDeque.addLast(12);
        testDeque.addLast(13);
        
        ArrayList<Integer> testList = new ArrayList<Integer>();
        for (Integer s : testDeque) {
            testList.add(s);
        }
        
        assertEquals(testList.size(), testDeque.size());
        assertEquals(Integer.valueOf(10), testList.get(0));
        assertEquals(Integer.valueOf(12), testList.get(1));
        assertEquals(Integer.valueOf(13), testList.get(2));
    }

}
