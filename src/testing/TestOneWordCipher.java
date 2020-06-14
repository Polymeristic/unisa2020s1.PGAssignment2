package testing;

import main.CaesarCipher;
import main.OneWordCipher;

/**
 * This test classes uses the TestModule class to run all of it's tests. As such all tests will display:
 *      TEST [{test name}] SUCCEEDED!
 *
 * If they have passed their tests, otherwise it will say:
 *
 *      TEST [{test name}] FAILED! {reason}
 */

public class TestOneWordCipher extends Test {

    /**
     * Run all testers
     */
    @Override
    public void run() {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }

    /**
     * Test to see if the encoding process with morse succeeds or not
     * If successful, the encode will equal the string 'encoded'
     */
    public static void testCase1() {
        String decoded = "HELLOWORLD";
        String encoded = "...././.-../.-../---/.--/---/.-./.-../-..";

        TestModule module = new TestModule(OneWordCipher.MorseCoder(decoded), "MORSE_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    /**
     * Test to see if when the codebook if not the correct length (length of the alphabet) it will fail an encode
     */
    public static void testCase2() {
        OneWordCipher cipher = new OneWordCipher("some message", new String[] { "incomplete", "codebook" });
        TestModule module = new TestModule(CaesarCipher.rot13(null), "ONEWORD_INCOMPLETE_CODEBOOK_TEST");
        module.assertEncodingFail();
    }

    /**
     * Test to see if the encoding process with phonetic succeeds or not
     * If successful, the encode will equal the string 'encoded'
     */
    public static void testCase3() {
        String decoded = "aimee";
        String encoded = "Alfa India Mike Echo Echo";

        TestModule module = new TestModule(OneWordCipher.PhoneticCoder(decoded), "MORSE_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    /**
     * Test to see if the decoding process succeeds or not
     * If successful, the encode will equal the string 'decoded'
     */
    public static void testCase4() {
        String decoded = "GOODBYEWORLD"; // :(
        String encoded = "gee oh oh dee bee why ee doubleu oh ar el dee";

        OneWordCipher cipher = new OneWordCipher(encoded, true, new String[] {
                "aye", "bee", "cee", "dee", "ee", "eff", "gee", "aych",
                "eye", "jay", "kay", "el", "em", "en", "oh", "pee",
                "cue", "ar", "es", "tee", "uy", "vee", "doubleu",
                "ex", "why", "zee"
        });

        TestModule module = new TestModule(cipher, "ONEWORD_ENCODING_TEST");

        module.assertDecodingSuccess(decoded);
    }
}
