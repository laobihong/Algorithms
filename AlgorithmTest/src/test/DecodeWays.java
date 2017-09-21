package test;

public class DecodeWays {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] ways = new int[len + 1];
        ways[1] = s.charAt(0) == '0'? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int lastTwo = Integer.parseInt(s.substring(i - 2, i));
            int way = 0;
            if (lastTwo >= 10 && lastTwo <= 26){
                way = 1;
            }
            char c = s.charAt(i - 1);
            System.out.println(ways[i - 1]);
            System.out.println(c == '0'? 0 : 1);
            int temp = ways[i - 1] + (c == '0'? 0 : 1);
            System.out.println(temp);
            System.out.println(ways[i - 1] + c == '0'? 0 : 1);
            ways[i] = Math.max(way + ways[i - 2], ways[i - 1] + c == '0'? 0 : 1);
        }
        
        return ways[len];
    }
    
    public static void main(String[] args) {
        //System.out.println(numDecodings("11"));
        System.out.println((char)('a' + 1));
    }

}
