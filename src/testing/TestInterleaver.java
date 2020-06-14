package testing;


import main.Interleaver;

import java.lang.reflect.GenericDeclaration;

public class TestInterleaver extends Test {

    @Override
    public void run() {
        testCase1();
        testCase2();
        testCase3();
    }

    public static void testCase1() {
        String decoded = "Welcome to Jamaica. I hope you have an enjoyable stay.";
        String encoded = "Wotemole c JacIma a.hi opoaeuv  eyh ananjb oleye y s. t  a  ";

        TestModule module = new TestModule(new Interleaver(decoded, 3, 4), "INTERLEAVER_ENCODING_TEST");
        module.assertEncodingSuccess(encoded);
    }

    public static void testCase2() {
        TestModule module = new TestModule(new Interleaver(null, true, 3, 4), "INTERLEAVER_NULL_CHECK");
        module.assertDecodingFail();
    }

    public static void testCase3() {
        String decoded = "Great work. You have managed to unjumble this mess.";
        String encoded = "Gtrr kew.ao Yh oamuva ena ugtneojd um sbt lhmeies  s  .     ";

        TestModule module = new TestModule(new Interleaver(encoded, true, 3, 4), "INTERLEVER_DECODER_TEST");
        module.assertDecodingSuccess(decoded);
    }
}
