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
        return false;
    }

    @Override
    protected boolean decode() throws DecodeErrorException {
        return false;
    }
}
