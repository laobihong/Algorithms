/*
Perfect Squares 

 Description
 Notes
 Testcase
 Judge
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Have you met this question in a real interview? Yes
Example
Given n = 12, return 3 because 12 = 4 + 4 + 4
Given n = 13, return 2 because 13 = 4 + 9

Tags 
Mathematics Dynamic Programming
Related Problems 
Medium Ugly Number II
*/

//sol 1:
     public int numSquares(int n) {
        int[] nums = new int[n + 1]; // note n+1 easier to think and less prone to error
        for (int i = 1; i <= n; i++) {
            nums[i] = i;
            for (int j = 1; j * j <= i; j++) // resist the natural impulse to put j on the left hand side of the termination condition, e.g. j <= sqrt(i). Also, sqrt return a double
            {
                nums[i] = Math.min(1 + nums[i - j * j], nums[i]);
            }
        }
        
        return nums[n];
    }

//sol 2:四整数平方和定理
    public int numSquares(int n) {
        
        while (n % 4 == 0) n /= 4;
        if (n % 8 == 7) return 4;
        for (int a = 0; a * a <= n; ++a) {
            int b = (int)Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                int aPos = 0;
                int bPos = 0;
                if (a > 0) {
                    aPos = 1;
                }
                if (b > 0) {
                    bPos = 1;
                }
                return aPos + bPos;
            }
        }
        return 3;
    }

