package testing;

public abstract class Test {
    public static void main(String[] a) {
        runAllTests();
    }

    /**
     * Runs all of the tests that have been defined
     */
    public static void runAllTests() {
        Test[] tests = new Test[] { new TestCaesarCipher(), new TestChecksumCoder(), new TestOneWordCipher(), new TestInterleaver() };

        for (Test t : tests) {
            t.run();
        }
    }


    public abstract void run();
}
