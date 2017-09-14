package test;

import java.util.*;

public class MaxStack {

	ListNode head;
	ListNode tail;
	Integer maxEle;
	Map<Integer, ArrayList<ListNode>> map;
	
	public MaxStack() {
		map = new HashMap<>();
		head = new ListNode(Integer.MAX_VALUE);
		tail = new ListNode(Integer.MIN_VALUE);
		head.next = tail;
		tail.prev = head;
	}
	
	private boolean isEmpty() {
		return head.next == tail;
	}
	
	private void pushHelper(ListNode cur) {
		
		ListNode secondLast = tail.prev;
		secondLast.next = cur;
		cur.prev = secondLast;
		tail.prev = cur;
		cur.next = tail;
	}
	
	public void push(int x) {
		ListNode cur;
		if (isEmpty()) {
			maxEle = x;
			cur = new ListNode(x);
			pushHelper(cur);
			map.put(x, new ArrayList<ListNode>());
			map.get(x).add(cur);
		} else {
			if (x > maxEle) {
				cur = new ListNode(2 * x - maxEle);
				pushHelper(cur);
				maxEle = x;
			} else {
				cur = new ListNode(x);
				pushHelper(cur);
			}
			if (!map.containsKey(x)) {
				map.put(x, new ArrayList<ListNode>());
			}		
			map.get(x).add(cur);
		}
		
		System.out.println("Number inserted: " + x);
	}
	
	public int peek() throws Exception{
		if (isEmpty()) {
			throw new Exception("Empty stack");
		}
		
		int t = tail.prev.val;
		if (t > maxEle) {
			return maxEle;
		} else {
			return t;
		}
	}
	
	public int peekMax() throws Exception{
		if (isEmpty()) {
			throw new Exception("Empty stack");
		}
		
		System.out.println("Current max: " + maxEle);
		return maxEle;
	}
	
	public int popMax() throws Exception {
		if (isEmpty()) {
			throw new Exception("Empty stack");
		}
		ArrayList<ListNode> nodes = map.get(maxEle);
		int temp = maxEle;
		ListNode last = nodes.get(nodes.size() - 1);
		int val = last.val;
		if (val > maxEle) {
			maxEle = 2 * maxEle - val;
		}
		for (ListNode node: nodes) {
			ListNode prevNode = node.prev;
			ListNode nextNode = node.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
		}
		
		map.remove(temp);
		return temp;
	}
	
	public int pop() throws Exception{ 
		if (isEmpty()) {
			throw new Exception("Empty stack");
		}
		
		ListNode popNode = tail.prev;
		ListNode popPrev = popNode.prev;
		popPrev.next = tail;
		tail.prev = popPrev;
		int val = popNode.val;

		if (val > maxEle) {
			int temp = maxEle;
			map.get(temp).remove(map.size() - 1);
			if (map.get(temp).size() == 0) {
				map.remove(temp);
			}
			maxEle = 2 * maxEle - val;
			return temp;
		} else {
			map.get(val).remove(map.size() - 1);
			if (map.get(val).size() == 0) {
				map.remove(val);
			}
			return val;
		}
	}
	
	public static void main(String[] args) throws Exception {
		MaxStack s = new MaxStack();
		s.push(5);
		s.push(3);
		s.peekMax();
		s.push(7);
		s.push(10);
		s.peekMax();
		s.push(10);
		s.push(10);
		s.peekMax();
		s.popMax();
		s.peekMax();
	}
	
}

class ListNode{
	int val;
	ListNode prev;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
}
