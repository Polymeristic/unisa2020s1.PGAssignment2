package main;

public class PigLatin extends Transposition {
    public PigLatin(String msg) {
        super(msg);
    }

    public PigLatin(String msg, boolean isC) {
        super(msg, isC);
    }

    @Override
    protected boolean encode() {
        if (getMessage() == null) return false;

        // Remove any punctuation
        String[] words = getMessage().split("\\W+");;

        // Input likely included blank string or just punctuation
        if (words.length == 0) {
            setMessage("", true);
            return true;
        }

        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(encodeSingle(word)).append(" ");
        }

        // Remove trailing space
        setMessage(sb.substring(0, sb.length() - 1), true);
        return true;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (getMessage() == null)
            throw new NullPointerException("Message is null.");

        // Split on any non-word characters
        String[] words = getMessage().split("\\W+");;

        // Input likely included blank string or just punctuation
        if (words.length == 0) {
            setMessage("", false);
            return true;
        }

        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(decodeSingle(word)).append(" ");
        }

        // Remove trailing space
        setMessage(sb.substring(0, sb.length() - 1), false);
        return true;
    }

    /**
     * Encodes a single word
     * @param word The non-encoded word
     * @return the encoded word
     */
    private String encodeSingle(String word) {
        if (word.length() == 0) return "";

        char first = word.charAt(0);

        if (isVowel(word.charAt(0))) {
            return word + "way";
        } else {
            // Word is only 1 long, just return word + ay
            if (word.length() == 1) return word + "ay";
            char second = word.charAt(1);

            // Check if first is upper to set the new first to upper too
            if (Character.isUpperCase(first)) {
                second = Character.toUpperCase(second);
            }

            first = Character.toLowerCase(first);

            // Check as we are using subtrings
            if (word.length() == 2) {
                return second + first + "ay";
            }

            return second + word.substring(2) + first + "ay";
        }
    }

    /**
     * Decodes a single word
     * @param word Word to decode
     * @return The decoded word
     * @throws DecodeErrorException if unable to decode the message
     */
    private String decodeSingle(String word) throws DecodeErrorException {
        if (word.length() < 2)
            throw new DecodeErrorException("Encoded message length is not valid.");

        String end = word.substring(word.length() - 3).toLowerCase();
        boolean isVowelEncoded = end.equals("way");
        if (!isVowelEncoded) {
            // If false, probably not pig latin
            if (!end.substring(1).equals("ay")) return word;
        }

        if (isVowelEncoded) {
            return word.substring(0, word.length() - 3);
        } else {
            // Get first and last wordds
            word = word.substring(0, word.length() - 2);
            char first = word.charAt(0);
            char last = word.charAt(word.length() - 1);

            // Check if first is cap and set new first to cap if so
            boolean isFirstCap = Character.isUpperCase(first);
            if (isFirstCap) last = Character.toUpperCase(last);

            // Return concat of new values
            return String.valueOf(last) + Character.toLowerCase(first) + word.substring(1, word.length() - 1);
        }
    }

    /**
     * Checks if a character is a vowel or not, returns false for non-alphabetic characters
     * @return True if the character is a vowel
     */
    private boolean isVowel(char c) {
        if (!Character.isAlphabetic(c)) return false;
        char[] vowels = new char[] { 'A', 'E', 'I', 'O', 'U' };

        // Go through every vowel, if equal, return true
        for (char v : vowels) {
            if (Character.toUpperCase(c) == v) return true;
        }

        return false;
    }
}
