import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator test = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("1 222 1"));
        assertTrue(palindrome.isPalindrome("&^^&"));
        assertTrue(palindrome.isPalindrome("  "));
        assertTrue(palindrome.isPalindrome("1"));
        assertFalse(palindrome.isPalindrome("whale"));
        assertTrue(palindrome.isPalindrome("xanax"));
        assertTrue(palindrome.isPalindrome("g"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("nhh"));
    }

    @Test
    public void testisPalindrome2() {
        OffByOne comp = new OffByOne();
        assertFalse(palindrome.isPalindrome("tweak", comp));
        assertTrue(palindrome.isPalindrome("flake", comp));
        assertTrue(palindrome.isPalindrome("t", comp));
        assertTrue(palindrome.isPalindrome("", comp));
        assertTrue(palindrome.isPalindrome("flake", comp));
        assertTrue(palindrome.isPalindrome("acdb", comp));
        assertTrue(palindrome.isPalindrome(" ", comp));
        assertTrue(palindrome.isPalindrome("", comp));
        assertFalse(palindrome.isPalindrome("  ", comp));
        assertFalse(palindrome.isPalindrome("acca", comp));
        assertFalse(palindrome.isPalindrome("nhhchr", comp));
    }
}
