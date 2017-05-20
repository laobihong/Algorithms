/*
Jump Game II 

 Description
 Notes
 Testcase
 Judge
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Have you met this question in a real interview? Yes
Example
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Tags 
Greedy Array
Related Problems 
Medium Jump Game 37 %
*/

// DP
    public int jump(int[] A) {
        
    int[] steps = new int[A.length];
        
        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) { 
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }
        
        return steps[A.length - 1];
    }

// correct greedy solutions
// each step jump to the furthest, then next time, start from last furthest(the nearest point reached by this time)
// to the new furthest point. Scan through this range(each scan represents one step, so steps += 1) and find the
// furthest point thta any point in this range can reach. Until end >= len - 1, which means reach the last index. 
    public int jump(int[] A) {
        
        int furthest = 0;
        int start = 0, end = 0;
        int steps = 0;
        int len = A.length;
        
        while (end < len - 1) {
            steps += 1;
            for (int i = start; i <= end; i++) {
                if (i + A[i] > furthest) {
                    furthest = i + A[i];
                }
            }
          
            start = end + 1;
            end = furthest;
        }
        
        return steps;
    }

