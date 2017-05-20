/*
Language 
laobihong 
Jump Game 

 Description
 Notes
 Testcase
 Judge
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

 Notice

This problem have two method which is Greedy and Dynamic Programming.

The time complexity of Greedy method is O(n).

The time complexity of Dynamic Programming method is O(n^2).

We manually set the small data set to allow you pass the test in both ways. This is just to let you learn how to use this problem in dynamic programming ways. If you finish it in dynamic programming ways, you can try greedy method to make it accept again.

Have you met this question in a real interview? Yes
Example
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

Tags 
Greedy Dynamic Programming Array
Related Problems 
Medium Jump Game II
×/

O(n2): DP
public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        int len = A.length;
        boolean[] can = new boolean[len];
        
        can[0] = true;
        for(int i = 1; i < len; ++i) {
            for(int j = 0; j < i; ++j) {
                if(can[j] && (i-j) <= A[j]) {
                    can[i] = true;
                    break;
                }
            }
        }
        
        return can[len - 1];
    }
}

DP sol 2:
    public boolean canJump(int[] A) {
        int n = A.length;
        boolean[] canReach = new boolean[n];
        canReach[0] = true;
        for (int i = 0; i < n - 1; i++) {
            if (canReach[i]) {
                for (int j = i + 1; j <= i + A[i] && j < n; j++) {
                    if (canReach[j]) {
                        continue;
                    }
                    canReach[j] = true;
                }// end for j
            }
        }// end for i
        return canReach[n - 1];
    }


O(n): greedy
public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        int canReach = 0;
        
        for(int i = 0; i < A.length; ++i) {
            if(i <= canReach && A[i] + i > canReach) {
                canReach = A[i] + i;
            }
        }
    
        return canReach >= A.length - 1; // reach the last index
    }
}
