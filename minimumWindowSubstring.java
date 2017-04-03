/**
Given a string source and a string target, find the minimum window in source which will contain all the characters in target.

 Notice

If there is no such window in source that covers all characters in target, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.

Have you met this question in a real interview? Yes
Clarification
Should the characters in minimum window has the same order in target?

Not necessary.
Example
For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"

Challenge 
Can you do it in time complexity O(n) ?

Tags 
LinkedIn Hash Table Facebook
*/
    public String minWindow(String source, String target) {
        if (source == null || target == null) {
           return ""; 
        }
        
        int minLen = Integer.MAX_VALUE;
        String minStr = "";
        
        int[] targetHash = new int[256];
        
        int targetNum = initTargetHash(targetHash, target);
        int sourceNum = 0;
        int sourceLen = source.length();
        for (int right = 0, left = 0; right < sourceLen; right++) {
            char c = source.charAt(right);
            if (targetHash[c] > 0) {
                sourceNum++;
            }
            targetHash[c]--;
            while (sourceNum == targetNum) {
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    minStr = source.substring(left, right + 1);
                }
                if (++targetHash[source.charAt(left)] > 0) {
                    sourceNum --;
                }
                left++;
            }
        }
        return minStr;
    }
    
    private int initTargetHash(int[] targetHash, String target) {
        int targetNum = 0;
        for (char ch : target.toCharArray()) {
            targetNum++;
            targetHash[ch]++;
        }
        return targetNum;
    }
