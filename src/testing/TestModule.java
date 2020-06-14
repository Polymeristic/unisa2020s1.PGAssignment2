package testing;

import main.DecodeErrorException;
import main.TextCoder;

import javax.swing.plaf.TreeUI;

public class TestModule {
    /** Coder used in tests **/
    private final TextCoder coder;

    /** Name of the test **/
    private final String name;

    /**
     * Create a new test module with the specified coder and test name
     * @param coder Coder to use
     * @param name Test module name
     */
    public TestModule(TextCoder coder, String name) {
        this.coder = coder;
        this.name = name;
    }

    /**
     * Will succeed if the encoded text equals the expected result, will fail if not or if the encoding fails
     * @param expected Expected output
     */
    public boolean assertEncodingSuccess(String expected) {
        if (!coder.isCoded()) {
            try{
                if (!coder.encodeMessage()) return showFail( "Encoding failed.");
            } catch (Exception e) {
                return showFail(e.getMessage());
            }
        }

        if (coder.getMessage().equals(expected)) {
            return showSuccess();
        }

        return showFail( expected, coder.getMessage());
    }

    /**
     * Will succeed if the decoded text equals the expected result, will fail if not or if the decoding fails
     * @param expected Expected output
     */
    public boolean assertDecodingSuccess(String expected) {
        if (coder.isCoded()) {
            try {
                if (!coder.decodeMessage()) return showFail( "Encoding failed.");
            } catch (Exception e) {
                return showFail(e.getMessage());
            }
        }

        if (coder.getMessage().equals(expected)) {
            return showSuccess();
        }

        return showFail( expected, coder.getMessage());
    }

    /**
     * Will succeed if we fail to encode the text, will fail if it succeeds=
     */
    public boolean assertEncodingFail() {
        try {
            if (coder.encodeMessage()) return showFail( "Encoding succeeded where it should have failed.");
        } catch (Exception ignored) { }
        return showSuccess();
    }

    /**
     * Will succeed if we fail to decode the text, will fail if it succeeds=
     */
    public boolean assertDecodingFail() {
        try {
            if (coder.decodeMessage()) return showFail( "Encoding failed.");
        } catch (Exception ignored) { }
        return showSuccess();
    }

    /**
     * Gets the coder used in this test
     * @return the coder
     */
    public TextCoder getCoder() {
        return coder;
    }

    /**
     * Show a test fail with the expected and actual result
     * @param expected Expected result
     * @param actual Actual result
     */
    private boolean showFail(String expected, String actual) {
        System.out.println("TEST [" + name + "] FAILED! Expected:\n" + expected + "\nActual:\n" + actual);
        return false;
    }

    /**
     * Show a test fail with a message
     * @param message Message
     */
    private boolean showFail(String message) {
        System.out.println("TEST [" + name + "] FAILED! " + message);
        return false;
    }

    /**
     * Show a test fail with a message
     */
    private boolean showFail() {
        System.out.println("TEST [" + name + "] FAILED!");
        return false;
    }

    /**
     * Show a test fail with a message
     */
    private boolean showSuccess() {
        System.out.println("TEST [" + name + "] SUCCEEDED!");
        return true;
    }
}
