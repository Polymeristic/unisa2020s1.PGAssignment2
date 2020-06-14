package testing;

import main.CaesarCipher;

/**
 * This test classes uses the TestModule class to run all of it's tests. As such all tests will display:
 *      TEST [{test name}] SUCCEEDED!
 *
 * If they have passed their tests, otherwise it will say:
 *
 *      TEST [{test name}] FAILED! {reason}
 */

public class TestCaesarCipher extends Test {


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
     * Test to see if the encoding process succeeds or not
     * If successful, the encode will equal the string 'encoded'
     */
    public static void testCase1() {
        String decoded = "CONGRATULATIONS, YOU HAVE SUCCESSFULLY DECRYPTED THE ROTTHIRTEEN (OR CAESAR-SHIFT N) CIPHER!";
        String encoded = "PBATENGHYNGVBAF, LBH UNIR FHPPRFFSHYYL QRPELCGRQ GUR EBGGUVEGRRA (BE PNRFNE-FUVSG A) PVCURE!";

        CaesarCipher cipher = CaesarCipher.rot13(decoded);
        cipher.setKeepPunct(true);

        TestModule module = new TestModule(cipher, "ROT13_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    /**
     * Test to see if the null checking properly returns false
     */
    public static void testCase2() {
        TestModule module = new TestModule(CaesarCipher.rot13(null), "ROT13_NULL_CHECK");
        module.assertEncodingFail();
    }

    /**
     * Test to see if the decode method works when there is excessive punctuation in the given string and the keep
     * punctuation flag is false
     * If successful, the encode will equal the string 'encoded'
     */
    public static void testCase3() {
        String decoded = "!@$%!@$tes!@$!@$!@$@$ti&%$&$%&%&ng";
        String encoded = "YJXYNSL";

        CaesarCipher cipher = new CaesarCipher(decoded, 'F');
        cipher.setKeepPunct(false);

        TestModule module = new TestModule(cipher, "ROT5_ENCODING_PUNC_TEST");
        module.assertEncodingSuccess(encoded);
    }

    /**
     * Test to see if the decoding process succeeds or not
     * If successful, the encode will equal the string 'decoded'
     */
    public static void testCase4() {
        String decoded = "hello world";
        String encoded = "ROVVY GYBVN";

        CaesarCipher cipher = new CaesarCipher(encoded, true, 'K');
        TestModule module = new TestModule(cipher, "ROT10_DECODING_TEST");

        module.assertDecodingSuccess(decoded);
    }
}
