package test;

public class LazyPropagation {

    public static void main(String[] args) {
        int x = (int)Math.ceil(Math.log(0)/Math.log(2));
        System.out.println(2 * (int)Math.pow(2, x) - 1);
    }

}
