package com.example.javatest.common;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

public class LongestPalindromeCalculator {

    private static Pattern pattern = Pattern.compile("^[a-zA-Z]*$");

    public static int calculateLongestPalindrome(@NotNull String text) {

        int longestPalindromeSize = 0;

        if (text.length() < 1 || (isPalindrome(text) && isAlpha(text))) {
            return text.length();
        }

        for (int kernelSize = 0, index = 0; kernelSize + index < text.length(); kernelSize++) {
            for (; kernelSize + index < text.length(); index++) {

                String candidate = kernel(text, index, kernelSize);
                if (isAlpha(candidate) && isPalindrome(candidate) && candidate.length() > longestPalindromeSize) {
                    longestPalindromeSize = candidate.length();
                }
            }
            index = 0;
        }

        return longestPalindromeSize;
    }

    private static String kernel(String text, int position, int size) {
        return text.substring(position, position + size + 1);
    }

    private static boolean isAlpha(String text) {
        return pattern.matcher(text).find();
    }

    private static boolean isPalindrome(String text) {
        return text.equals(new StringBuilder(text).reverse().toString());
    }

}
