/**
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 Notice

Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

Example
"A man, a plan, a canal: Panama" is a palindrome.

"race a car" is not a palindrome.

Challenge 
O(n) time without extra memory.

Tags 
String Two Pointers Facebook Zenefits Uber
Related Problems 
Medium Palindrome Linked List 29 %
Medium Longest Palindromic Substring 28 %
*/

public class Solution {
    /**
     * @param s A string
     * @return Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        
        if(s == null || s.length() == 0) {
            return true;
        }
        
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            
            char c1 = Character.toLowerCase(s.charAt(i));
            while(!valid(c1) && i < j) {
                i++;
                c1 = Character.toLowerCase(s.charAt(i));
            }
            
            char c2 = Character.toLowerCase(s.charAt(j));
            while(!valid(c2) && i < j) {
                j--;
                c2 = Character.toLowerCase(s.charAt(j));
            }
            
            if(c1 != c2){
                return false;
            }
        }
        
        return true;
    }
    
    private boolean valid(char c) {
        return Character.isLetterOrDigit(c);
    }
    
}
