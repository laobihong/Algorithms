/**
find the kth smallest number in at row and column sorted matrix.

Have you met this question in a real interview? Yes
Example
Given k = 4 and a matrix:

[
  [1 ,5 ,7],
  [3 ,7 ,8],
  [4 ,8 ,9],
]
return 5

Challenge 
Solve it in O(k log n) time where n is the bigger one between row size and column size.

Tags 
Heap Priority Queue Matrix

*/

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
 public class Point {
     int x;
     int y;
     int val;
     public Point(int x, int y, int val){
         this.x = x;
         this.y = y;
         this.val = val;
     }
 }

Comparator<Point> comparator = new Comparator<Point>(){
  @Override
  public int compare(Point a, Point b) {
      return a.val - b.val;
  }
};

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        if(matrix.length * matrix[0].length < k) {
            return 0;
        }
        
        return vertical(matrix, k);
    }
    
    private int vertical(int[][] matrix, int k) {
        PriorityQueue<Point> heap = new PriorityQueue<Point>(k, comparator);
        
        int min = Math.min(matrix.length,k);
        for(int i = 0; i < min; i++) {
            heap.offer(new Point(i, 0, matrix[i][0]));
        }
        
        for(int i = 0; i < k - 1; i++){
            Point p = heap.poll();
            if(p.y + 1 < matrix[0].length) {
                heap.offer(new Point(p.x, p.y + 1, matrix[p.x][p.y + 1]));
            }
        }
        return heap.peek().val;
    }


}
