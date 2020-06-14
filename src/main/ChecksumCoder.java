package main;

import testing.TestModule;

public class ChecksumCoder extends ErrorDetection {

    /** Weights for the checksum */
    private int[] weights = new int[] { 3, 5 };

    /**
     * Create a new checksum coder
     * @param msg Message to use
     */
    public ChecksumCoder(String msg) { super(msg); }

    /**
     * Create a new checksum coder
     * @param msg Message to use
     * @param isC If the message is already coded
     */
    public ChecksumCoder(String msg, boolean isC) { super(msg, isC); }

    /**
     * Create a new checksum coder
     * @param msg Message to use
     * @param weights Weights for encoding the checksum
     */
    public ChecksumCoder(String msg, int[] weights) { super(msg); this.weights = weights; }

    /**
     * Create a new checksum coder
     * @param msg Message to use
     * @param isC If the message is already coded
     * @param weights Weights for encoding the checksum
     */
    public ChecksumCoder(String msg, boolean isC, int[] weights) { super(msg, isC); this.weights = weights; }

    @Override
    protected boolean encode() {
        if (weights.length == 0 || getMessage() == null)
            throw new IllegalArgumentException("Weight length can't be 0 and message cannot be null.");

        if (getMessage().length() == 0) {
            setMessage("", true);
            return true;
        }

        String[] words = getWords();
        StringBuilder sb = new StringBuilder();

        // Go through all words and process them individually
        for (String word : words) {
            int chk = 0;
            String lc = word.toUpperCase();

            // Go over ever char
            for (int i = 0; i < word.length(); i++) {
                char c = lc.charAt(i);

                // If they're alphabetic add them to the weighting
                if (Character.isAlphabetic(c)) {
                    chk += getAbsChar(c) * weights[i % weights.length];
                }
            }

            // Append the relative char for the checksum char
            sb.append(word).append((char) getRelChar(chk % ALPHABET_LENGTH)).append(" ");
        }

        setMessage(sb.substring(0, sb.length() - 1), true);
        return true;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (weights.length == 0 || getMessage() == null)
            throw new IllegalArgumentException("Weight length can't be 0 and message cannot be null.");

        if (getMessage().length() == 0) {
            setMessage("", false);
            return true;
        }

        String[] words = getWords();
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            int chk = 0;
            String lc = word.toUpperCase();

            // Go over ever char EXCEPT the last one in the word, this is the checksum char
            for (int i = 0; i < word.length() - 1; i++) {
                char c = lc.charAt(i);

                // If they're alphabetic add them to the weighting
                if (Character.isAlphabetic(c)) {
                    chk += getAbsChar(c) * weights[i % weights.length];
                }
            }

            // Check the checksum char
            int chkChar = chk % ALPHABET_LENGTH;

            // Check if checksum matches, if no then switch to ? or throw err
            if (chkChar == getAbsChar(word.charAt(word.length() - 1))) {
                sb.append(word, 0, word.length() - 1).append(" ");
            } else {
                if (handleExcept) {
                    throw new DecodeErrorException("Unable to decode word '" + word + "'");
                }

                sb.append(new String(new char[word.length() - 1]).replace("\0", "?")).append(" ");
            }
        }

        setMessage(sb.substring(0, sb.length() - 1), false);
        return true;
    }

    /**
     * Set the weights for the encoder
     * @param weights weights
     */
    public void setWeights(int[] weights) { this.weights = weights; }
}
