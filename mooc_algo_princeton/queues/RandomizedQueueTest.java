import static org.junit.Assert.*;
import org.junit.Test;


public class RandomizedQueueTest {

    @Test
    public void test() {
        RandomizedQueue<Integer> rQ = new RandomizedQueue<Integer>();
        int[] testElems = new int[] {10, 120, 14, 11, 14, 112, 123, 121, 115, 121 };
        
       for (int elem: testElems){
           rQ.enqueue(elem);
       }
       
       assertEquals(testElems.length, rQ.size());
       while (rQ.size() > 0) { rQ.dequeue(); }
       assertEquals(0, rQ.size());
       rQ.enqueue(10);
       assertEquals(1, rQ.size());
    }
    
    @Test
    public void graderTestCase () {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(742); 
        rq.enqueue(772); 
        rq.enqueue(947); 
        rq.enqueue(781);
        rq.size(); // ==> 4 
        rq.enqueue(914); 
        rq.enqueue(492); 
        rq.isEmpty();// ==> false
        rq.enqueue(955); 
        rq.enqueue(0); 
        rq.dequeue();
    }

}
