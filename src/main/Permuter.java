package main;

import java.util.Arrays;

public class Permuter extends BlockScramble {

    public static void main(String[] a) {
        String msg;
        msg = "What’s it going to be then, eh?";

        int[] perm = {1, 7, 0, 5, 3, 6, 4, 2}; //{1,0,5,3,4,2};
        Permuter PM = new Permuter(msg,perm);

        System.out.println(PM);
        PM.encodeMessage();
        System.out.println(PM);
        PM.decodeMessage();
        System.out.println(PM);
    }

    private int[] permutation;

    public Permuter(String msg, int[] permutation) {
        super(msg);
        this.permutation = permutation;
    }

    public Permuter(String msg, boolean isC, int[] permutation) {
        super(msg, isC);
        this.permutation = permutation;
    }


    @Override
    protected boolean encode() {
        if (getMessage() == null)
            throw new IllegalArgumentException("Message cannot be null.");

        if (isCoded())
            return false;

        String msg = getMessage();
        StringBuilder out = new StringBuilder();

        msg = padding(msg, (int) Math.ceil((float) msg.length() / getBlockSize()) * getBlockSize());

        for (int i = 0; i < msg.length(); i += getBlockSize()) {
            out.append(encodeBlock(msg.substring(i, i + getBlockSize())));
        }

        setMessage(out.toString(), true);
        return true;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (getMessage() == null)
            throw new IllegalArgumentException("Message cannot be null.");

        if (!isCoded())
            return false;

        String msg = getMessage();

        // Check if message can evenly fit in the block size
        if (msg.length() % getBlockSize() != 0)
            throw new DecodeErrorException("Message length is not a multiple of the block size.");

        StringBuilder out = new StringBuilder();

        // Decode all sub-blocks and append
        for (int i = 0; i < msg.length(); i += getBlockSize()) {
            out.append(decodeBlock(msg.substring(i, i + getBlockSize())));
        }

        // Set message and return true
        setMessage(out.toString().trim(), false);
        return true;
    }

    @Override
    protected String encodeBlock(String block) {
        if (block == null) return "";
        if (block.length() != getBlockSize()) return null;

        StringBuilder sb = new StringBuilder();

        for (int i : permutation) {
            sb.append(block.charAt(i));
        }

        return sb.toString();
    }

    @Override
    protected String decodeBlock(String block) {
        if (block == null) return "";
        if (block.length() != getBlockSize()) return null;

        StringBuilder sb = new StringBuilder();

        int[] rev = reverse(permutation);

        for (int i : rev) {
            sb.append(block.charAt(i));
        }

        return sb.toString();
    }

    @Override
    public int getBlockSize() { return permutation.length; }

    /**
     * Reverses a permutation array
     * @param array Array to reverse
     * @return reversed array
     */
    private int[] reverse(int[] array) {
        int[] rev = new int[array.length];

        for (int i = 0; i < array.length / 2; i++) {
            int pos = array.length - i - 1;

            rev[pos] = array[i];
            rev[i] = array[pos];
        }

        return rev;
    }
}
