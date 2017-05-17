public class Solution {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int[][] sum = new int[n][n];
        int[][] mergeExtra = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            mergeExtra[i][i] = 0;
            sum[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }
        
        boolean[][] visited = new boolean[n][n];
        return searchRange(0, n - 1, sum, mergeExtra, visited);
    }
    
    private int searchRange(int start, int end, int[][] sum, int[][] mergeExtra, boolean[][] visited) {
        if (visited[start][end]) {
            return mergeExtra[start][end];
        }
        
        visited[start][end] = true;
        
        if (start != end) {
            mergeExtra[start][end] = Integer.MAX_VALUE;
            for (int i = start; i < end; i++) {
                mergeExtra[start][end] = Math.min(mergeExtra[start][end], sum[start][end] + searchRange(start, i, sum, mergeExtra, visited) + searchRange(i + 1, end, sum, mergeExtra, visited));
            }
        }
        
        return mergeExtra[start][end];
    }
}
