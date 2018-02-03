import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {

    /**
     * Takes an integer K as a command line argument , reads in a sequence of
     * string from StdIn and randomly prints exactly k of them uniformly at random.
     * 
     * Each item can be printed out exactly once & 0 <= k <= N, number of strings in StdIn
     */
    public static void main(String[] args) {
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        
        while (StdIn.isEmpty() == false) {
            rQ.enqueue(StdIn.readString());
        }
        
        while (k > 0) {
            StdOut.println(rQ.dequeue());
            k--;
        }
    }

}
