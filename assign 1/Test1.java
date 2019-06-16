// This program tests stage 1 of the LetterInventory class.  The program reads
// from the file test1.txt which has a series of test cases with the correct
// answers.

import java.util.*;
import java.io.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File("test1.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("You must copy test1.txt to this directory" +
                               " before running the testing program.");
            System.exit(1);
        }
        int cases = input.nextInt();
        input.nextLine();  // to throw away end-of-line
        String[] strings = new String[cases];
        LetterInventory[] inventories = new LetterInventory[cases];
        testConstructor(inventories, input, strings);
        testSize(inventories, input, strings);
        testIsEmpty(inventories, input, strings);
        testGet(inventories, input, strings);
        testToString(inventories, input, strings);
        System.out.println("All tests passed.");
    }

    // pre : arrays have been initalized with correct size, input is positioned
    //       at a sequence of strings to be converted to inventories
    // post: inventories and strings arrays populated if no error encountered
    public static void testConstructor(LetterInventory[] inventories,
                                       Scanner input, String[] strings) {
        System.out.println("Testing constructor...");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = input.nextLine();
            System.out.print("    testing \"" + strings[i] + "\"");
            try {
                inventories[i] = new LetterInventory(strings[i]);
                System.out.println("...passed");
            } catch (Exception e) {
                System.out.println("...failed");
                System.out.println("        threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("        in LetterInventory line#" + line);
                System.exit(1);
            }
        }
        System.out.println("passed");
        System.out.println();
    }

    // pre : inventories and strings contain a series of test cases; input
    //       positioned at a sequence of correct sizes for the given test cases
    // post: prints result of testing, exiting the program if an error is seen
    public static void testSize(LetterInventory[] inventories, Scanner input,
                                String[] strings) {
        System.out.println("Testing size...");
        for (int i = 0; i < strings.length; i++) {
            System.out.print("    testing \"" + strings[i] + "\"");
            int correct = input.nextInt();
            int test = 0;
            try {
                test = inventories[i].size();
            } catch (Exception e) {
                System.out.println("...failed");
                System.out.println("        threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("        in LetterInventory line#" + line);
                System.exit(1);
            }
            if (correct == test) {
                System.out.println("...passed");
            } else {
                System.out.println("...failed");
                System.out.println("        correct size = " + correct);
                System.out.println("        your size    = " + test);
                System.exit(1);
            }
        }
        System.out.println("passed");
        System.out.println();
    }

    // pre : inventories and strings contain a series of test cases; input
    //       positioned at a sequence of correct toString entries for the given
    //       test cases
    // post: prints result of testing, exiting the program if an error is seen
    public static void testToString(LetterInventory[] inventories,
                                    Scanner input, String[] strings) {
        System.out.println("Testing toString...");
        for (int i = 0; i < strings.length; i++) {
            System.out.print("    testing \"" + strings[i] + "\"");
            String correct = input.next();
            String test = "";
            try {
                test = inventories[i].toString();
            } catch (Exception e) {
                System.out.println("...failed");
                System.out.println("        threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("        in LetterInventory line#" + line);
                System.exit(1);
            }
            if (correct.equals(test)) {
                System.out.println("...passed");
            } else {
                System.out.println("...failed");
                System.out.println("        correct toString = " + correct);
                System.out.println("        your toString    = " + test);
                System.exit(1);
            }
        }
        System.out.println("passed");
        System.out.println();
    }

    // pre : inventories and strings contain a series of test cases; input
    //       positioned at a sequence of correct get entries for the given
    //       test cases (26 counts for each case for each of the 26 letters)
    // post: prints result of testing, exiting the program if an error is seen
    public static void testGet(LetterInventory[] inventories, Scanner input,
                               String[] strings) {
        System.out.println("Testing get...");
        for (int i = 0; i < strings.length; i++) {
            System.out.print("    testing \"" + strings[i] + "\"");
            for (char ch = 'a'; ch <= 'z'; ch++) {
                int correct = input.nextInt();
                testLetter(ch, correct, inventories[i]);
                testLetter(Character.toUpperCase(ch), correct, inventories[i]);
            }
            System.out.println("...passed");
        }
        System.out.println("passed");
        System.out.println();
    }

    // post: tests whether a call on get for the given character returns the
    //       given count, exiting the program if an error is seen
    public static void testLetter(char ch, int correct, 
                                  LetterInventory inventory) {
        int test = 0;
        try {
            test = inventory.get(ch);
        } catch (Exception e) {
            System.out.println("...failed for get on '" + ch + "'");
            System.out.println("        threw exception: " + e);
            int line = e.getStackTrace()[0].getLineNumber();
            System.out.println("        in LetterInventory line#" + line);
            System.exit(1);
        }
        if (correct != test) {
            System.out.println("...failed for get on '" + ch + "'");
            System.out.println("        correct get = " + correct);
            System.out.println("        your get    = " + test);
            System.exit(1);
        }
    }

    // pre : inventories and strings contain a series of test cases; input
    //       positioned at a sequence of correct isEmpty entries for the given
    //       test cases
    // post: prints result of testing, exiting the program if an error is seen
    public static void testIsEmpty(LetterInventory[] inventories, 
                                   Scanner input, String[] strings) {
        System.out.println("Testing isEmpty...");
        for (int i = 0; i < strings.length; i++) {
            System.out.print("    testing \"" + strings[i] + "\"");
            boolean correct = input.nextBoolean();
            boolean test = false;
            try {
                test = inventories[i].isEmpty();
            } catch (Exception e) {
                System.out.println("...failed");
                System.out.println("        threw exception: " + e);
                int line = e.getStackTrace()[0].getLineNumber();
                System.out.println("        in LetterInventory line#" + line);
                System.exit(1);
            }
            if (correct == test) {
                System.out.println("...passed");
            } else {
                System.out.println("...failed");
                System.out.println("        correct isEmpty = " + correct);
                System.out.println("        your isEmpty    = " + test);
                System.exit(1);
            }
        }
        System.out.println("passed");
        System.out.println();
    }
}
