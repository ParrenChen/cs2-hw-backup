// This program tests stage 3 of the LetterInventory class.  The program reads
// from the file test3.txt which has a series of test cases with the correct
// answers.

import java.util.*;
import java.io.*;

public class Test3 {
    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File("test3.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("You must copy test3.txt to this directory" +
                               " before running the testing program.");
            System.exit(1);
        }
        while (input.hasNext()) {
            String s1 = input.nextLine();
            String s2 = input.nextLine();
            System.out.println("Testing these two strings:");
            System.out.println("    i1: \"" + s1 + "\"");
            System.out.println("    i2: \"" + s2 + "\"");
            System.out.println("constructing i1 and i2");
            LetterInventory i1 = null;
            LetterInventory i2 = null;
            try {
                i1 = new LetterInventory(s1);
                i2 = new LetterInventory(s2);
            } catch (Exception e) {
                System.out.println("failed");
                System.out.println("    threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("    in LetterInventory line#" + line);
                System.exit(1);
            }

            System.out.println("constructing and testing i1.add(i2)");
            try {
                LetterInventory test = i1.add(i2);
                check(test, input);
            } catch (Exception e) {
                System.out.println("add failed");
                System.out.println("    threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("    in LetterInventory line#" + line);
                System.exit(1);
            }

            System.out.println("constructing and testing i2.add(i1)");
            try {
                LetterInventory test = i2.add(i1);
                check(test, input);
            } catch (Exception e) {
                System.out.println("add failed");
                System.out.println("    threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("    in LetterInventory line#" + line);
                System.exit(1);
            }

            System.out.println("constructing and testing i1.subtract(i2)");
            try {
                LetterInventory test = i1.subtract(i2);
                check(test, input);
            } catch (Exception e) {
                System.out.println("subtract failed");
                System.out.println("    threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("    in LetterInventory line#" + line);
                System.exit(1);
            }

            System.out.println("constructing and testing i2.subtract(i1)");
            try {
                LetterInventory test = i2.subtract(i1);
                check(test, input);
            } catch (Exception e) {
                System.out.println("subtract failed");
                System.out.println("    threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("    in LetterInventory line#" + line);
                System.exit(1);
            }
            if (input.hasNextLine())
                input.nextLine();  // to skip end-of-line
            System.out.println();
        }
        System.out.println("All tests passed.");
    }

    // pre : input file contains a line with correct values for the given
    //       inventory
    // post: reports whether or not test was passed
    public static void check(LetterInventory tester, Scanner input) {
        String text = input.next();
        if (text.equals("null")) {
            if (tester == null) {
                System.out.println("passed because inventory is null");
            } else {
                System.out.println("failed because inventory should be null");
                System.exit(1);
            }
        } else {
            testToString(tester, input, text);
            testSize(tester, input);
            testIsEmpty(tester, input);
            testGet(tester, input);
            System.out.println("toString, size, isEmpty, and count all passed");
        }

    }

    // post: reports whether or not test was passed
    public static void testToString(LetterInventory tester, Scanner input, 
                                    String correct) {
        System.out.println("inventory = " + correct);
        String test = "";
        try {
            test = tester.toString();
        } catch (Exception e) {
            System.out.println("toString failed");
            System.out.println("    threw exception: " + e);
            int line = e.getStackTrace()[0].getLineNumber();
            System.out.println("    in LetterInventory line#" + line);
            System.exit(1);
        }
        if (!correct.equals(test)) {
            System.out.println("toString failed");
            System.out.println("    correct toString = " + correct);
            System.out.println("    your toString    = " + test);
            System.exit(1);
        }
    }

    // pre : input file contains correct size
    // post: reports whether or not test was passed
    public static void testSize(LetterInventory tester, Scanner input) {
        int correct = input.nextInt();
        int test = 0;
        try {
            test = tester.size();
        } catch (Exception e) {
            System.out.println("size failed");
            System.out.println("    threw exception: " + e);
            int line = e.getStackTrace()[0].getLineNumber();
            System.out.println("    in LetterInventory line#" + line);
            System.exit(1);
        }
        if (correct != test) {
            System.out.println("size failed");
            System.out.println("    correct size = " + correct);
            System.out.println("    your size    = " + test);
            System.exit(1);
        }
    }

    // pre : input file contains correct isEmpty
    // post: reports whether or not test was passed
    public static void testIsEmpty(LetterInventory tester, Scanner input) {
        boolean correct = input.nextBoolean();
        boolean test = false;
        try {
            test = tester.isEmpty();
        } catch (Exception e) {
            System.out.println("isEmpty failed");
            System.out.println("    threw exception: " + e);
            int line = e.getStackTrace()[0].getLineNumber();
            System.out.println("    in LetterInventory line#" + line);
            System.exit(1);
        }
        if (correct != test) {
            System.out.println("isEmpty failed");
            System.out.println("    correct isEmpty = " + correct);
            System.out.println("    your isEmpty    = " + test);
            System.exit(1);
        }
    }

    // pre : input file contains correct get values (26 of them)
    // post: reports whether or not test was passed
    public static void testGet(LetterInventory tester, Scanner input) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int correct = input.nextInt();
            int test = 0;
            try {
                test = tester.get(ch);
            } catch (Exception e) {
                System.out.println("get failed for '" + ch + "'");
                System.out.println("    threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("    in LetterInventory line#" + line);
                System.exit(1);
            }
            if (correct != test) {
                System.out.println("get failed for '" + ch + "'");
                System.out.println("    correct get = " + correct);
                System.out.println("    your get    = " + test);
                System.exit(1);
            }
        }
    }
}
