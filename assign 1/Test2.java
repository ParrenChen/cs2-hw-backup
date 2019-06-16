// This program tests stage 2 of the LetterInventory class.  The program reads
// from the file test2.txt which has a series of test cases with the correct
// answers.

import java.util.*;
import java.io.*;

public class Test2 {
    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File("test2.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("You must copy test2.txt to this directory" +
                               " before running the testing program.");
            System.exit(1);
        }
        LetterInventory tester = new LetterInventory("");
        System.out.println("Starting with empty inventory");
        check(tester, input);
        while (input.hasNext()) {
            char ch = input.next().charAt(0);
            int count = input.nextInt();
            System.out.println("setting count for " + ch + " to " + count);
            try {
                tester.set(ch, count);
            } catch(Exception e) {
                System.out.println("failed");
                System.out.println("    threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("    in LetterInventory line#" + line);
                System.exit(1);
            }
            check(tester, input);
        }
        System.out.println("All tests passed.");
    }

    // pre : input file contains a 2-line test case that contains the state
    //       that the given inventory should be in after performing the given
    //       call on get
    // post: reports whether or not test was passed
    public static void check(LetterInventory tester, Scanner input) {
        testSize(tester, input);
        testToString(tester, input);
        testIsEmpty(tester, input);
        testGet(tester, input);
        System.out.println("size, toString, isEmpty, and count all passed");
        System.out.println();
    }

    // pre : input file contains correct toString
    // post: reports whether or not test was passed
    public static void testToString(LetterInventory tester, Scanner input) {
        String correct = input.next();
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
