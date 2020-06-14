package testing;


import main.Interleaver;


/**
 * This test classes uses the TestModule class to run all of it's tests. As such all tests will display:
 *      TEST [{test name}] SUCCEEDED!
 *
 * If they have passed their tests, otherwise it will say:
 *
 *      TEST [{test name}] FAILED! {reason}
 */

public class TestInterleaver extends Test {

    /**
     * Run all testers
     */
    @Override
    public void run() {
        testCase1();
        testCase2();
        testCase3();
    }

    /**
     * Test to see if the encoding process succeeds or not
     * If successful, the encode will equal the string 'encoded'
     */
    public static void testCase1() {
        String decoded = "Welcome to Jamaica. I hope you have an enjoyable stay.";
        String encoded = "Wotemole c JacIma a.hi opoaeuv  eyh ananjb oleye y s. t  a  ";

        TestModule module = new TestModule(new Interleaver(decoded, 3, 4), "INTERLEAVER_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    /**
     * Test to see if the null checking properly returns false
     */
    public static void testCase2() {
        TestModule module = new TestModule(new Interleaver(null, true, 3, 4), "INTERLEAVER_NULL_CHECK");
        module.assertDecodingFail();
    }

    /**
     * Test to see if the decoding process succeeds or not
     * If successful, the encode will equal the string 'decoded'
     */
    public static void testCase3() {
        String decoded = "Great work. You have managed to unjumble this mess.";
        String encoded = "Gtrr kew.ao Yh oamuva ena ugtneojd um sbt lhmeies  s  .     ";

        TestModule module = new TestModule(new Interleaver(encoded, true, 3, 4), "INTERLEVER_DECODER_TEST");
        module.assertDecodingSuccess(decoded);
    }
}
