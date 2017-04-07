public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(-1);
        
        while(head != null) {
            ListNode temp = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = temp;
        }
        
        return dummy.next;
    }
    
}

// the following answer is from James Royer and is NOT in-place!!
    public ListNode reverse(ListNode head) {
        ListNode answer = null;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ListNode temp = answer;
            answer = new ListNode(cur.val);
            answer.next = temp;
        }
        return answer;
    }

// or even (if constructor can take two parameters)
    public ListNode reverse(ListNode head) {
        ListNode answer = null;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            answer = new ListNode(cur.val, answer);
        }
        return answer;
    }
