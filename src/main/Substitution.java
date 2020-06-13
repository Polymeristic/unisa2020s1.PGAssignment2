package main;

public abstract class Substitution extends TextCoder {

    /** Key used in substitution */
    protected Character key;

    /** String key used in substitution **/
    protected String stringKey;

    /** If punctuation should be kept or discarded **/
    protected boolean keepPunct;

    public Substitution(String msg, String key) {
        super(msg);
        setKey(key);
    }

    public Substitution(String msg, boolean isC, String key) {
        super(msg, isC);
        setKey(key);
    }

    /**
     * Converts a string to a key and sets it as the ciphers current key
     * @param key Key to use
     */
    protected void stringToKey(String key) {
        key = key.toUpperCase();

        if (key == null || key.length() == 0)
            throw new IllegalArgumentException("String cannot be neither null or 0 characters long.");

        this.key = key.charAt(0);
        this.stringKey = key;
    }

    /**
     * Sets the key of the cipher
     * @param key Key to use
     */
    public void setKey(String key) { stringToKey(key); }

    /**
     * Sets the key of the cipher
     * @param key Key to use
     */
    public void setKey(Character key) { this.key = key; this.stringKey = key.toString(); }

    /**
     * Clears the key from the cipher
     */
    public void clearKey() { key = null; stringKey = null; }

    /**
     * If a key has been set or not
     * @return true if a key has been set
     */
    public boolean hasKey() { return key != null; }

    /**
     * If punctuation should be kept in encoding or not
     * @param keepPunct False to remove punctuation
     */
    public void setKeepPunct(boolean keepPunct) {
        this.keepPunct = keepPunct;
    }

    /**
     * Adds a number to a character, wrapping it around the length of the alphabet (ie, 'B' + 28 -> 'D')
     * @param c character to add to
     * @param addition the numerical absolute addition
     * @return the final char
     */
    protected char addAndWrap(char c, int addition) {
        return (char) getRelChar(Math.floorMod(getAbsChar(c) + addition, ALPHABET_LENGTH));
    }

    /**
     * Encodes a character based on an absolute int key
     * @param c Character to encode
     * @param key Key to use
     * @return The encoded character as a string
     */
    protected String encodeChar(char c, int key) {
        if (Character.isAlphabetic(c)) {
            return String.valueOf(addAndWrap(c, key));
        } else {
            // Keep spaces
            if (keepPunct || Character.isSpaceChar(c)) {
                return String.valueOf(c);
            }
        }

        return "";
    }

    /**
     * Encodes a character based on an absolute int key
     * @param c Character to encode
     * @param key Key to use
     * @return The encoded character as a string
     */
    protected String decodeChar(char c, int key) {
        if (Character.isAlphabetic(c)) {
            return String.valueOf(addAndWrap(c, -key));
        } else {
            if (keepPunct || Character.isSpaceChar(c)) {
                return String.valueOf(c);
            }
        }

        return "";
    }
}
