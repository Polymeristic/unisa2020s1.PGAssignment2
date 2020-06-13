package main;

/**
 * This class illustrates how you might write a text coding class by extending
 * TextCoder directly.
 * Note that:
 * - the constructor calls the superclass constructor via super() for setup
 *   appropriate to the superclass.
 * - methods encode() and decode(), abstract in TextCoder, have concrete 
 *   definitions here.
 * - encode and decode both get the message, transform it, and re-set it; 
 *   also setting the new state of the message (i.e. coded or not).
 *   Both return true on success.
 *   
 * You could do the assignment by writing every class in this manner, 
 * extending TextCoder directly.  However this would be poor design. 
 * Some text coders share common functionality.  Use a class hierarchy so 
 * that common functionality is inherited by all text coders that use it,
 * variations to functionality are specified at the lower classes (enabling
 * polymorphism), and functionality specific to a class is specified in that 
 * class.
 * 
 * @author sjs
 *
 */
public class VowelSwapper extends TextCoder {

	/**
	 * construct VowelSwapper object with initial message in non-coded state.
	 * @param msg  original non-coded message.
	 */
	public VowelSwapper(String msg) {
		super(msg);
	}

	/**
	 * change vowels to the next vowel in the alphabet, or to the previous.
	 * If supplied character is not a vowel (A,E,I,O,U) then it is unchanged.
	 * @param ch  a character
	 * @param forward  encode (next vowel) if true, decode (previous vowel) if false.
	 * @return
	 */
	public static char swapVowel(char ch, boolean forward) {
		// determine if ch was uppercase so we can change back later.
		boolean wasUpper = Character.isUpperCase(ch);
		char out = ch;
		switch(Character.toLowerCase(ch)) {
		case 'a':
			out = forward?'e':'u';
			break;
		case 'e':
			out = forward?'i':'a';
			break;
		case 'i':
			out = forward?'o':'e';
			break;
		case 'o':
			out = forward?'u':'i';
			break;
		case 'u':
			out = forward?'a':'o';
		}
		// this preserves original case:
		if (wasUpper) out = Character.toUpperCase(out);
		return out;
	}
	

	@Override
	/**
	 * change all vowels in the message in the forward direction,
	 * i.e. A->E, E->I, I->O, O->U, U->A
	 * @return 	true on success
	 */
 	protected boolean encode() {
		String output = "";
		// process each char in turn, building up transformed String.
		for (int i=0; i<getMessage().length(); i++) {
			char ch = getMessage().charAt(i);
			ch = swapVowel(ch, true);
			output += ch;
		}
		setMessage(output, true);
		return true;
	}

	@Override
	/**
	 * change all vowels in the message in the reverse direction,
	 * i.e. A->U, E->A, I->E, O->I, U->O
	 * @return 	true on success
	 */	
	protected boolean decode() throws DecodeErrorException {
		String output = "";
		// process each char in turn, building up transformed String.
		for (int i=0; i<getMessage().length(); i++) {
			char ch = getMessage().charAt(i);
			ch = swapVowel(ch, false);
			output += ch;
		}
		setMessage(output, false);
		return true;
	}

	// // // //
	
	/**
	 * Test driver for VowelSwapper class.
	 * @param args
	 */

}
