package testing;

import main.CaesarCipher;

public class TestCaesarCipher extends Test {

    @Override
    public void run() {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }

    public static void testCase1() {
        String decoded = "CONGRATULATIONS, YOU HAVE SUCCESSFULLY DECRYPTED THE ROTTHIRTEEN (OR CAESAR-SHIFT N) CIPHER!";
        String encoded = "PBATENGHYNGVBAF, LBH UNIR FHPPRFFSHYYL QRPELCGRQ GUR EBGGUVEGRRA (BE PNRFNE-FUVSG A) PVCURE!";

        CaesarCipher cipher = CaesarCipher.rot13(decoded);
        cipher.setKeepPunct(true);

        TestModule module = new TestModule(cipher, "ROT13_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    public static void testCase2() {
        TestModule module = new TestModule(CaesarCipher.rot13(null), "ROT13_NULL_CHECK");
        module.assertEncodingFail();
    }

    public static void testCase3() {
        String decoded = "testing";
        String encoded = "YJXYNSL";

        CaesarCipher cipher = new CaesarCipher(decoded, 'F');
        cipher.setKeepPunct(false);

        TestModule module = new TestModule(cipher, "ROT5_ENCODING_PUNC_TEST");
        module.assertEncodingSuccess(encoded);
    }

    public static void testCase4() {
        String decoded = "hello world";
        String encoded = "ROVVY GYBVN";

        CaesarCipher cipher = new CaesarCipher(encoded, true, 'K');
        TestModule module = new TestModule(cipher, "ROT10_DECODING_TEST");

        module.assertDecodingSuccess(decoded);
    }
}
