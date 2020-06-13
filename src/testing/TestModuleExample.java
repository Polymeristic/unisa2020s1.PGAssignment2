package testing;

import main.VowelSwapper;

/**
 * This is an example of how you might write test modules in Java 
 * without recourse to JUnit.
 * 
 * Each test module should be documented with what is being tested,
 * how it is being tested, what the output should be for the test input.
 * 
 * The first method shows how you might write a method returning boolean
 * to signify success or failure.
 * 
 * The second method shows how you can use assert to trap unexpected values
 * and catch the Exception it generates in your calling code.
 * 
 * @author sjs
 *
 */
public class TestModuleExample {

	/**
	 * Test the Vowel Swapper class.
	 * 1. create a String entirely of vowels and encode it.
	 *    Every element of the coded message should be different.
	 * 2. create a String entirely of consonants.  
	 *    Every element should be unchanged by encoder.
	 * 3. Define a whole lot of Strings, plus what they should encode as,
	 *    and check that they do (check that Strings are .equal() )
	 * @return
	 */
	static boolean testVowelSwapper() {
		VowelSwapper VS = new VowelSwapper("");
		
		boolean OK = true;
		
		// 1.
		String msg = "aeiouAEIOU";
		VS.setMessage(msg, false);
		boolean success = VS.encodeMessage();
		// OK &= success;  // check success was true if we want to
		String coded = VS.getMessage();
		char[] arr1 = msg.toCharArray();
		char[] arr2 = coded.toCharArray();
		for(int i=0; i<msg.length(); i++)
			OK &= (arr1[i]!=arr2[i]);
		//System.out.println("1, " + OK);
		
		// 2. 
		msg = "bCdFgHjKlMnPqRsTvWxYz";
		VS.setMessage(msg, false);
		VS.encodeMessage();
		OK &= VS.isCoded();  // make sure it thinks it has processed the text.
		OK &= msg.equals(VS.getMessage());
		//System.out.println("2, " + OK);
		
		//3.
		// first define some plaintext/coded pairs
		String[] checkPairs = {
				"rhythm/rhythm",   					// no vowels
				"fastidious/festodouas", 			// lotsa vowels
				"Harry Potter/Herry Puttir", 		// some capitals and spaces
				"Golly! Count: 3/Gully! Cuant: 3"	// and some punctuation
		};
		for(String ess: checkPairs) {
			String[] words = ess.split("/");
			msg = words[0];
			coded = words[1];
			VS.setMessage(msg, false);
			VS.encodeMessage();
			OK &= VS.getMessage().equals(coded);
			VS.decodeMessage();
			OK &= VS.getMessage().equals(msg);
		}
		//System.out.println("3, " + OK);

		return OK;
	}
	
	
	/**
	 * Test the vowel swapper module.
	 * same as final test from above - but using assert to do the dirty work.
	 * To use assert you must enable assertions in eclipse:
	 *   Run -> Run Configurations -> Arguments tab 
	 *   enter "-ea"  (without the quotes) into the VM arguments box
	 *   Apply.
	 */
	static void testVSwithAssert() {  // note returning void, not boolean
		String[] checkPairs = {
				"rhythm/rhythm",   					// no vowels
				"fastidious/festodouas", 			// lotsa vowels
				"Harry Potter/Herry Puttir", 		// some capitals and spaces
				"Golly! Count: 3/Gully! Cuant: 3"	// and some punctuation
		};
		VowelSwapper VS = new VowelSwapper("");
		for(String ess: checkPairs) {
			String[] words = ess.split("/");
			String msg = words[0];
			String coded = words[1];
			VS.setMessage(msg, false);
			VS.encodeMessage();
			assert(VS.getMessage().equals(coded));
			VS.decodeMessage();
			assert(VS.getMessage().equals(msg));
			
			//assert(false);
		}
	}
	
	
	public static void main(String[] args) {
		
		// test method 1:  use a boolean method
		System.out.println("test Vowel Swapper.");
		boolean OK = testVowelSwapper();
		System.out.println("vowel swapper " + (OK?"PASSED":"FAILED") + "\n");
		
		// test method 2:  use assert() and catch the AssertionError.
		try{
			testVSwithAssert();
			System.out.println("test with assert PASSED.");
		} catch(AssertionError e) {
			System.out.println("test with assert FAILED.");
		}
	}
	

}
