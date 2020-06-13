package main;

public class Interleaver extends BlockScramble {

    public static void main(String[] a) {
        Interleaver i = new Interleaver("Welcome to Jamaica. I hope you have an enjoyable stay.", 3, 4);

        i.encode();

        System.out.println(i.getMessage());

        try {
            i.decode();
        } catch (DecodeErrorException e) {
            e.printStackTrace();
        }

        System.out.println(i.getMessage());
    }

    /**
     * Create BlockScramble and set the message in an uncoded state.
     * If there is no message, use the empty String "".
     * @param msg the message
     */
    public Interleaver(String msg, int blockHeight, int blockWidth) {
        super(msg);
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
    }

    /**
     * Create BlockScramble and set both message AND its state.
     * No check is performed to see if the message is coded or not;
     * It is the responsibility of the caller.
     * @param msg the message
     * @param isC true if message is coded, false if not (plain text).
     */
    public Interleaver(String msg, boolean isC, int blockHeight, int blockWidth){
        super(msg, isC);
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
    }

    /**
     * Blocksize in the X direction
     */
    private int blockWidth = 4;

    /**
     * Blocksize in the Y direction
     */
    private int blockHeight = 3;


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

    /**
     * Encodes a single block of text
     * @param block Block of text
     * @return The encoded block of text
     */
    @Override
    protected String encodeBlock(String block) {
        if (block.length() != getBlockSize())
            block = padding(block, getBlockSize());

        StringBuilder out = new StringBuilder();

        // Iterate over the block in a pattern similar to
        // 012301230123
        // Based on a 3x4 block size
        for (int h = 0; h <= blockHeight; h++) {
            for (int w = h; w < getBlockSize(); w += blockWidth) {
                out.append(block.charAt(w));
            }
        }

        return out.toString();
    }

    /**
     * Decodes a single block of text
     * @param block Block of text
     * @return The decoded block of text
     */
    @Override
    protected String decodeBlock(String block) {
        if (block.length() != getBlockSize())
            block = padding(block, getBlockSize());

        StringBuilder out = new StringBuilder();

        // Switch block iteration lengths and decode
        for (int h = 0; h < blockWidth - 1; h++) {
            for (int w = h; w < getBlockSize(); w += blockHeight) {
                out.append(block.charAt(w));
            }
        }

        return out.toString();
    }

    /**
     * Gets the block size of the cipher
     * @return Block size
     */
    private int getBlockSize() {
        return blockWidth * blockHeight;
    }
}
