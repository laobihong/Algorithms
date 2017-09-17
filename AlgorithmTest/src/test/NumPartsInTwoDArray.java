package test;
import java.util.*;

public class NumPartsInTwoDArray {
    
    public static int numParts(char[][] a) {
        if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
            return 0;
        }
        
        int row = a.length;
        int col = a[0].length;
        int total = row * col;
        Set<Integer> set = new HashSet<Integer>();
        
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int sequence = i * col + j;
                if (!set.contains(sequence)) {
                    // up
                    helper(set, i, j, sequence, '#', true, a);
                    result++;
                }
                if (!set.contains(sequence + total)){
                    // down
                    helper(set, i, j, sequence + total, '#', false, a);
                    result++;
                }
            }
        }
        
        return result;
    }
    
    private static void helper(Set<Integer> set, int curRow, int curCol, int sequence,
                        char expected, boolean up, char[][] a) {
        int row = a.length, col = a[0].length, total = row * col;
        if (curRow < 0 || curRow >= row || curCol < 0 || curCol >= col) {
            return;
        }

        if (set.contains(sequence)) {
            return;
        }

        char c = a[curRow][curCol];
        if (expected != '#' && c != expected) {
            return;
        }
        
        set.add(sequence);
        
        if (c == '/') {
            if (up) {
                // up
                helper(set, curRow - 1, curCol, (curRow - 1) * col + curCol + total, '#', false, a);
                // left
                helper(set, curRow, curCol - 1, curRow * col + (curCol - 1), '\\', true, a);
                helper(set, curRow, curCol - 1, curRow * col + (curCol - 1) + total, '/', false, a);
            } else {
                // down
                helper(set, curRow + 1, curCol, (curRow + 1) * col + curCol, '#', true, a);
                // right
                helper(set, curRow, curCol + 1, curRow * col + (curCol + 1), '/', true, a);
                helper(set, curRow, curCol + 1, curRow * col + (curCol + 1) + total, '\\', false, a);
            }
        } else { // c == '\'
            if (up){
                // up
                helper(set, curRow - 1, curCol, (curRow - 1) * col + curCol + total, '#', false, a);
                // right
                helper(set, curRow, curCol + 1, curRow * col + (curCol + 1), '/', true, a);
                helper(set, curRow, curCol + 1, curRow * col + (curCol + 1) + total, '\\', false, a);
            } else {
                // down
                helper(set, curRow + 1, curCol, (curRow + 1) * col + curCol, '#', true, a);
                // left
                helper(set, curRow, curCol - 1, curRow * col + (curCol - 1), '\\', true, a);
                helper(set, curRow, curCol - 1, curRow * col + (curCol - 1) + total, '/', false, a);
            }
        }
    }

    public static void main(String[] args) {
        char[][] c1 = {{'/', '\\'},{'\\', '/'}}; // should be 5
        System.out.println(numParts(c1));
        char[][] c2 = {{'\\', '\\'}, {'\\', '\\'}}; // should be 4
        System.out.println(numParts(c2));
        char[][] c3 = {{'\\', '/', '/'},{'\\', '/', '\\'}}; // should be 5
        System.out.println(numParts(c3));
    }

}
