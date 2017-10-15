package test;

// LinkedIn interview question
public class KSubsetsWithEqualSum {

    public static boolean arrayKBucketsWithSameSum_mem(int[] input, int k) {
        if (input == null || input.length < k) {
            return false;
        }

        if (k == 1) {
            return true;
        }

        int sum = 0;
        for (int i : input) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        int subset = sum / k;
        int N = input.length;
        int used = 0;
        boolean[] visited = new boolean[1 << N];
        boolean[] isValid = new boolean[1 << N];
        return helper_mem(input, 0, 0, k, subset, used, visited, isValid);

    }

    public static boolean helper_mem(int[] input, int curSum, int curK, int k, int subset, int used, boolean[] visited,
            boolean[] isValid) {
        if (curK == k) {
            // edit 1!!!!! no actual impact?
            visited[used] = true;
            isValid[used] = true;
            return true;
        }

        if (visited[used]) {
            return isValid[used];
        }
        int N = input.length;

        for (int i = 0; i < N; i++) {
            int cur = 1 << i;
            if ((used & cur) != 0 || curSum + input[i] > subset) {
                continue;
            }
            if (curSum + input[i] == subset) {
                if (helper_mem(input, 0, curK + 1, k, subset, used | cur, visited, isValid)) {
                    visited[used] = true;
                    isValid[used] = true;
                    return true;
                }
            } else if (curSum + input[i] < subset) {
                if (helper_mem(input, curSum + input[i], curK, k, subset, used | cur, visited, isValid)) {
                    visited[used] = true;
                    isValid[used] = true;
                    return true;
                }
            }
        }
        visited[used] = true;
        isValid[used] = false;
        return false;
    }

    public static boolean arrayKBucketsWithSameSum(int[] input, int k) {
        if (input == null || input.length < k) {
            return false;
        }

        if (k == 1) {
            return true;
        }

        int sum = 0;
        for (int i : input) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        int subset = sum / k;
        int N = input.length;
        boolean[] used = new boolean[N];
        return helper(input, 0, 0, k, subset, used);

    }

    public static boolean helper(int[] input, int curSum, int curK, int k, int subset, boolean[] used) {
        if (curK == k) {
            return true;
        }
        int N = input.length;
        for (int i = 0; i < N; i++) {
            if (used[i] || curSum + input[i] > subset) {
                continue;
            }
            used[i] = true;
            if (curSum + input[i] == subset) {
                if (helper(input, 0, curK + 1, k, subset, used)) {
                    return true;
                }
            } else if (curSum + input[i] < subset) {
                if (helper(input, curSum + input[i], curK, k, subset, used)) {
                    return true;
                }
            }
            used[i] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] input = new int[24];
        for (int i = 0; i < input.length; i++) {
            input[i] = i + 1;
        }
        long startTime = System.currentTimeMillis();
        System.out.println(arrayKBucketsWithSameSum_mem(input, 10)); // 25ms
        System.out.println(System.currentTimeMillis() - startTime);
        // System.out.println(arrayKBucketsWithSameSum(input, 10)); // 958ms

    }

}
