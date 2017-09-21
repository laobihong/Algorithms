package test;

public class IntegerToEnglishWords {

    final static String[] LESS_THAN_TWENTY = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    final static String[] TENS = new String[] { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };
    final static String[] THOUSANDS = new String[] { "", "Thousand", "Million", "Billion" };
    final static int THOUSAND = 1000;
    final static int HUNDRED = 100;
    final static int TWENTY = 20;
    final static int TEN = 10;
    final static int ZERO = 0;

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder ans = new StringBuilder();
        int i = 0; // current thousands

        while (num > 0) {
            if (num % THOUSAND != 0) {
                ans.insert(0, helper(num % THOUSAND) + THOUSANDS[i] + " ");
            }
            num /= THOUSAND;
            i++;
        }

        return ans.toString().trim();
    }

    private static String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < TWENTY) {
            return LESS_THAN_TWENTY[num] + " ";
        } else if (num < HUNDRED) {
            return TENS[num / TEN] + " " + helper(num % TEN);
        } else {
            return LESS_THAN_TWENTY[num / HUNDRED] + " Hundred " + helper(num % HUNDRED);
        }
    }

    public static void main(String[] args) {
        System.out.println(numberToWords(1000));

    }

}
