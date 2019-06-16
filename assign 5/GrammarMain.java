// GrammarMain contains a main program that prompts a user for the name of a
// grammar file and then gives the user the opportunity to generate random
// versions of various elements of the grammar.

import java.io.*;
import java.util.*;

public class GrammarMain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the cse143 random sentence generator.");
        System.out.println();

        // open grammar file
        System.out.print("What is the name of the grammar file? ");
        String fileName = console.nextLine();
        Scanner input = new Scanner(new File(fileName));

        // read the grammar file and construct the grammar solver
        List<String> grammar = new ArrayList<String>();
        while (input.hasNextLine()) {
            String next = input.nextLine().trim();
            if (next.length() > 0) {
                grammar.add(next);
            }
        }
        GrammarSolver solver = 
            new GrammarSolver(Collections.unmodifiableList(grammar));

        showResults(console, solver);
    }

    // pre : console open for console reading, solver initialized
    // post: allows the user to repeatedly pick a grammar element to generate
    public static void showResults(Scanner console, GrammarSolver solver) {
        boolean done = false;
        while (!done) {
            System.out.println();
            System.out.println("Available symbols to generate are:");
            System.out.println(solver.getSymbols());
            System.out.print("What do you want generated (return to quit)? ");
            String target = console.nextLine();
            if (target.length() == 0) {
                done = true;
            } else if (!solver.grammarContains(target))
                System.out.println("Illegal symbol");
            else {
                System.out.print("How many do you want me to generate? ");
                if (!console.hasNextInt()) {
                    System.out.println("that's not an integer");
                } else {
                    int number = console.nextInt();
                    if (number < 0) {
                        System.out.println("no negatives allowed");
                    } else {
                        String[] answers = solver.generate(target, number);
                        for (int i = 0; i < number; i++) {
                            System.out.println(answers[i]);
                        }
                    }
                }
                console.nextLine();  // to position to next line
            }
        }
    }
}
