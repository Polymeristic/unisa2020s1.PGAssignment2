package main;

public abstract class BlockScramble extends TextCoder {
    /**
     * Create BlockScramble and set the message in an uncoded state.
     * If there is no message, use the empty String "".
     * @param msg the message
     */
    public BlockScramble(String msg){ super(msg); }

    /**
     * Create BlockScramble and set both message AND its state.
     * No check is performed to see if the message is coded or not;
     * It is the responsibility of the caller.
     * @param msg the message
     * @param isC true if message is coded, false if not (plain text).
     */
    public BlockScramble(String msg, boolean isC){ super(msg, isC); }

    /**
     * Adds padding to a string for use in block ciphers
     * @param text Text to pad
     * @param dLength Desired length
     * @return A string with required padding to achieve a desired length
     */
    public String padding(String text, int dLength) {
        return String.format("%-" + dLength + "s", text);
    }

    /**
     * Encodes a single block of text
     * @param block Block of text
     * @return The encoded block of text
     */
    protected abstract String encodeBlock(String block);

    /**
     * Decodes a single block of text
     * @param block Block of text
     * @return The decoded block of text
     */
    protected abstract String decodeBlock(String block);
}
