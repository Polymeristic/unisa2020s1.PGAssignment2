package main;

import java.util.Arrays;

public class TripleRepeat extends ErrorDetection {

    public TripleRepeat(String msg) {
        super(msg);
    }

    public TripleRepeat(String msg, boolean isC) {
        super(msg, isC);
    }

    @Override
    protected boolean encode() {
        if (getMessage() == null) return false;

        if (getMessage().length() == 0) {
            setMessage("", true);
            return true;
        }

        StringBuilder sb = new StringBuilder();

        // Go through every character and triple it
        for (char c : getMessage().toCharArray()) {
            sb.append(String.valueOf(c).repeat(3));
        }

        setMessage(sb.toString(), true);
        return true;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        if (getMessage() == null) return false;

        String msg = getMessage();

        if (msg.length() == 0) {
            setMessage("", true);
            return true;
        }

        if (msg.length() % 3 != 0) {
            throw new DecodeErrorException("Message length is not valid.");
        }

        StringBuilder sb = new StringBuilder();

        // Go through every third set of three characters
        for (int i = 0; i < msg.length(); i += 3) {
            char[] seg = msg.substring(i, i + 3).toCharArray();

            // Check for the most equality between them
            if (seg[0] == seg[1]) {
                sb.append(seg[0]);
            } else if (seg[1] == seg[2]) {
                sb.append(seg[1]);
            } else if (seg[2] == seg[0]) {
                sb.append(seg[2]);
            } else {
                // If no equality, perform error reporting as per specs
                if (handleExcept) sb.append('?');
                else throw new DecodeErrorException("Segment cannot be decoded : " + Arrays.toString(seg));
            }
        }

        setMessage(sb.toString(), false);
        return true;
    }
}
