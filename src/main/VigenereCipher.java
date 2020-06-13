package main;

public class VigenereCipher extends Substitution {
    public static void main(String[] a) {
        VigenereCipher c = new VigenereCipher("I'm a teapot!", "GOODY");
        c.setKeepPunct(true);

        c.encode();
    }

    public VigenereCipher(String msg, String key) {
        super(msg, key);
    }

    public VigenereCipher(String msg, boolean isC, String key) {
        super(msg, isC, key);
    }

    @Override
    protected boolean encode() {
        if (getMessage() == null || key == null)
            return false;

        String msg = getMessage().toUpperCase();
        StringBuilder sb = new StringBuilder();

        int[] keyArray = generateKeyArray();

        int cipherIndex = 0;
        int charIndex = 0;
        while (charIndex < msg.length()) {
            char c = msg.charAt(charIndex);

            sb.append(encodeChar(c, keyArray[cipherIndex]));

            if (Character.isAlphabetic(c)) {
                cipherIndex++;
            }
            charIndex++;
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

        int[] keyArray = generateKeyArray();

        int i = 0;
        int ti = 0;
        while (ti < msg.length()) {
            char c = msg.charAt(ti);
            if (Character.isAlphabetic(c)) {
                i++;
            }

            sb.append(decodeChar(c, keyArray[i]));
            ti++;
        }

        setMessage(sb.toString().toLowerCase(), false);
        return true;
    }

    /**
     * Generates a key array based on the current key
     * @return A key array of ints
     */
    private int[] generateKeyArray() {
        int[] keys = new int[getMessage().length()];

        for (int i = 0; i < getMessage().length(); i++) {
            keys[i] = getAbsChar(stringKey.charAt(i % stringKey.length()));
        }

        return keys;
    }
}
