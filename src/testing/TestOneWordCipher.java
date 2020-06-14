package testing;

import main.CaesarCipher;
import main.OneWordCipher;

public class TestOneWordCipher extends Test {

    @Override
    public void run() {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }

    public static void testCase1() {
        String decoded = "HELLOWORLD";
        String encoded = "...././.-../.-../---/.--/---/.-./.-../-..";

        TestModule module = new TestModule(OneWordCipher.MorseCoder(decoded), "MORSE_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    public static void testCase2() {
        OneWordCipher cipher = new OneWordCipher("some message", new String[] { "incomplete", "codebook" });
        TestModule module = new TestModule(CaesarCipher.rot13(null), "ONEWORD_INCOMPLETE_CODEBOOK_TEST");
        module.assertEncodingFail();
    }

    public static void testCase3() {
        String decoded = "aimee";
        String encoded = "Alfa India Mike Echo Echo";

        TestModule module = new TestModule(OneWordCipher.PhoneticCoder(decoded), "MORSE_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    public static void testCase4() {
        String decoded = "goodbye world"; // :(
        String encoded = "gee oh oh dee bee why ee doubleu oh ar el dee";

        OneWordCipher cipher = new OneWordCipher(decoded, new String[] {
                "aye", "bee", "cee", "dee", "ee", "eff", "gee", "aych",
                "eye", "jay", "kay", "el", "em", "en", "oh", "pee",
                "cue", "ar", "es", "tee", "uy", "vee", "doubleu",
                "ex", "why", "zee"
        });
        TestModule module = new TestModule(cipher, "ONEWORD_ENCODING_TEST");

        module.assertEncodingSuccess(encoded);
    }
}
