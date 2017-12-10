from data_structures import heap_pq


def heap_sort(list_to_sort, sorting_function):
    """ Given a list and a sorting function, uses heap sort to sort the list
    Args:
    list_to_sort (list) : List containing any abstract data type
    sorting_function (function): A function which takes in two args and returns True if
        * first is smaller than second - Ascending order sort
        * first is greater than second - Descending order sort
    Return:
        sorted_list (list)
    """
    q = heap_pq.HeapPriorityQueue(sorting_function, list_to_sort)
    sorted_list = [q.pop() for i in range(len(q))]
    return sorted_list
