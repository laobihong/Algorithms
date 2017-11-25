package test;

import org.junit.Assert;
public class TurnOffTheRightMostSetBit {

    public static int and(int n) {
        return n & (n - 1);
    }
    public static int xor(int n) {
        int mask = 1;
        while((mask & n) == 0) {
            mask = mask << 1;
        }
        return mask ^ n;
    }
    
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            Assert.assertEquals(and(i), xor(i), 0);
            assert xor(i) == and(i): i;
            if (and(i) == xor(i)) {
                System.out.print(i + " ");
                System.out.print(and(i) + " ");
                System.out.println(xor(i));
            }
        }
    }

}
