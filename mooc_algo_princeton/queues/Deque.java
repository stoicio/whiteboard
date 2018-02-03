import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    
    // Helper Node - container class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private int size;
    private Node first, last;
    
        
    // Construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
    
    // Is the deque empty
    public boolean isEmpty() {
        return (size == 0);
    }
    // return the number of items on the deque
    public int size() {
        return size;
    }
    
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to add must not be null");
        }
        
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        
        if (size == 0) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        
        size++;
    }
    
    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to add must not be null");
        }
        
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        
        if (size == 0) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }
    
    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove element from empty Deque");
        }
        
        Item item = first.item;

        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }
    
    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove element from empty Deque");
        }
        Item item = last.item;

        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        
        size--;
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
        
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No more elements to iterate over");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException("remove operation not supported");
        }   
    }
}
