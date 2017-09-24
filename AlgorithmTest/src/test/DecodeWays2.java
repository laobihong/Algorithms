package test;

public class DecodeWays2 {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 1;
        }
        int len = s.length();
        final long MOD = (long)Math.pow(10, 9) + 7;  
        System.out.println(MOD);
        long e0 = 1;
        long e1 = 0;
        long e2 = 0;
        for (char c: s.toCharArray()) {
//            int f0 = 0;
//            int f1 = 0;
//            int f2 = 0;
            if (c == '*') {
                long f0 = 9*e0 + 9*e1 + 6*e2;
                long f1 = e0;
                long f2 = e0;
                e0 = f0% MOD;
                e1 = f1;
                e2 = f2;
            } else {
                long f0 = (c > '0'? 1 : 0) * e0 + e1 + (c <= '6'? 1:0) * e2;
                long f1 = (c == '1'? 1 : 0) * e0;
                long f2 = (c == '2'? 1 : 0) * e0;
                e0 = f0%MOD;
                e1 = f1;
                e2 = f2;
            }

        }
        System.out.println(e0);
        return (int)(e0);
    }
    
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE); // "**********1111111111"
        System.out.println(((int)Math.pow(10, 10)));
        System.out.println(((long)Math.pow(10, 10)));
    }

}
