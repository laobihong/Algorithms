/**
Rehashing 

 Description
 Notes
 Testcase
 Judge
The size of the hash table is not determinate at the very beginning. If the total size of keys is too large (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a hash table looks like below:

size=3, capacity=4

[null, 21, 14, null]
       ↓    ↓
       9   null
       ↓
      null
The hash function is:

int hashcode(int key, int capacity) {
    return key % capacity;
}
here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.

rehashing this hash table, double the capacity, you will get:

size=3, capacity=8

index:   0    1    2    3     4    5    6   7
hash : [null, 9, null, null, null, 21, 14, null]
Given the original hash table, return the new hash table after rehashing .

 Notice

For negative integer in hash table, the position can be calculated as follow:

C++/Java: if you directly calculate -4 % 3 you will get -1. You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
Python: you can directly use -1 % 3, you will get 2 automatically.
Have you met this question in a real interview? Yes
Example
Given [null, 21->9->null, 14->null, null],

return [null, 9->null, null, null, null, 21->null, 14->null, null]

Tags 
LintCode Copyright Hash Table
Related Problems 
Easy Consistent Hashing
*/

// sol 1: self mock
// use a hashmap to keep track of the tail, so next insert is O(1), and NO extra memory COMPLEXITY(but cost more memory),
// if properly hashed, each chain shouldn't be too long, so the hashmap is not that necessary.
public ListNode[] rehashing(ListNode[] hashTable) {
        if (hashTable == null) {
            return null;
        }
        int len = hashTable.length;
        ListNode[] ans = new ListNode[len * 2];
        Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        
        for (int i = 0; i < len * 2; i++) {
            ans[i] = new ListNode(-1);
            map.put(i, ans[i]);
        }
        
        for (int i = 0; i < len; i++) {
            ListNode node = hashTable[i];
            while (node != null) {
                int index = getIndex(node, len * 2);
                ListNode curTail = map.get(index);
                curTail.next = new ListNode(node.val);
                map.put(index, curTail.next);
                node = node.next;
            }
        }// end for
        
        for (int i = 0; i < len * 2; i++) {
            ans[i] = ans[i].next;
        }
        
        return ans;
    }
    
    private int getIndex(ListNode node, int len) {
        int val = node.val;
// here val could be negative, so + len to ensure it becomes positive
        return (val%len + len) % len;
    }

// sol 2: each time use while to get to the tail of the new table, no need to query against a map
 public ListNode[] rehashing(ListNode[] hashTable) {
        if(hashTable == null || hashTable.length == 0) {
            return hashTable;
        }
        
        ListNode[] newTable = new ListNode[hashTable.length * 2];
        
        // copy nodes into new table
        for(ListNode node: hashTable) {
            while(node != null) {
                insertNode(newTable, node);
                node = node.next;
            }
        }// end for
        
        return newTable;
    }
    
    private void insertNode(ListNode[] newTable, ListNode node) {
        int key = (node.val % newTable.length + newTable.length) % newTable.length;
        /**int key = 0;
        if(node.val >= 0) {
            key = node.val % newTable.length;
        }
        else {
            key = (node.val % newTable.length + newTable.length) % newTable.length;
        }*/
        if(newTable[key] == null) {
            newTable[key] = new ListNode(node.val);
        }
        else{
            ListNode head = newTable[key];
            while(head.next != null) {
                head = head.next;
            }
            head.next = new ListNode(node.val);
        }
        
    }
    
};


