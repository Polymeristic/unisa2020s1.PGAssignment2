package main;

public abstract class TextCoder {

	// --- CONSTANT VARIABLES ---

	public static final String STUDENT_ID = "metam002";

	/** Length of the Latin alphabet */
	public static final int ALPHABET_LENGTH = 26;

	// --- INSTANCE VARIABLES ---

	/** the message being held and managed by this TextCoder */
	private String message;

	/** the state of the message: true if coded, false if not coded */
	private boolean coded;

	// --- GETTERS AND SETTERS ---

	/**
	 * current state of the message: is it coded or not?
	 * @return true if coded, false if not
	 */
	public boolean isCoded(){ return coded; }

	/**
	 * Set the message, specifying whether or not is is coded.
	 * No check is done to verify that the message is actually coded;
	 * it is the responsibility of the caller.
	 * @param msg the message
	 * @param isC true if message is coded, false if not (plaintext).
	 */
	public void setMessage(String msg, boolean isC){
		message = msg;
		coded = isC;
	}

	/**
	 * retrieve the message in its current state -
	 * no decoding is performed.
	 * @return the message.
	 */
	public String getMessage(){
		return message;
	}

	// --- CONSTRUCTORS ---

	/**
	 * Create TextCoder and set the message in an uncoded state.
	 * If there is no message, use the empty String "".
	 * @param msg the message
	 */
	public TextCoder(String msg){
		setMessage(msg, false);
	}

	/**
	 * Create TextCoder and set both message AND its state.
	 * No check is performed to see if the message is coded or not;
	 * It is the responsibility of the caller.
	 * @param msg the message
	 * @param isC true if message is coded, false if not (plain text).
	 */
	public TextCoder(String msg, boolean isC){
		setMessage(msg, isC);
	}

	// --- PUBLIC INTERFACE ---

	/**
	 * Encode message.
	 * This operation will fail if the message is already encoded.
	 * It may fail for other coder-specific reasons.
	 * The instance variable coded is set to true if the encode succeeds.
	 * @return true on success, false on failure
	 */
	public boolean encodeMessage(){
		if (coded) return false;
		boolean success = encode();
		//if (success) coded = true;  // this should be done when encode()
		// sets the message; no need to do here.
		return success;
	}

	/**
	 * Decode message.
	 * This operation will fail if the message is already plain text (not coded)
	 * and may fail for other coder-specific reasons.
	 * The instance variable coded is set to false if the decode succeeds.
	 *
	 * EXCEPTION TASK:
	 * If you attempt the Exception task, you must modify this routine to catch
	 * a DecodeErrorException, set the message to "*DECODE ERROR*" and return false.
	 * Otherwise leave this method as-is.
	 *
	 * @return true if decode operation succeeded.
	 */
	public boolean decodeMessage() {
		if (!coded) return false;

		boolean OK = false;

		// If exception caught, change message
		try {
			OK = decode();
		} catch (DecodeErrorException e) {
			message = "*DECODE*ERROR*";
		}

		return OK;
	}

	/**
	 * Encode the message then write it to specified text file.
	 * @param fname filename
	 * @return true on success, false on failure.
	 */
	public boolean encodeAndWriteToFile(String fname) {
		System.out.println("DELETE THIS LINE AND WRITE THIS METHOD PROPERLY");
		return false;
	}

	// -- PRIVATE IMPLEMENTATION METHODS ---

	/**
	 * Perform the encoding of message.
	 * This method must apply the encoding method to the text in message,
	 * and replace that text with the output of the encoder.
	 * @return true on success, false on failure
	 */
	abstract protected boolean encode();

	/**
	 * Perform decoding of coded message.
	 * This method must apply the decoding method to the text in message,
	 * and replace that text with the output of the decoder.
	 * @return true on success, false on failure.
	 * @throws DecodeErrorException
	 */
	abstract protected boolean decode() throws DecodeErrorException;;

	/**
	 * Converts a letter to be numerically absolute (i.e. 'A' (65) -> 0, 'C' (67) -> 1)
	 * @param c a character in relative space
	 * @return the absolute value of the character
	 */
	protected int getAbsChar(char c) {
		return c - 65;
	}

	/**
	 * Converts a letter to be numerically relative to A (i.e. 0 -> 'A' (65), 2 -> 'C' (67))
	 * @param c a character in absolute space
	 * @return the relative value of the character
	 */
	protected int getRelChar(char c) {
		return c + 65;
	}

	/**
	 * Converts a letter to be numerically relative to A (ie, 0 -> 'A' (65), 2 -> 'C' (67))
	 * @param i a number representing a character in absolute space
	 * @return the relative value of the character
	 */
	protected int getRelChar(int i) {
		return i + 65;
	}

	/**
	 * Splits a sentence into words
	 * @return Array of words
	 */
	protected String[] getWords() { return getWords(getMessage()); }

	/**
	 * Splits a sentence into words
	 * @param text Text to get the words from
	 * @return Array of words
	 */
	protected String[] getWords(String text) { return text.split("\\s+"); }

	/**
	 * Splits a sentence into words based on a delimiter
	 * @param delim Delimiter to use
	 * @return Array of words
	 */
	protected String[] getWords(char delim) {
		return getMessage().split("\\" + delim + "+");
	}

	// --- PUBLIC UTILITY METHODS ---

	/**
	 * two TextCoders are equal if the hold the same message in the same state.
	 */
	public boolean equals(Object other){
		if (other==null) return false;
		if (!(other instanceof TextCoder)) return false;
		TextCoder TC = (TextCoder) other;
		return ( (this.coded==TC.coded) && this.message.equals(TC.message) );
	}

	/**
	 * TextCoder toString:
	 * if message is coded it is returned enclosed in curly braces {}
	 * otherwise message is returned as-is.
	 */
	public String toString(){
		String out = message;
		if (coded) out = "{"+message+"}";
		return out;
	}

	// PLEASE IGNORE THE FOLLOWING
	// This is not part of the project; it is included only so that your code will
	// still compile and run with the marking code even if you don't attempt the
	// Exception task.
	public void setHandleExcepts(boolean OK) {
		System.out.println("You should never see this message!");
	}
}
