class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.double_list = DoubleLinkedList()
        self.max_capacity = capacity
        self.key_to_node = dict()
        self.size = 0

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        ret_val = -1
        if key in self.key_to_node:
            node = self.key_to_node[key]
            ret_val = node.value
            if self.double_list.head.next != node:
                self.double_list.unlink_node(node)
                self.double_list.add_to_front(node)
        return ret_val        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: void
        """
        node = None
        
        if key not in self.key_to_node:
            node = Node(key, value)
            self.key_to_node[key] = node
            self.size += 1
        else:
            node = self.key_to_node[key]
            node.value = value
            self.double_list.unlink_node(node)
        
        self.double_list.add_to_front(node)
        
        if self.size > self.max_capacity:
            lru_node = self.double_list.tail.prev
            self.key_to_node.pop(lru_node.key)
            self.double_list.unlink_node(lru_node)
            self.size -= 1
        
class DoubleLinkedList(object):
    
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def unlink_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def add_to_front(self, node):
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node

class Node(object):
    def __init__(self,key=None, value=None):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None


# TODO  : Add Tests
