import unittest

from epi.array_chapter import array_iterations as aiter


class TestArrayChapter(unittest.TestCase):
    def test_remove_duplicates(self):
        cases = [{'input': [1, 1, 2, 3, 4, 5], 'output': range(1, 6)},
                 {'input': [1], 'output': [1]},
                 {'input': [], 'output': []},
                 {'input': [1, 1, 2, 2, 3, 3, 4, 5, 6, 6], 'output': range(1, 7)}
                 ]
        for case in cases:
            self.assertListEqual(aiter.remove_duplicates_sorted_array(case['input']),
                                 case['output'])

    def test_remove_duplicates_with_limit(self):
        cases = [{'input': [1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 5], 'limit': 2,
                 'output': [1, 1, 2, 2, 3, 4, 4, 5, 5]},
                 {'input': [1], 'limit': 2, 'output': [1]},
                 {'input': [], 'limit': 2, 'output': []},
                 {'input': range(1, 5), 'limit': 1, 'output': range(1, 5)},
                 {'input': [1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 5], 'limit': 3,
                 'output': [1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 5]}
                 ]
        for case in cases:
            self.assertListEqual(aiter.remove_duplicates_sorted_array(case['input'], case['limit']),
                                 case['output'])

    def test_if_m_appearances_keep_2_in_list(self):
        cases = [{'input': [1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 5], 'check_appearance': 3,
                 'output': [1, 1, 2, 2, 3, 4, 4, 5, 5]},
                 {'input': [1], 'check_appearance': 2, 'output': [1]},
                 {'input': [], 'check_appearance': 2, 'output': []},
                 {'input': range(1, 5), 'check_appearance': 1, 'output': range(1, 5)},
                 {'input': [1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 5, 5, 5, 5], 'check_appearance': 5,
                 'output': [1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5]}
                 ]
        for case in cases:
            self.assertListEqual(aiter.if_m_appearances_keep_2_in_list(case['input'],
                                 case['check_appearance']), case['output'])


def main():
    unittest.main()


if __name__ == '__main__':
    main()