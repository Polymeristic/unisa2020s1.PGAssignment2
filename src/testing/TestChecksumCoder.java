package testing;

import main.ChecksumCoder;

/**
 * This test classes uses the TestModule class to run all of it's tests. As such all tests will display:
 *      TEST [{test name}] SUCCEEDED!
 *
 * If they have passed their tests, otherwise it will say:
 *
 *      TEST [{test name}] FAILED! {reason}
 */

public class TestChecksumCoder extends Test {

    /**
     * Run all testers
     */
    @Override
    public void run() {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
    }

    /**
     * Test to see if the encoding process succeeds or not
     * If successful, the encode will equal the string 'encoded'
     */
    public static void testCase1() {
        String decoded = "To err is human but to forgive is divine.";
        String encoded = "ToX errS isK humanO butE toX forgiveV isK divine.D";

        ChecksumCoder cipher = new ChecksumCoder(decoded);

        TestModule module = new TestModule(cipher, "CHECKSUM_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    /**
     * Test to see if the null checking properly returns false
     */
    public static void testCase2() {
        TestModule module = new TestModule(new ChecksumCoder(null), "CHECKSUM_NULL_CHECK");
        module.assertDecodingFail();
    }

    /**
     * Test to see if the decoding process succeeds or not
     * If successful, the encode will equal the string 'decoded'
     */
    public static void testCase3() {
        String decoded = "Who controls the past controls the future. Who controls the present controls the past.";
        String encoded = "WhoE controlsG theE pastC controlsG theE future.K WhoE controlsG theE presentI controlsG theE past.C";

        ChecksumCoder cipher = new ChecksumCoder(encoded, true, new int[] { 8, 4 });

        TestModule module = new TestModule(cipher, "CHECKSUM_DECODING_TEST");
        module.assertDecodingSuccess(decoded);
    }

    /**
     * Test to see if the decoding process succeeds or not even with an error included
     * If successful, the encode will equal the string 'decoded'
     */
    public static void testCase4() {
        String decoded = "Who controls the ???? controls the future. Who controls the present controls the past.";
        String encoded = "WhoE controlsG theE paYIC controlsG theE future.K WhoE controlsG theE presentI controlsG theE past.C";

        ChecksumCoder cipher = new ChecksumCoder(encoded, true, new int[] { 8, 4 });
        cipher.setHandleExcept(false);

        TestModule module = new TestModule(cipher, "CHECKSUM_ERROR_DECODE_TEST");
        module.assertDecodingSuccess(decoded);
    }


    public static void testCase5() {
        String encoded = "WhoE controlsG theE paYIC controlsG theE future.K WhoE controlsG theE presentI controlsG theE past.C";

        ChecksumCoder cipher = new ChecksumCoder(encoded, true, new int[] { 8, 4 });
        cipher.setHandleExcept(true);

        TestModule module = new TestModule(cipher, "CHECKSUM_ERROR_TEST");
        module.assertDecodingFail();
    }
}
