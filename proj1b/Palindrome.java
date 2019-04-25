public class Palindrome {
    /** Change the word to the Deque tyoe */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    /** Determine whether the word is Palindrome */
    public boolean isPalindrome(String word) {
        int n = word.length() / 2;
        Deque result = wordToDeque(word);
        for (int i = 0; i < n; i++) {
            char first = (char) result.removeFirst();
            char last = (char) result.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    /** Determine whether the word is Palindrome with the specific rules */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int n = word.length() / 2;
        Deque result = wordToDeque(word);
        for (int i = 0; i < n; i++) {
            char first = (char) result.removeFirst();
            char last = (char) result.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
