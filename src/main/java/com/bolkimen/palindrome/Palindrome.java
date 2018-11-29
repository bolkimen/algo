package com.bolkimen.palindrome;

import java.util.Arrays;

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
    public static String findLongestPalindromeO2(String str) {
        int size = str.length();
        boolean[][] table = new boolean[size][size];

        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < size; ++i)
            table[i][i] = true;

        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < size - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for lengths greater than 2. k is length
        // of substring
        for (int k = 3; k <= size; ++k) {

            // Fix the starting index
            for (int i = 0; i < size - k + 1; ++i)
            {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;

                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    table[i][j] = true;

                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

        printMatrix(table);
        return str.substring(start, start + maxLength);
    }

    private static void printMatrix(boolean[][] m) {
        System.out.println("Table");
        for (int i = 0; i<m.length; i++) {
            for (int j = 0; j<m[i].length; j++) {
                System.out.print("\t" + m[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Begin of Manacher's algorithm
     */
    public static String findLongestPalindromeO1(String s) {
        if (s==null || s.length()==0)
            return "";

        char[] s2 = addBoundaries(s.toCharArray());
        int[] p = new int[s2.length];
        int c = 0, r = 0; // Here the first element in s2 has been processed.
        int m = 0, n = 0; // The walking indices to compare if two elements are the same
        for (int i = 1; i<s2.length; i++) {
            if (i>r) {
                p[i] = 0; m = i-1; n = i+1;
            } else {
                int i2 = c*2-i;
                if (p[i2]<(r-i-1)) {
                    p[i] = p[i2];
                    m = -1; // This signals bypassing the while loop below.
                } else {
                    p[i] = r-i;
                    n = r+1; m = i*2-n;
                }
            }
            while (m>=0 && n<s2.length && s2[m]==s2[n]) {
                p[i]++; m--; n++;
            }
            if ((i+p[i])>r) {
                c = i; r = i+p[i];
            }
        }
        int len = 0; c = 0;
        for (int i = 1; i<s2.length; i++) {
            if (len<p[i]) {
                len = p[i]; c = i;
            }
        }
        char[] ss = Arrays.copyOfRange(s2, c-len, c+len+1);
        return String.valueOf(removeBoundaries(ss));
    }

    private static char[] addBoundaries(char[] cs) {
        if (cs==null || cs.length==0)
            return "||".toCharArray();

        char[] cs2 = new char[cs.length*2+1];
        for (int i = 0; i<(cs2.length-1); i = i+2) {
            cs2[i] = '|';
            cs2[i+1] = cs[i/2];
        }
        cs2[cs2.length-1] = '|';
        return cs2;
    }

    private static char[] removeBoundaries(char[] cs) {
        if (cs==null || cs.length<3)
            return "".toCharArray();

        char[] cs2 = new char[(cs.length-1)/2];
        for (int i = 0; i<cs2.length; i++) {
            cs2[i] = cs[i*2+1];
        }
        return cs2;
    }
    /**
     * End of Manacher's algorithm
     */

    public static void main(String[] args) {
        System.out.println(findLongestPalindromeO3("fghbananas"));
        System.out.println(findLongestPalindromeO3("fa"));

        System.out.println(findLongestPalindromeO2("aa"));
        System.out.println(findLongestPalindromeO2("fghbaaknas"));
        System.out.println(findLongestPalindromeO2("fghbananas"));
        System.out.println(findLongestPalindromeO2("fa"));

        System.out.println(findLongestPalindromeO1("fghbananas"));
        System.out.println(findLongestPalindromeO1("fa"));
    }

}
