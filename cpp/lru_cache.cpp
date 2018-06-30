#include <unordered_map>
#include <iostream>
#include <memory>

class Node;
class DoubleList;
class LRUCache;

using NodePtr = std::shared_ptr<Node>;

class Node {
    public:
        int value;
        int key;
        NodePtr next;
        NodePtr prev;
};

class DoubleList {
public:
    
    DoubleList() :head(std::make_shared<Node>()), tail(std::make_shared<Node>()) {
        head->next = tail;
        tail->prev = head;
    }
    
    NodePtr create_node(int key, int val) {
        NodePtr newNode = std::make_shared<Node>();
        newNode->key = key;
        newNode->value = val;
        return newNode;
    }
    
    void unlink_node(NodePtr node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }
    
    void add_to_front(NodePtr node) {
        node->next = head->next;
        node->prev = head;
        node->next->prev = node;
        head->next = node;
    }

    void print_list() {
        NodePtr curr = head->next;
        while(curr != tail) {
            std::cout << curr->key << "-->";
            curr = curr->next;
        }
        std::cout << std::endl;
    }

    NodePtr get_front() {
        return head->next;
    }

    NodePtr get_back() {
        return tail->prev;
    }
 
private:
    NodePtr head;
    NodePtr tail;
    friend LRUCache;
};

class LRUCache {
public:
    LRUCache(int capacity) : 
        MAX_CAP(capacity),
        double_list(DoubleList()),
        curr_size(0) {
    }
    
    int get(int key) {
        int ret_val = -1;
        auto it = key_to_node.find(key);
        if (it != key_to_node.end()) {
            ret_val = it->second->value;
            if (double_list.get_front() != it->second)  {
                // If its not already at the head move it to the head;
                double_list.unlink_node(it->second);
                double_list.add_to_front(it->second);
            }
        }
        return ret_val;
    }
    
    void put(int key, int value) {
        auto it = key_to_node.find(key);
        NodePtr node;
        if (it == key_to_node.end()) {
            // Key doesn't exist, create new node
            node = double_list.create_node(key, value);
            key_to_node[key] = node;
            curr_size++;
        } else {
            // Key already exists, update key val
            node = key_to_node[key];
            node->value = value;
            double_list.unlink_node(node);
        }
        
        double_list.add_to_front(node);
        
        if (curr_size > MAX_CAP) {
            // If last insert exceeded capacity remove the most recent one.
            node = double_list.get_back();
            double_list.unlink_node(node);
            key_to_node.erase(node->key);
            curr_size--;
        }
    }
    
private:
    DoubleList double_list;
    std::unordered_map<int, NodePtr> key_to_node;
    size_t const MAX_CAP;
    size_t curr_size;
};

int main() { // TODO : Write a test suite for this.
    LRUCache cache = LRUCache(2);// /* capacity */ );

    cache.put(1, 1);
    cache.put(2, 2);

    std::cout << cache.get(1);       // returns 1
    std::cout << std::endl;

    cache.put(3, 3);    // evicts key 2
    std::cout << cache.get(2);       // returns -1 (not found)
    std::cout << std::endl;

    cache.put(4, 4);    // evicts key 1

    std::cout << cache.get(1);       // returns -1 (not found)
    std::cout << std::endl;
    std::cout << cache.get(3);       // returns 3
    std::cout << std::endl;
    std::cout << cache.get(4);       // returns 4
    std::cout << std::endl;
return 0;
}
