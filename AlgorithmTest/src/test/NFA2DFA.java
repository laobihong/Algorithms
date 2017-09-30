package test;

import java.util.*;

public class NFA2DFA {
    
    public static boolean NFAToDFA(String input, String[][] trans, String target) {
        HashMap<String, Boolean> map = new HashMap<>();
        return helper(input, map, trans, target);
    }
    
    private static boolean helper(String input, HashMap<String, Boolean> map, String[][] trans, String target) {
        if (map.containsKey(input) && map.get(input)) {
            return true;
        }
        int len = input.length();
        // base case: length is 1
        if (len == 1) {
            if (target.indexOf(input) != -1) {
                map.put(input, true);
                return true;
            } else {
                map.put(input, false);
                return false;
            }
        }
        
        
        List<List<Character>> list = new ArrayList<>();
        for (int i = 0; i < len - 1; i++) {
            List<Character> curList = getCurList(trans, input.charAt(i), input.charAt(i+1));
            if (curList.size() == 0) {
                map.put(input, false);
                return false;
            }
            list.add(curList);
        }
        
        boolean valid = isValid(list, 0, new StringBuilder(), map, trans, target);
        if (valid) {
            map.put(input, true);
            return true;
        } else {
            map.put(input, false);
            return false;
        }
    }
    
    private static boolean isValid(List<List<Character>> list, int start, StringBuilder sb,
    HashMap<String, Boolean> map, String[][] trans, String target) {
        // base case
        if (start == list.size()) {
            return helper(sb.toString(), map, trans, target);
        }
        for (char c: list.get(start)) {
            sb.append(c);
            boolean valid = isValid(list, start + 1, sb, map, trans, target);
            if(valid) {
                return true;
            }
        }
        return false;
    }
    
    private static List<Character> getCurList(String[][] trans, char c1, char c2) {
        List<Character> list = new ArrayList<>();
        String transformation = trans[c1 - 'A'][c2 - 'A']; // ==== ERROR: - 'A' not -'a'
        for(char c: transformation.toCharArray()) { // ==== ERROR: string to charArray!
            list.add(c);
        }
        return list;
    }

    public static void main(String[] args) {
        // test 1: expect true
        String input1 = "ABCD";
        String[][] trans1 = new String[][] {new String[] {"B", "AC", "D", "A"}, new String[] {"D", "BC", "A", ""},
            new String[] {"", "", "", "B"}, new String[] {"", "", "", ""}};
        String target1 = "CD";
        System.out.println(NFAToDFA(input1, trans1, target1));
        // test 2: expect true
        String input2 = "AACC";
        String[][] trans2 = new String[][] {new String[] {"B", "AC", "D", "A"}, new String[] {"D", "BC", "A", "A"},
            new String[] {"", "", "C", "B"}, new String[] {"", "", "C", ""}};
        System.out.println(NFAToDFA(input2, trans2, target1));
        // test 3: expect false
        System.out.println(NFAToDFA(input2, trans1, target1));
    }

}
