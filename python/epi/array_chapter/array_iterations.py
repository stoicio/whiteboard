def remove_duplicates_sorted_array(input_array, num_copies_to_keep=1):
    '''Given a sorted array and a postive integer, update the array so that
    for any item 'x' in the array appears only 'num_copies_to_keep' times '''
    curr_idx = idx_to_overwrite = m = num_copies_to_keep
    # Start with  idx = m, we don't care about first m itemsm since we need to keep a minimum
    # of m copies of any item
    while curr_idx < len(input_array):
        if input_array[curr_idx] != input_array[idx_to_overwrite - m]:
            input_array[idx_to_overwrite] = input_array[curr_idx]
            idx_to_overwrite += 1
        curr_idx += 1
    return input_array[:idx_to_overwrite]


def find_bounds(arr, start):
    '''Given a sorted array and a starting index, return a tuple (start, end)
    where start = starting index of a digit, end = ending index of the same digit at start'''
    curr_idx = start
    while (curr_idx < len(arr)):
        if (arr[curr_idx] != arr[start]):
            break
        curr_idx += 1
    return (start, curr_idx)


def if_m_appearances_keep_2_in_list(arr, m):
    '''Given a sorted array and a positive integer m, for any item 'x' that
    appears 'm' time in the array, update array A such that 'x' appears exactly
    min(2,m) times in the updated array'''
    curr_idx = 0
    while (curr_idx < len(arr)):
        (start, end) = find_bounds(arr, curr_idx)
        if (end - start) == m:
            offset = min(2, m)
            arr[start + offset:] = arr[end:]
        curr_idx = end
    return arr
