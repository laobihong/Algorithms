/**
Sort List

Example
Given 1->3->2->null, sort it to 1->2->3->null.

Challenge 
Tags 
Linked List
Related Problems 
Easy Insertion Sort List 31 %

*/

// mergesort
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
                    using constant space complexity.
     */
    public ListNode sortList(ListNode head) {  
        
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode middle = findMiddle(head);
        ListNode right = sortList(middle.next);
        middle.next = null;
        ListNode left = sortList(head);
        
        return merge(left, right);
    }
    
    private ListNode merge(ListNode left, ListNode right) {
        
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(left != null && right != null) {
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
        else if (right != null) {
            cur.next = right;
        }
        return head.next;
    }
    
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
}
/**
Analysis: 
        ListNode middle = findMiddle(head);
        ListNode right = sortList(middle); // i.e. middle instead of middle.next, stackoverflow!
        middle.next = null;
        ListNode left = sortList(head);

e.g. 1->-1->null
1. middle == slow!!
2. when num of node is odd, front part n + 1, back part n, uneven
*/


// quicksort
// in concat, left could be null, so dummy look for tail each time starting from the head!
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
                    using constant space complexity.
     */
    public ListNode sortList(ListNode head) {  
        
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode middle = findMiddle(head); // middle as the pivot
        ListNode left_dummy = new ListNode(-1), left_tail = left_dummy;
        ListNode mid_dummy = new ListNode(-1), mid_tail = mid_dummy;
        ListNode right_dummy = new ListNode(-1), right_tail = right_dummy;
        
        while(head != null) {
            if(head.val < middle.val) {
                left_tail.next = head;
                left_tail = left_tail.next;
            }
            else if(head.val > middle.val) {
                right_tail.next = head;
                right_tail = right_tail.next;
            }
            else {
                mid_tail.next = head;
                mid_tail = mid_tail.next;
            }
            
            head = head.next;
        }
        
        left_tail.next = null;
        mid_tail.next = null;
        right_tail.next = null;
        
        ListNode left = sortList(left_dummy.next);
        ListNode right = sortList(right_dummy.next);
        
        return concat(left, mid_dummy.next, right);
    }
    
    private ListNode concat(ListNode left, ListNode mid, ListNode right) {
        ListNode dummy = new ListNode(-1), tail = dummy;
        tail.next = left; tail = getTail(tail);
        tail.next = mid; tail = getTail(tail);
        tail.next = right; tail = getTail(tail);
        
        return dummy.next;
    }
    
    private ListNode getTail(ListNode head) {
        if(head == null) {
            return null;
        }
        while(head.next != null) {
            head = head.next;
        }
        return head;
    }
    
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
}
