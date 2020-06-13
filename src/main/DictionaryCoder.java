package main;

public class DictionaryCoder extends Transposition {

    public static void main(String[] a) throws DecodeErrorException {
        DictionaryCoder d = new DictionaryCoder("Meet me next Tuesday at the station.", new String[][] {
                { "Meet", "Moi" },
                { "Tuesday", "Mardi" },
                { "station", "gare" }
        });

        d.encode();

        System.out.println(d.getMessage());

        d.decode();

        System.out.println(d.getMessage());
    }

    /** Codebook to translate words **/
    private String[][] codebook;

    /**
     * Create a new dictionary coder
     * @param msg Message to use
     * @param codebook Codebook for dictionary coding
     */
    public DictionaryCoder(String msg, String[][] codebook) { super(msg); this.codebook = codebook; }

    /**
     * Create a new dictionary coder
     * @param msg Message to use
     * @param isC If the message is coded
     * @param codebook Codebook for dictionary coding
     */
    public DictionaryCoder(String msg, boolean isC, String[][] codebook) { super(msg, isC); this.codebook = codebook; }

    @Override
    protected boolean encode() {
        if (getMessage() == null || codebook == null)
            return false;

        String msg = getMessage();

        // Just replace all the keys with the required code
        for (String[] code : codebook) {
            if (code.length != 2)
                return false;

            msg = replaceWord(msg, code[0], code[1]);
        }

        setMessage(msg, true);
        return false;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (getMessage() == null || codebook == null)
            throw new DecodeErrorException("Neither message or codebook can be null.");

        String msg = getMessage();

        // Just replace all the keys with the required code, but using opposite indices
        for (String[] code : codebook) {
            if (code.length != 2)
                throw new DecodeErrorException("Word dictionary is missing a key value pair.");

            msg = replaceWord(msg, code[1], code[0]);
        }

        setMessage(msg, false);
        return false;
    }
}
