package main;

import java.util.ArrayList;
import java.util.Random;

class Codebook {
    /** Maximum codewords any letter may have **/
    private static final int MAX_CODEWORDS = 10;

    /** Current max associated codeword that has been assigned **/
    private static int CURRENT_MAX_CODEWORD = 0;

    /** Associated codebook **/
    private final ArrayList<Integer> codebook = new ArrayList<>();

    public Codebook() { }

    /**
     * Gets a random codeword based on the codebook for a given character
     * @return a random int based on the codebook
     */
    public int getCodeword() {
        Random r = new Random();
        return codebook.get(r.nextInt(codebook.size()));
    }

    /**
     * Checks whether this codebook has a specified code within it
     * @param code Code to check
     * @return true if this codebook has the given code
     */
    public boolean isAssociated(int code) {
        for (Integer c : codebook) {
            if (c.equals(code)) return true;
        }

        return false;
    }

    /**
     * Adds a code to this codebook
     * @param code Code to add
     * @return true if was added
     */
    public boolean addCode(int code) {
        if (codebook.size() == MAX_CODEWORDS) return false;

        if (code > CURRENT_MAX_CODEWORD) CURRENT_MAX_CODEWORD = code;

        codebook.add(code);
        return true;
    }

    /**
     * Adds a code to this codebook based on the current max
     * @return true if was added
     */
    public boolean addCode() {
        if (codebook.size() == MAX_CODEWORDS) return false;
        return addCode(++CURRENT_MAX_CODEWORD);
    }

    /**
     * If this codebook has any codes in it
     * @return true if it has codes
     */
    public boolean hasCodes() {
        return codebook.size() != 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int code : codebook) {
            sb.append(code).append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }
}
