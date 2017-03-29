/**
Longest Substring Without Repeating Characters 

 Description
 Notes
 Testcase
 Judge
Given a string, find the length of the longest substring without repeating characters.

Have you met this question in a real interview? Yes
Example
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

For "bbbbb" the longest substring is "b", with the length of 1.

Challenge 
O(n) time

Tags 
String Two Pointers Hash Table
*/

// two takeaways:
// 1. set.remove(s.charAt(left)), here we have to remove ALL the chars inside as long as there is still c in set. They are innocent but they must be killed, otherwise we will not be able to remove all c's totally!
// 2. inner while (left < right) rather than left < len!
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        // first try hashmap
        Set<Character> set = new HashSet<Character>();
        int longest = 0;
        int left = 0, right = 0;
        while(right < s.length()) {
            // current char
            char c = s.charAt(right);
            while(set.contains(c) && left < right){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            longest = Math.max(longest, right - left + 1);
            
            right++;
        }
        
        return longest;
    }
    
}
