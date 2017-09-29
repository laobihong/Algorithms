package test;

public class SplitArrayLargestSum {
    public static int splitArray(int[] nums, int m) {
        long sum = 0;
        long max = 0;
        for (int i: nums) {
            max = Math.max(max, i);
            sum += i;
        }
        long start = max, end = sum;
        while (start < end) {
            long mid = start + (end - start) / 2;
            if (!canSplit(nums, mid, m)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return (int)start;
    }
    
    private static boolean canSplit(int[] nums, long mid, int m) {
        int total = 1;
        long sum = 0;
        for(int i: nums) {
            sum += i;
            if (sum > mid) {
                total++;
                if (total > m) {
                    return false;
                }
                sum = i;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        // add JUnit later
        System.out.println(splitArray(new int[]{7,2,5,10,8}, 2));
    }

}
