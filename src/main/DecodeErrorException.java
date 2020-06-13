package main;

/**
 * Exception that occurs when a decode operation fails irrecoverably
 */
public class DecodeErrorException extends Exception {
    /**
     * Create a DecodeErrorException with no details
     */
    public DecodeErrorException() { super(); }

    /**
     * Create a DecodeErrorException
     * @param message Message to use
     */
    public DecodeErrorException(String message) { super(message); }

    /**
     * Create a DecodeErrorException
     * @param message Message to use
     * @param cause Cause of the exception
     */
    public DecodeErrorException(String message, Throwable cause) { super(message, cause); }
}
