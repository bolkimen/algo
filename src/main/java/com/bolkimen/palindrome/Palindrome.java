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

        char[] t = addBoundaries(s.toCharArray());
        int n = t.length;
        int[] p = new int[n];
        int c = 0, r = 0;
        for (int i = 1; i < n-1; i++) {
            int i_mirror = c - (i - c);

            p[i] = (r > i) ? Math.min(r-i, p[i_mirror]) : 0;

            // Attempt to expand palindrome centered at i
            while (i + 1 + p[i] < n && i - 1 - p[i] >= 0 && t[i + 1 + p[i]] == t[i - 1 - p[i]])
                p[i]++;

            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }

        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n-1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        int startIndex = (centerIndex - maxLen) / 2;
        return s.substring(startIndex, startIndex + maxLen);
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

        System.out.println(findLongestPalindromeO1("faa"));
        System.out.println(findLongestPalindromeO1("fghbananas"));
        System.out.println(findLongestPalindromeO1("faba"));
    }

}
