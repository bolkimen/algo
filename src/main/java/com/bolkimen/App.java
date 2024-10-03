package com.bolkimen;

public class App {

    public static void main(String[] args) {
        String phrase= "Return. last   word\n" +
                " \n" +
                "lenaaagth.\"";
        System.out.println(getLastWordLength(phrase));
        System.out.println(getLastWordLength(".hh,.,"));
    }

    public static int getLastWordLength(String phrase) {
        boolean processLastWord = false;
        int wordLength = 0;
        for (int i = phrase.length() - 1; i >= 0; i--) {
            if ((phrase.charAt(i) >= 'a' && phrase.charAt(i) <= 'z') ||
                    (phrase.charAt(i) >= 'A' && phrase.charAt(i) <= 'Z')) {
                processLastWord = true;
                wordLength++;
            } else if (processLastWord) {
                break;
            }
        }
        return wordLength;
    }

}
