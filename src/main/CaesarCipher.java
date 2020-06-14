package main;

public class CaesarCipher extends Substitution {
    /**
     * Create a new CaesarCipher
     * @param msg Message to use
     * @param key Key to use
     */
    public CaesarCipher(String msg, String key) {
        super(msg, key);
    }

    /**
     * Create a new CaesarCipher
     * @param msg Message to use
     * @param isC If the message is already encoded
     * @param key Key to use
     */
    public CaesarCipher(String msg, boolean isC, String key) { super(msg, isC, key); }

    /**
     * Create a new CaesarCipher
     * @param msg Message to use
     * @param key Key to use
     */
    public CaesarCipher(String msg, Character key) { super(msg, key.toString()); }

    /**
     * Create a new CaesarCipher
     * @param msg Message to use
     * @param isC If the message is already encoded
     * @param key Key to use
     */
    public CaesarCipher(String msg, boolean isC, Character key) { super(msg, isC, key.toString()); }


    @Override
    protected boolean encode() {
        if (getMessage() == null || key == null)
            return false;

        String msg = getMessage().toUpperCase();
        StringBuilder sb = new StringBuilder();
        // Get abs value of key char
        int intKey = getAbsChar(key);

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            sb.append(encodeChar(c, intKey));
        }

        setMessage(sb.toString(), true);
        return true;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (getMessage() == null || key == null)
            throw new DecodeErrorException("Unable to decode if either message or key are null.");

        String msg = getMessage().toUpperCase();
        StringBuilder sb = new StringBuilder();
        int intKey = getAbsChar(key);

        for (int i = 0; i < msg.length(); i++) {
            char c = getMessage().charAt(i);
            sb.append(decodeChar(c, intKey));
        }

        setMessage(sb.toString().toLowerCase(), false);
        return true;
    }

    /**
     * Create a pre-defined ROT-13 type cipher with N as the key
     * @param message Message to use
     * @return A ROT-13 defined cipher object
     */
    public static CaesarCipher rot13(String message) {
        return new CaesarCipher(message, 'N');
    }
}
