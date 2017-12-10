import unittest


from data_structures import heap_pq


class TestHeapQueue(unittest.TestCase):

    ascending_list = [i for i in range(0, 11, 1)]
    descending_list = [i for i in range(10, -1, -1)]

    @staticmethod
    def less_than_function(a, b):
        return a < b

    @staticmethod
    def greater_than_function(a, b):
        return a > b

    def test_basic_heap_function(self):
        min_heap_queue = heap_pq.HeapPriorityQueue(self.less_than_function,
                                                   self.descending_list)
        max_heap_queue = heap_pq.HeapPriorityQueue(self.greater_than_function,
                                                   self.ascending_list)
        # Assert Heapify - intializing heap with prior data
        self.assertEqual(len(max_heap_queue), 11)
        self.assertEqual(len(min_heap_queue), 11)

        # Test Peek
        self.assertEqual(min_heap_queue.peek(), 0)
        self.assertEqual(max_heap_queue.peek(), 10)

        # Test Push
        min_heap_queue.push(-100)  # Smallest element in heap
        max_heap_queue.push(100)   # largest element in heap
        self.assertEqual(min_heap_queue.peek(), -100)
        self.assertEqual(max_heap_queue.peek(), 100)
        self.assertEqual(len(min_heap_queue), 12)
        self.assertEqual(len(max_heap_queue), 12)

        # Test Pop
        self.assertEqual(min_heap_queue.pop(), -100)  # Smallest element in heap
        self.assertEqual(min_heap_queue.pop(), 0)
        self.assertEqual(max_heap_queue.pop(), 100)   # largest element in heap
        self.assertEqual(max_heap_queue.pop(), 10)
        self.assertEqual(len(min_heap_queue), 10)
        self.assertEqual(len(max_heap_queue), 10)

    def test_assert_empty_throws(self):
        def pop_empty_queue():
            queue = heap_pq.HeapPriorityQueue(self.less_than_function)
            queue.pop()

        def pop_non_empty_queue():
            queue = heap_pq.HeapPriorityQueue(self.less_than_function, [12, 34, 12, 12])
            result = [queue.pop() for i in range(len(queue))]
            self.assertListEqual([12, 12, 12, 34], result)
            queue.pop()  # Pop empty queue
        self.assertRaises(IndexError, pop_empty_queue)
        self.assertRaises(IndexError, pop_non_empty_queue)


if __name__ == '__main__':
    unittest.main()
