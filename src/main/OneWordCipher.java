package main;

public class OneWordCipher extends Transposition {

    public static void main(String[] a) throws DecodeErrorException {
        OneWordCipher c = OneWordCipher.MorseCoder(".../---/...");

        c.decode();

        System.out.println(c.getMessage());

        c.encode();

        System.out.println(c.getMessage());
     }

    /** Codebook used for translation **/
    private String[] codebook;

    /** Delimiter used to seperate words **/
    private char delim = ' ';

    public OneWordCipher(String msg, String[] codebook) {
        super(msg);
        this.codebook = codebook;
    }

    public OneWordCipher(String msg, boolean isC, String[] codebook) {
        super(msg, isC);
        this.codebook = codebook;
    }

    /**
     * Create TextCoder and set both message AND its state.
     * No check is performed to see if the message is coded or not;
     * It is the responsibility of the caller.
     * @param msg the message
     * @param codebook codebook used for message translation
     * @param delim delimiter used to separate words
     */
    public OneWordCipher(String msg, String[] codebook, char delim) {
        super(msg);
        this.codebook = codebook;
        this.delim = delim;
    }

    /**
     * Create TextCoder and set both message AND its state.
     * No check is performed to see if the message is coded or not;
     * It is the responsibility of the caller.
     * @param msg the message
     * @param codebook codebook used for message translation
     * @param isC true if message is coded, false if not (plain text).
     * @param delim delimiter used to separate words
     */
    public OneWordCipher(String msg, boolean isC, String[] codebook, char delim) {
        super(msg, isC);
        this.codebook = codebook;
        this.delim = delim;
    }

    /**
     * Create TextCoder and set both message AND its state.
     * No check is performed to see if the message is coded or not;
     * It is the responsibility of the caller.
     * @param msg the message
     * @param codebook codebook used for message translation
     * @param delim delimiter used to separate words
     */
    public OneWordCipher(String msg, String codebook, char delim) {
        super(msg);
        this.codebook = getWords(codebook);
        this.delim = delim;
    }

    /**
     * Create TextCoder and set both message AND its state.
     * No check is performed to see if the message is coded or not;
     * It is the responsibility of the caller.
     * @param msg the message
     * @param codebook codebook used for message translation
     * @param isC true if message is coded, false if not (plain text).
     * @param delim delimiter used to separate words
     */
    public OneWordCipher(String msg, boolean isC, String codebook, char delim) {
        super(msg, isC);
        this.codebook = getWords(codebook);
        this.delim = delim;
    }

    @Override
    protected boolean encode() {
        if (getMessage() == null || codebook == null || codebook.length != ALPHABET_LENGTH)
            return false;

        if (getMessage().length() == 0) {
            setMessage("", true);
            return true;
        }

        StringBuilder sb = new StringBuilder();

        // Go through each character and encode them individually
        for (char c : getMessage().toUpperCase().toCharArray()) {
            if (!Character.isAlphabetic(c)) continue;
            int cNum = getAbsChar(c);

            // Append the related char number to the final output IF character is less than
            // length of the codebook, if not, ignore
            if (cNum >= 0 && cNum < ALPHABET_LENGTH)
                sb.append(codebook[cNum]).append(delim);
        }

        // In case no words were letters
        if (getMessage().length() == 0) return true;

        // Remove last char from message as it would have an extra delim
        setMessage(sb.substring(0, sb.length() - 1), true);
        return true;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (getMessage() == null)
            throw new DecodeErrorException("Unable to decode if message is null.");

        if (codebook == null || codebook.length != ALPHABET_LENGTH)
            throw new DecodeErrorException("Codebook cannot be null, and has to be " + ALPHABET_LENGTH + " entries long.");

        if (getMessage().length() == 0) {
            setMessage("", false);
            return true;
        }

        StringBuilder sb = new StringBuilder();

        // Split words by delim and iterate
        for (String word : getWords(delim)) {
            boolean matched = false;
            word = word.toUpperCase();

            // Go through codebook and try and find a match
            for (int i = 0; i < codebook.length; i++) {
                if (codebook[i] == null) continue;

                // If match append to output
                if (codebook[i].toUpperCase().equals(word)) {
                    sb.append((char) getRelChar(i));
                    matched = true;
                    break;
                }
            }

            if (!matched) sb.append('?');
        }

        setMessage(sb.toString(), false);
        return true;
    }

    /**
     * Create a new phonetic alphabet coder with a given message
     * @param message Message
     * @return A phonetic alphabet coder object
     */
    public static OneWordCipher PhoneticCoder(String message) {
        return new OneWordCipher(message, new String[] {
               "Alfa", "Bravo", "Charlie", "Delta",
               "Echo", "Foxtrot", "Golf", "Hotel",
               "India", "Juliet", "Kilo", "Lemur",
               "Mike", "November", "Oscar", "Papa",
               "Quebec", "Romeo", "Sierra", "Tango",
               "Uniform", "Victor", "Whiskey", "X-Ray",
               "Yankee", "Zulu"
        }, ' ');
    }

    public static OneWordCipher MorseCoder(String message) {
        return new OneWordCipher(message, new String[] {
                ".-", "-...", "-.-.", "-..",
                ".", "..-.", "--.", "....",
                "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".---.",
                "--.-", ".-.", "...", "-",
                "..-", "...-", ".--", "-..-",
                "-.--", "--.."
        }, '/');
    }
}
