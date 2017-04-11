/**
Reverse a linked list from position m to n.
Notice
Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.

Example
Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.

Challenge 
Reverse it in-place and in one-pass

Tags 
Linked List
Related Problems 
Hard Reverse Nodes in k-Group 33 %
Easy Reverse Words in a String 25 %
Easy Reverse Linked List
*/
public class Solution {
    /**
     * @param ListNode head is the head of the linked list 
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
public ListNode reverseBetween(ListNode head, int m , int n) {
        
        if (head == null) {
            return head;
        }
        
        ListNode dummy  = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy;
        for (int i = 1; i < m; i++) {
            first = first.next;
        }
        ListNode second = first.next;
        ListNode newtail = second;
        for(int j = m; j <= n; j++) {
            ListNode temp = second.next;
            second.next = first.next;
            first.next = second;
            second = temp;
        }
        
        newtail.next = second;
        
        return dummy.next;
    }
    
}


