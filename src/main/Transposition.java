package main;

import java.util.regex.Matcher;

public abstract class Transposition extends TextCoder {
    public Transposition(String msg) {
        super(msg);
    }

    public Transposition(String msg, boolean isC) {
        super(msg, isC);
    }

    /**
     * Safe replaces a word in a string by only replacing whole words
     * @param sentence Sentence to search through
     * @param key Key word to find and replace
     * @param replace Replacement word
     * @return The modified string
     */
    protected String replaceWord(String sentence, String key, String replace) {
        return sentence.replaceAll("(?i)\\b" + key + "\\b", replace);
    }
}
