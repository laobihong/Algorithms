package test;

import java.util.*;

public class TinyURL {
    Map<String, String> longToShort = new HashMap<>();
    Map<String, String> shortToLong = new HashMap<>();
    char[] dict = new char[]{'0', '1', '2', '3', '4', '5', '6', '7','8','9',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    Random r = new Random();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = getRandom();
        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl,longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl);
    }
    
    private String getRandom() {
        while (true) {
            StringBuilder sb = new StringBuilder("http://tinyurl.com/");
            for (int i = 0; i < 6; i++) {
                sb.append(dict[r.nextInt(62)]);
            }
            String key = sb.toString();
            if (!shortToLong.containsKey(key)){
                return key;
            }
        }
    }
    public static void main(String[] args) {
       String s = "123";
       StringBuilder sb = new StringBuilder("456");
       System.out.println(sb.reverse());
       System.out.println(1<<20);
    }

}
