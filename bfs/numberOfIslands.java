/**
Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Find the number of islands.

Have you met this question in a real interview? Yes
Example
Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]
return 3.

Tags 
Facebook Zenefits Google
Related Problems 
Medium Surrounded Regions 21 %
Hard Number of Islands II
*/

    public int numIslands(boolean[][] grid) {
        int num = 0;
        
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return num;
        }
        int row = grid.length;
        int col = grid[0].length;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j]) {
                    num++;
                    mergeIsland(grid, i, j);
                }
            }
        }
        
        return num;
    }
    
    private void mergeIsland(boolean[][] grid, int i, int j) {
        
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == false ) {
            return;
        }
        
        if (grid[i][j]) {
            grid[i][j] = false;
            mergeIsland(grid, i + 1, j);
            mergeIsland(grid, i - 1, j);
            mergeIsland(grid, i, j + 1);
            mergeIsland(grid, i, j - 1);
        }
    }
