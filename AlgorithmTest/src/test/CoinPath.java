package test;

import java.util.*;

public class CoinPath {
    public static List<Integer> cheapestJump(int[] A, int B) {
        if (A == null || A.length == 0) {
            return new LinkedList<>();
        }
        Map<Integer, Integer> min = new HashMap<>();
        Map<Integer, LinkedList<Integer>> path = new HashMap<>();
        return helper(A, B, 0, min, path);
    }
    private static LinkedList<Integer> helper(int[] A, int B, int start, Map<Integer, Integer> min, Map<Integer, LinkedList<Integer>> path) {
        // base case
        if (A[start] == -1) {
            return new LinkedList<>();
        }
        
        // dp
        if (min.get(start) != null) {
            return path.get(start);
        }
        
        if (start == A.length - 1) {
            min.put(start, A[start]);
            if (!path.containsKey(start)) {
                path.put(start, new LinkedList<>());
            }
            path.get(start).addFirst(start + 1);
            return path.get(start);
        }      
    
        LinkedList<Integer> cur = new LinkedList<>();
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= B; i++) {
            if (start + i < A.length) {
                LinkedList<Integer> next = helper(A, B, start + i, min, path);
                if (next.size() > 0 && min.get(start + i) < minCost) { // NOTE HERE: If == minCost, subsequent i will always be ignored
                    // since it must be lexicographically larger. So no need to update the result.
                    //cur.clear();
                    //cur.addAll(next);
                    cur = new LinkedList<Integer>(next);
                    minCost = min.get(start + i);
                }
            } else {
                break;
            }
        }
        
        if (cur.size() > 0) {
            cur.addFirst(start + 1);
            path.put(start, cur);
            min.put(start, minCost + A[start]);
        }
        
        return cur;
    }
    public static void main(String[] args) {
        System.out.println(cheapestJump(new int[] {1,2,4,-1,2},2));
    }

}
