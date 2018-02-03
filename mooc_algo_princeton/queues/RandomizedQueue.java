import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int size;
    private Item[] qArray;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        qArray = (Item[]) new Object[2];
        size = 0;
    }
    
    // resizing the array container
    private void resize(int newCapacity) {
        
        Item[] temp = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = qArray[i];
        }
        
        qArray = temp;
    }
    
    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }
    
    // return the number of items on the randomized queue
    public int size() {
        return size;
    }
    
    
    // add the item
    public void enqueue(Item item) {
        
        if (item == null) {
            throw new IllegalArgumentException("Item to be enqueued should not be null");
        }
        
        // IF the array has no more space for new elements, double the size of array
        if (size == qArray.length) { 
            resize(2 * size); 
        }
        
        qArray[size++] = item;
    }
    
    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
         throw new NoSuchElementException("Queue is empty. Cannot dequeue");   
        }
        
        
        int idxToRemove = StdRandom.uniform(size);
        Item item = qArray[idxToRemove];
        qArray[idxToRemove] = qArray[--size]; // Replace the removed item with the last element of the array
        if (size + 1 != qArray.length) {
            qArray[size + 1] = null; // Prevent the last element that was just replaced from loitering    
        }
        
        
        if (size > 0 && size <= qArray.length/ 4) {
            resize(qArray.length / 2);
        }
        return item;
    }
    
    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot sample an empty array");   
           }
        return qArray[StdRandom.uniform(size)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
        
    }
    
    private class RandomIterator implements Iterator<Item> {
        
        Item[] itrArray = (Item[]) new Object[size];
        int currentIdx = 0;
        
        public RandomIterator() {
            // Constructor
            for (int i = 0; i < size; i++) {
                itrArray[i] = qArray[i];
            }
            StdRandom.shuffle(itrArray);
        }

        public boolean hasNext() {
            return (currentIdx < size);
        }
        
        
        public Item next() {
            if (currentIdx >= size) {
                throw new NoSuchElementException("No more elements in Queue to iterate over");
            }
            return itrArray[currentIdx++];
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not suppored!");
        }
    }
}
