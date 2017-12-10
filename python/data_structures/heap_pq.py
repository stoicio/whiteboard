
class HeapPriorityQueue:
    def __init__(self, compare_fn, data=[]):
        """Constructor for Priority Queue, which takes in a comparison function & optional data
        Args :
        compare_fn (function) : Comparison function which defines if the Heap is a Min / max heap
            This gives us flexibility to use this class as a Priority Queue container for any
            abstract data type. The function takes in two args a & b
            If it returns True for a < b - Heap is MinHeap
            If it returns False for a > b - Heap is MaxHeap
        data (list) : Optional list , which contains the objects to be placed in the PQ container.
            This is useful if the list of items to be placed is known prior to constructing the
            function, since the heapify operation is slightly more efficent at O(n) instead of
            performing _sift_up operation for n items each taking amortized log n time resulting in
            a O(nlogn) time
        """

        self._data = data[:]
        self._compare_fn = compare_fn

        # Heapify data initialized through constructor arguments
        # https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/MIT6_006F11_lec04.pdf  # noqa
        if not self.is_empty():
            # start with one level higher than lowest level
            start_idx = self._parent_idx(len(self) - 1)
            # Satisfy heap invariant starting from lowest level, till (& including) the ROOT
            for idx in range(start_idx, -1, -1):
                self._sift_down(idx)
        # EXPLANATION for O(n) : Assuming a COMPLETE binary tree.
        # n/2 nodes @ level 0, n / 4 nodes @ level 1,  n/8 nodes @ level 2 .. 1 node @ level (log n)
        # Amount of work per loop
        # n/4 (1c)  + n/8 (2c)... + 1 (log n * c) --
        # With n/4 set to 2^k
        # (2^k * c) ( 1/2^0 + 2/2^1 + 3/2^2 ... (k+1)/2^k ) ==>  Bounded by a constant
        # C * n/4 * another constant ===> O(n)

    def __len__(self):
        """Return the number of items in the Queue"""
        return len(self._data)

    # Public functions
    def is_empty(self):
        """Returns True if queue is empty"""
        return len(self) == 0

    def push(self, item):
        """Add an item to the queue"""
        self._data.append(item)  # Add item to the end of the list
        # Let item trickle up based on the compare_fn to maintain heap invariant
        self._sift_up(len(self) - 1)

    def pop(self):
        """Remove and return the element at the top of the queue"""
        if self.is_empty():
            raise IndexError("Queue is empty")
        # Swap the item to be popped with the item at the  end of the list
        self._swap(0, len(self) - 1)
        item = self._data.pop()
        # Retore the heap invariant, by letting the swapped element trickle down
        self._sift_down(0)
        return item

    def peek(self):
        """Return the first element in the queue (item that will be popped first)"""
        if self.is_empty():
            raise IndexError("Queue is empty")
        return self._data[0]

    # Private functions
    def _parent_idx(self, idx):
        return (idx - 1) // 2

    def _left_idx(self, idx):
        return (2 * idx) + 1

    def _right_idx(self, idx):
        return (2 * idx) + 2

    def _has_parent(self, idx):
        return idx > 0

    def _has_left_child(self, idx):
        return self._left_idx(idx) < len(self)

    def _has_right_child(self, idx):
        return self._right_idx(idx) < len(self)

    def _swap(self, idx_1, idx_2):
        self._data[idx_1], self._data[idx_2] = self._data[idx_2], self._data[idx_1]

    def _sift_up(self, idx):
        parent = self._parent_idx(idx)
        # if the element is not alreay ROOT and heap invariant is lost, swap the two items
        if self._has_parent(idx) and self._compare_fn(self._data[idx], self._data[parent]):
            self._swap(idx, parent)
            # Recurse starting one level up
            self._sift_up(parent)

    def _sift_down(self, idx):
        # check if the item has a left child, if not its a LEAF node &
        # heap invariant is already maintained for LEAF NODES
        if self._has_left_child(idx):
            left_idx = self._left_idx(idx)
            child_idx_to_swap = left_idx
            # Now check if we have a Right Child, since that could be
            # smaller (/ greater depending on the compare_fn) than item at IDX
            if self._has_right_child(idx):
                right_idx = self._right_idx(idx)
                if self._compare_fn(self._data[right_idx], self._data[left_idx]):
                    child_idx_to_swap = right_idx
            # Check if heap invariant is maintained
            if self._compare_fn(self._data[child_idx_to_swap], self._data[idx]):
                self._swap(child_idx_to_swap, idx)
                # Recurse starting at the lower level. All levels above will have
                # heap invariant satisfied
                self._sift_down(child_idx_to_swap)
