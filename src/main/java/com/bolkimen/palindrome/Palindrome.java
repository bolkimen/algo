package com.bolkimen.palindrome;

public class Palindrome {

    /**
     * Bruteforse
     */
    public static String findLongestPalindromeO3(String s) {
        String result = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = chars.length - 1; j >= i + 1; j--) {
                boolean palindrome = true;
                int middle = (j - i) / 2;
                for (int k = i; k <= i + middle; k++) {
                    if (chars[k] != chars[j + i - k]) {
                        palindrome = false;
                        break;
                    }
                }
                if (palindrome && result.length() < j + 1 - i) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    /**
     * Dunamic programming
     * The time complexity can be reduced by storing results of subproblems. The idea is similar to this post.
     * We maintain a boolean table[n][n] that is filled in bottom up manner. The value of table[i][j] is true,
     * if the substring is palindrome, otherwise false. To calculate table[i][j], we first check the value of
     * table[i+1][j-1], if the value is true and str[i] is same as str[j], then we make table[i][j] true.
     * Otherwise, the value of table[i][j] is made false.
     * 11 12 13
     * 21 22 23
     * 31 32 33
     */
    public static String findLongestPalindromeO2(String s) {
        int size = s.length();
        boolean[][] fields = new boolean[size][size];

        return null;
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromeO3("fghbananas"));
        System.out.println(findLongestPalindromeO3("fa"));
    }

}
