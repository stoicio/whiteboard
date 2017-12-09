import unittest

suite = unittest.TestLoader().discover('./tests/unit/', pattern='*_test.py')
unittest.TextTestRunner().run(suite)
