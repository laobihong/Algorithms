package test;

import java.util.*;
public class JumpingNumbers {
    // O(n)
    public static ArrayList<Integer> printJumping1(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if(i < 10) {
                ans.add(i);
                System.out.print(i + " ");
            } else {
                if (isJumping1(i)) {
                    ans.add(i);
                    System.out.print(i + " ");
                }
            }
    }
        System.out.println();
        return ans;
    } 

    private static boolean isJumping1(int n) {
        int last = n % 10;
        n /= 10;
        while (n > 0) {
            int cur = n % 10;
            if (Math.abs(last - cur) != 1) {
                return false;
            }
            last = cur;
            n /= 10;
        }
        return true;
    }
    
    // O(k), k is the jumping numbers (i.e. we only consider those numbers)
    public static ArrayList<Integer> printJumping(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 1; i <= 9 && i <= n; i++) {
            res.addAll(bfs(n, i));
        }
        Collections.sort(res);
        return res;
    }
    
    private static ArrayList<Integer> bfs(int n, int i) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur <= n) {
                System.out.print(cur + " ");
                res.add(cur);
                int lastDigit = cur % 10;
                if (lastDigit == 0) {
                    q.offer(cur * 10 + 1);
                } else if (lastDigit == 9) {
                    q.offer(cur * 10 + 8);
                } else {
                    q.offer(cur * 10 + lastDigit + 1);
                    q.offer(cur * 10 + lastDigit - 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("\n" + printJumping(20)); // 0 1 2 3 4 5 6 7 8 9 10 12
        printJumping1(20); // 0 1 2 3 4 5 6 7 8 9 10 12
        System.out.println("\n" + printJumping(105)); //0 1 2 3 4 5 6 7 8 9 10 12 21 23 32 34 43 45 54 56 65 67 76 78 87 89 98 101
        printJumping1(105); //0 1 2 3 4 5 6 7 8 9 10 12 21 23 32 34 43 45 54 56 65 67 76 78 87 89 98 101
    }

}
