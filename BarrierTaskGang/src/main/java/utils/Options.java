package utils;

import tasks.CyclicSearchWithCyclicBarrier;
import tasks.CyclicSearchWithPhaser;
import tasks.OneShotSearchWithCountDownLatch;

/**
 * This class implements the Singleton pattern to handle
 * command-line option processing.
 */
public class Options {
    /**
     * Array of words to search for in the input.
     */
    public final static String[] sWordList = {"do",
                                              "re",
                                              "mi",
                                              "fa",
                                              "so",
                                              "la",
                                              "ti",
                                              "oo"};

    /**
     * Used by the {@link OneShotSearchWithCountDownLatch} test that
     * searches for the words concurrently in multiple threads.
     */
    public final static String[][] sOneShotInputStrings = {
        {"xreo",
         "xfao",
         "xmiomio",
         "xlao",
         "xtiotio",
         "xsoosoo",
         "xdoo",
         "xdoodoo"}
    };

    /**
     * Used by the {@link CyclicSearchWithCyclicBarrier} test that
     * continues to search a fixed number of words/Threads
     * concurrently until there's no more input to process.
     */
    public final static String[][] sFixedNumberOfInputStrings = {
        {"xdoodoo",
         "xreo",
         "xmiomio",
         "xfao",
         "xsoosoo",
         "xlao",
         "xtiotio",
         "xdoo"},
        {"xdoo",
         "xreoreo",
         "xmio",
         "xfaofao",
         "xsoo",
         "xlaolao",
         "xtio",
         "xdoodooo"}
    };

    /**
     * Used by the {@link CyclicSearchWithPhaser} test that continues
     * to search a variable number of words/threads concurrently until
     * there's no more input to process.
     */
    public final static String[][] sVariableNumberOfInputStrings = {
        {"xfaofao"},
        {"xsoo", "xlaolao", "xtio", "xdoodooo", "xhodor"},
        {"xdoo", "xreoreo"},
        {"xreoreo", "xdoo"},
        {"xyyz"},
        {"xdoodoo", "xreo", "xmiomio"}
    };

    /**
     * The singleton {@link Options} instance.
     */
    private static Options mUniqueInstance = null;

    /**
     * Controls whether debugging output will be generated (defaults
     * to false).
     */
    private boolean mDiagnosticsEnabled = false;

    /**
     * Method to return the one and only singleton uniqueInstance.
     */
    public static Options instance() {
        if (mUniqueInstance == null)
            mUniqueInstance = new Options();

        return mUniqueInstance;
    }

    /**
     * Returns whether debugging output is generated.
     */
    public boolean diagnosticsEnabled() {
        return mDiagnosticsEnabled;
    }

    /**
     * Print debugging output if {@code mDiagnosticsEnabled} is true.
     */
    public static void printDebugging(String output) {
        if (Options.instance().diagnosticsEnabled())
            System.out.println("["
                               + Thread.currentThread().threadId()
                               + "] "
                               +
                               output);
    }

    /**
     * Print output.
     */
    public static void print(String output) {
        System.out.println("["
                           + Thread.currentThread().threadId()
                           + "] "
                           +
                           output);
    }

    /**
     * Parse command-line arguments and set the appropriate values.
     */
    public void parseArgs(String[] argv) {
        if (argv != null) {
            for (int argc = 0; argc < argv.length; argc += 2)
                if (argv[argc].equals("-d"))
                    mDiagnosticsEnabled = argv[argc + 1].equals("true");
        } else {
            printUsage();
            return;
        }
    }

    /**
     * Print out usage and default values.
     */
    public void printUsage() {
        System.out.println("Usage: ");
        System.out.println("-d [true|false]");
    }

    /**
     * Make the constructor private for a singleton.
     */
    private Options() {
    }
}