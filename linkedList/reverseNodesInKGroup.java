/**
Reverse Nodes in k-Group 

 Description
 Notes
 Testcase
 Judge
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.
Only constant memory is allowed.

Have you met this question in a real interview? Yes
Example
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Tags 
Linked List Facebook
Related Problems 
Easy Swap Nodes in Pairs 35 %
Medium Rotate List 25 %
Medium Reverse Linked List II 30 %
Easy Reverse Linked List
*/

// sol1: self in mock
public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        
        int len = getLen(head);
        ListNode tail = dummy, cur = head;
        while (len >= k) {
            int steps = k;
            ListNode tmpTail = cur;
            while (steps > 0) {
                ListNode tmp = cur.next;
                cur.next = tail.next;
                tail.next = cur;
                cur = tmp;
                steps--;
            }
            tail = tmpTail;
            tail.next = cur; // forget to link here during the mock
            len -= k;
        }
        
        return dummy.next;
    }
    
    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

// sol2: Jiuzhang answer
public ListNode reverseKGroup(ListNode head, int k) {
        
        if(head == null || k <= 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        
        while(head.next != null) {
            head = reverseNextKNodes(head, k);
        }
        
        return dummy.next;
    }
 
    private ListNode reverseNextKNodes(ListNode head, int k) {
        ListNode checkEnough = head;
        for(int i = 0; i < k; i++) {
            if(checkEnough.next == null){
                return checkEnough;
            }
            checkEnough = checkEnough.next;
        }
        
        ListNode n1 = head.next;
        ListNode curr = n1;
        ListNode prev = head;
        
        while(k > 0) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            k--;
        }
        
        head.next = prev;
        n1.next = curr;
        
        return n1;
    }


