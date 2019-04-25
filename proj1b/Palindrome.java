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
        Deque list = wordToDeque(word);
        return isPalindromehelper(list);
    }

    public boolean isPalindromehelper(Deque list) {
        if (list.size() <= 1) {
            return true;
        } else if (list.removeFirst() == list.removeLast()) {
            return isPalindromehelper(list);
        } else {
            return false;
        }
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
