from random import randint
import unittest

from sorting import heap_sort


class TestHeapSort(unittest.TestCase):

    def test_basic_data_types(self):
        ascending_list = [i for i in range(0, 11, 1)]
        descending_list = [i for i in range(10, -1, -1)]

        def less_than_function(a, b):
            return a < b

        def greater_than_function(a, b):
            return a > b

        ascending_sorted_list = heap_sort(descending_list, less_than_function)
        descending_sorted_list = heap_sort(ascending_list, greater_than_function)

        self.assertListEqual(ascending_list, ascending_sorted_list)
        self.assertListEqual(descending_list, descending_sorted_list)

    def test_abstract_data_types(self):

        in_list = [{'key': randint(0, 100), 'value': randint(0, 10000)}
                   for i in range(0, randint(0, 100))]

        def compare_fn(a, b):
            # sort by greater key to create a max_heap
            if a['key'] == b['key']:
                return a['value'] > b['value']  # if keys are equal break tie with value
            else:
                return a['key'] > b['key']

        sorted_list = heap_sort(in_list, compare_fn)

        # Test if the sorted list is in the expected order
        for i in range(len(sorted_list) - 1):
            self.assertEqual(sorted_list[i]['key'] >= sorted_list[i + 1]['key'], True)
            if sorted_list[i]['key'] == sorted_list[i + 1]['key']:
                self.assertEqual(sorted_list[i]['value'] >= sorted_list[i + 1]['value'], True)


if __name__ == '__main__':
    unittest.main()
