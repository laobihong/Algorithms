/**
Merge k Sorted Lists 

 Description
 Notes
 Testcase
 Judge
Merge k sorted linked lists and return it as one sorted list.

Analyze and describe its complexity.

Have you met this question in a real interview? Yes
Example
Given lists:

[
  2->4->null,
  null,
  -1->null
],
return -1->2->4->null.

Tags 
Divide and Conquer Linked List Priority Queue Heap Uber Google Twitter LinkedIn Airbnb Facebook
Related Problems 
Easy Merge Two Sorted Arrays 36 %
Medium Ugly Number II
*/

// sln 1: 
    public ListNode mergeKLists(List<ListNode> lists) {  
        
        if(lists == null || lists.size() == 0) {
            return null;
        }
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });
        
        for(int i = 0; i < lists.size(); i++) {
            if(lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while(!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if(head.next != null) {
                heap.add(head.next);
            }
        }
        
        return dummy.next;
    }
// sln 2: merge in pairs, recursion
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        return sort(lists, 0, lists.size() - 1);
    }
    
    private ListNode sort(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        
        int mid = start + (end - start)/2;
        ListNode left = sort(lists, start, mid);
        ListNode right = sort(lists, mid + 1, end);
        return merge(left, right);
    }
    
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            }
            else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        else {
            cur.next = right;
        }
        return dummy.next;
    }
// sln 3: merge adjacent two lists, using while?
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        while (lists.size() > 1) {
            List<ListNode> newLists = new ArrayList<ListNode>();
            for (int i = 0; i < lists.size() - 1; i += 2) {
                ListNode merged = merge(lists.get(i), lists.get(i + 1));
                newLists.add(merged);
            }
            if (lists.size() % 2 == 1) {
                newLists.add(0, lists.get(lists.size() - 1));
            }
            lists = newLists;
        }
        
        return lists.get(0);
    }
    
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            }
            else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        else {
            cur.next = right;
        }
        return dummy.next;
    }
