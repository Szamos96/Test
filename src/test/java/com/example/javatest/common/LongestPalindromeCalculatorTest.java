package com.example.javatest.common;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestPalindromeCalculatorTest {

    @Test
    public void calculateLongestPalindromeTest(){

        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("aba")).isEqualTo(3);
        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("ab")).isEqualTo(1);
        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("5aba5")).isEqualTo(3);
        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("a5ba")).isEqualTo(1);
        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("assabavd")).isEqualTo(4);
        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("as2sabavd")).isEqualTo(3);
        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("a")).isEqualTo(1);
        Assertions.assertThat(LongestPalindromeCalculator.calculateLongestPalindrome("2")).isEqualTo(0);
    }


}
