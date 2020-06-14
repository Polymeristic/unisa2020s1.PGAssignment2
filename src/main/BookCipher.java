package main;

import javax.swing.plaf.BorderUIResource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BookCipher extends Transposition {

    /** Max words we can read in one book **/
    private static final int MAX_WORDS = 250;

    /** Codebook used for translation **/
    private Codebook[] codebook;

    public static void main(String[] a) {
        String msg = "He loved Big Brother.";
        BookCipher BC = new BookCipher(msg,"1984ch1.txt");
        BC.encodeMessage();
        System.out.println(BC);
        BC.decodeMessage();
        System.out.println(BC);
    }

    public BookCipher(String msg, String bookPath) {
        this(msg, false, bookPath);
    }

    public BookCipher(String msg, boolean isC, String bookPath) {
        super(msg, isC);

        String book = null;

        try {
            book = readBook(bookPath);
        } catch (FileNotFoundException ignored) { }

        generateCodeBooks(book);
    }

    @Override
    protected boolean encode() {
        if (codebook == null || getMessage() == null) return false;
        if (getMessage().equals("")) {
            setMessage("", true);
            return true;
        }

        StringBuilder sb = new StringBuilder();

        // Iterate over all characters
        for (char c : getMessage().toCharArray()) {
            if (!Character.isAlphabetic(c)) continue;

            // Get the relative codebook and get a codeword
            int absC = getAbsChar(Character.toUpperCase(c));
            sb.append(codebook[absC].getCodeword()).append(" ");
        }

        setMessage(sb.substring(0, sb.length() - 1), true);
        return true;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (codebook == null || getMessage() == null)
            throw new DecodeErrorException("Codebook or message cannot be false.");

        if (getMessage().equals("")) {
            setMessage("", true);
            return true;
        }

        StringBuilder sb = new StringBuilder();

        // Get all the numbers in the output
        String[] numbers = getWords();

        // Iterate over all characters
        for (String stringNumber : numbers) {
            int number = Integer.parseInt(stringNumber);
            boolean hasMatch = false;

            // Go through all the codebooks and see if there is a match
            for (int i = 0; i < ALPHABET_LENGTH; i++) {
                Codebook cb = codebook[i];

                // If it is associated with that codebook, add the respective character to the string builder
                if (cb.isAssociated(number)) {
                    sb.append((char) getRelChar(i));
                    hasMatch = true;
                    break;
                }
            }

            // If no match append a ? character
            if (!hasMatch) sb.append('?');
        }

        setMessage(sb.toString(), false);
        return true;
    }

    /**
     * Generates codebooks for all characters from a given string code
     * @param book Book to generate from
     */
    private void generateCodeBooks(String book) {
        if (book == null) return;

        String[] words = book.split("\\W+");
        Codebook[] codebook = new Codebook[ALPHABET_LENGTH];

        int i = 0;

        // Go through all words and add them to the codebook
        for (String word : words) {
            char first = word.charAt(0);

            if (!Character.isAlphabetic(first)) continue;
            i++;

            if (i > MAX_WORDS) break;

            // Convert first character
            first = Character.toUpperCase(first);
            int pos = getAbsChar(first);

            // Add to the related codebook
            if (codebook[pos] == null) codebook[pos] = new Codebook();
            codebook[pos].addCode(i);
        }

        // Go back over codebook and see if we're missing codes, add if so
        for (int j = 0; j < ALPHABET_LENGTH; j++) {
            if (codebook[j] == null) {
                codebook[j] = new Codebook();
                codebook[j].addCode();
            }
        }

        this.codebook = codebook;
    }

    /**
     * Reads a book from a given file path
     * @param path Path to read from
     * @return The book as a string
     */
    private String readBook(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        StringBuilder data = new StringBuilder();

        // Add all lines into builder, append space in to ensure word separation
        while (sc.hasNextLine()) data.append(sc.nextLine()).append(" ");

        sc.close();
        return data.toString();
    }

    /**
     * Converts the codebook to a string
     * @return String representation of the codebook
     */
    private String toStringCodebook() {
        if (codebook == null) return "Empty";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            sb.append((char) getRelChar(i)).append(" ").append(codebook[i]).append("\n");
        }

        return sb.toString();
    }
}
