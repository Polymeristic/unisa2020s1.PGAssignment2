package testing;

import main.CaesarCipher;
import main.ChecksumCoder;

public class TestChecksumCoder extends Test {

    @Override
    public void run() {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        String decoded = "To err is human but to forgive is divine.";
        String encoded = "ToX errS isK humanO butE toX forgiveV isK divine.D";

        ChecksumCoder cipher = new ChecksumCoder(decoded);

        TestModule module = new TestModule(cipher, "CHECKSUM_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    public static void testCase2() {
        TestModule module = new TestModule(new ChecksumCoder(null), "CHECKSUM_NULL_CHECK");
        module.assertDecodingFail();
    }

    public static void testCase3() {
        String decoded = "Who controls the past controls the future. Who controls the present controls the past.";
        String encoded = "WhoE controlsG theE pastC controlsG theE future.K WhoE controlsG theE presentI controlsG theE past.C";

        ChecksumCoder cipher = new ChecksumCoder(encoded, true, new int[] { 8, 4 });

        TestModule module = new TestModule(cipher, "CHECKSUM_DECODING_TEST");
        module.assertDecodingSuccess(decoded);
    }
}
