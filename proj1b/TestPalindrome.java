import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

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
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("nhh"));
    }

    @Test
    public void testisPalindrome2() {
        CharacterComparator test = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", test));
        assertTrue(palindrome.isPalindrome("acdb", test));
        assertFalse(palindrome.isPalindrome("acca", test));
        assertFalse(palindrome.isPalindrome("nhhchr", test));
    }
}