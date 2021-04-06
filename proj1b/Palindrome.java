public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> res_deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i = i + 1){
            res_deque.addLast(word.charAt(i));
        }
        return res_deque;
    }

    private boolean isPalindrome(Deque<Character> word) {
        if (word.size() <= 1) {
            return true;
        } else if (word.removeFirst() == word.removeLast()) {
            return isPalindrome(word);
        }
        return false;
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> word, CharacterComparator c) {
        if (word.size() <= 1) {
            return true;
        } else if (c.equalChars(word.removeFirst(), word.removeLast())) {
            return isPalindrome(word, c);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator c) {
        return isPalindrome(wordToDeque(word), c);
    }
}
