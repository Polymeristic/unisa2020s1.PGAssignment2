package main;

public abstract class ErrorDetection extends TextCoder {
    protected boolean handleExcept = false;

    public ErrorDetection(String msg) {
        super(msg);
    }

    public ErrorDetection(String msg, boolean isC) {
        super(msg, isC);
    }

    /** Sets the handleExcept variable **/
    public void setHandleExcept(boolean OK) { handleExcept = OK; }

}
