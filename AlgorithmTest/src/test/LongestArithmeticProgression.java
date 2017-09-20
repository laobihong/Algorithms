package test;

public class LongestArithmeticProgression {
    public static int longestArithmeticProgression(int[] A) {
        if (A == null) {
            return 0;
        }
        int n = A.length;
        if (n <= 2) {
            return n;
        }

        int[][] longest = new int[n][n]; // longest[i][j]: longest from i to j

        // initialization
        for (int i = 0; i < n - 1; i++) {
            longest[i][i] = 1;
            longest[i][n - 1] = 2;
        }
        longest[n - 1][n - 1] = 1;
        int max = 2;

        for (int j = n - 2; j >= 1; j--) {
            int i = j - 1, k = j + 1;

            while (i >= 0 && k < n) {
                if (A[i] + A[k] > 2 * A[j]) {
                    longest[i][j] = 2;
                    i--;
                } else   if (A[i] + A[k] < 2 * A[j]) {
                    k++;
                    // here we don't need to update longest[j][k]
                    // because the i in last for loop has done this
                    // e.g. i j k o, j and k here are some "i" and "j"
                    // in last iteration
                    // the while below doesn't have to update either
                    // for the same reason
                } else {
                    longest[i][j] = longest[j][k] + 1;
                    max = Math.max(max, longest[i][j]);
                    i--;
                    k++;
                }
            }
            while (i >= 0) {
                longest[i][j] = 2;
                i--;
            }

        }

        return max;
    }

    public static void main(String[] args) {
        int[] a1 = new int[] {1, 7, 10, 15, 27, 29}; // should be 3
        System.out.println(longestArithmeticProgression(a1));
        int[] a2 = new int[] {5, 10, 15, 20, 25, 30}; // should be 6
        System.out.println(longestArithmeticProgression(a2));
        int a3[] = {1, 7, 10, 13, 14, 19}; // should be 4
        System.out.println(longestArithmeticProgression(a3));
        int a4[] = {1, 7, 10, 15, 27, 29}; // should be 3
        System.out.println(longestArithmeticProgression(a4));
        int a5[] = {2, 4, 6, 8, 10}; // should be 5
        System.out.println(longestArithmeticProgression(a5));
    }

}
