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

// sol 1 (bfs)
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int count = 0;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j]) {
                    count++;
                    mergeIslands(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    private void mergeIslands(boolean[][] grid, int x, int y) {
        
        int[] dx = {0, 1, -1, 0}, dy = {1, 0, 0, -1};
        int row = grid.length, col = grid[0].length;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(x * col + y);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            int curY = index % col;
            int curX = index / col;
            grid[curX][curY] = false;
            for (int k = 0; k < 4; k++) {
                int newX = curX + dx[k];
                int newY = curY + dy[k];
                if ( (newX >= 0 && newX < row && newY >= 0 && newY < col) && grid[newX][newY]) {
                    queue.offer(newX * col + newY);
                }
            }
        }
    }

// sol 2 union - find
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        UnionFind uf = new UnionFind(row * col);
        int[] dx = {1,0,-1,0}, dy = {0, -1, 0, 1};
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (grid[x][y]) {
                    uf.addCount();
                    for (int k = 0; k < 4; k++) {
                        int newX = x + dx[k];
                        int newY = y + dy[k];
                        if ((newX >= 0 && newX < row && newY >= 0 && newY < col) && grid[newX][newY]) {
                            uf.union(newX * col + newY, x * col + y);
                        }
                    }
                } // if
            } // inner for
        } // outer for
        
        return uf.getCount();
    }
    
    
    
    class UnionFind {
        int count;
        int[] father;
        UnionFind(int n) {
            count = 0;
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }
        void addCount() {
            this.count++;
        }
        int getCount() {
            return this.count;
        }
        int find (int n) {
            if (father[n] == n) {
                return n;
            }
            father[n] = find(father[n]);
            return father[n];
        }
        void union (int a, int b) {
            int fatherA = find(a);
            int fatherB = find(b);
            if (fatherA != fatherB) {
                father[fatherA] = fatherB;
                count--;
            }
        }
    }

// sol 3 (deprecated)
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
