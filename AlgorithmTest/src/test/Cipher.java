package test;

// ERROR1 ~ ERROR3 (Writing even a simplest test case will help you find those problems!)
// should've come up with the best solution in the first place
// but since the solution we had also took O(NL) time, then maybe it's okay?
// PROACTIVELY write test cases and 
/*
0123...
abcd...z
cdef...b

bad -> dcf

Objective: Implement a function that takes in two strings. Output whether one of the strings could be a shift cipher encrypted version of the other string. Assume that strings only contain characters from 'a' to 'z'.

bad, dcf: true
abc, bcd: true
bad, dcg: false
*/

/*
1. shift length should be consistent, e.g. length 1: a -> b, b -> c
2. same character should be encrypted into same object, 
*/

/*
Objective: Implement a function that takes in a list of strings. Partition the list into groupings such that each group contains all the strings that are shifted versions of one another. Assume that strings only contain characters from 'a' to 'z' and that all strings are the same length greater than 1.

(ab, bc, ac, bd, cd, ae) -> ((ab, bc, cd), (ac, bd), (ae))

N: the number of strings
L: is the length of each string
K: is the length of the alphabet

*/

/*
for example, ab -> bc:1, ab -> cd: 2
naive solution: start from ab, try shift distance from 1 to 25, and generate the results. If
they are in the dictionary, we add them into the same group.
ab, define the shift distance to be 1, bc, we check if cb exists in the passed list

iteration: L, make a set, check if a set contains a string will take constant time
worst case:O(NL)

bd -> ac
*/
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Cipher {
    
    public List<List<String>> grouping(List<String> inputs) {
        List<List<String>> result = new ArrayList<>();
        
        if (inputs == null || inputs.size() == 0) {
            return result;
        }
        
        Set<String> set = new HashSet<String>();
        // O(N)
        for (String s: inputs) {
            set.add(s);
        }
        
        for (String s: inputs) {
            if (set.contains(s)) {
                
                List<String> list = new ArrayList<String>();
                list.add(s);
            
                for (int i = 1; i <= 25; ++i) {
                    String newString = shiftString(s, i);
                    if (set.contains(newString)) {
                        list.add(newString);
                        set.remove(newString);
                    }
                }
                /* ================== ERROR 3: set needs to remove self!
                 * 
                 */
                result.add(list);
            }
            /* ====================== ERROR 1: ======================== 
            // RESULT SHOULD BE INSIDE IF
            // end of check for s
            result.add(list);
            */
        }
        
        return result;
    }
    
    private String shiftString(String s, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) {
            /* ============ERROR 2: =====================================
             *  NEED TO CONVERT INT TO CHAR
            char newChar = c + shift;
            */
            char newChar = (char)(c + shift);
            if (newChar > 'z') {
                newChar -= 26;
            }
            sb.append(newChar);
        }
        return sb.toString();
    }
  
  public boolean cipher(String from, String to) {
      if (from == null || to == null) {
          return false;
      }
      
      if (from.length() != to.length()) {
          return false;
      }
      
      /*============== ERROR 4: inexcusable!
       * why not ask the interviewer if self can be considered a ciphered form of self?
       * 
       */
      
      // same length
      int len = from.length();
      int shift = Integer.MAX_VALUE;
      for (int i = 0; i < len; ++i) {
          char c1 = from.charAt(i);
          char c2 = to.charAt(i);
          if (shift == Integer.MAX_VALUE) {
              shift = c2 - c1;
              // a -> b : 1
              // z -> a : -25
              if (shift < 0) {
                  shift += 26;
              }
          } else {
              int distance = c2 - c1;
              if (distance > 0 && distance != shift) {
                  return false;
              }
              if (distance < 0 && distance + 26 != shift) {
                  return false;
              }
          }
      } // end for
      return true;
  }
    
  public static void main(String[] args) {
      System.out.println((char)('a' + 1));
      char c = 'z';
      char d = (char)(c + 1);
      if (d > 'z') {
          d -= 26;
      }
      System.out.println(d);
  }
}

