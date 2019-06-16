// Parren Chen
// CSE 143 BH 
// TA: RanDair Porter
// Homework 7
// import the java util and io package
// The QuestionTree object is a binary search tree that having many yes/no questions at
// brunch and make the user to prompt to guess the object the user thinking. The 
// game stops until successfully guessed the object or guessed the wrong object at 
// the leaf node that stores the object.

import java.util.*;
import java.io.*;

public class QuestionTree {

   // the scanner for user interaction
   private Scanner console;
   // the overall root of the QuestionTree
   private QuestionNode root;
   
   // post: constructs a QuestionTree with the inital "computer" QuestionNode
   public QuestionTree(){
      console = new Scanner(System.in);
      root = new QuestionNode("computer");
   }
   
   // pre: file in standard form and legal input
   // post: replaces the current QuestionTree with the input from a given file
   public void read(Scanner input){
      root = readHelper(input);
   }
   
   // post: creates a new QuestionNode for given file input and returns
   //       the QuestionNode 
   private QuestionNode readHelper(Scanner input){
      String type = input.nextLine();
      String line = input.nextLine();
      QuestionNode newNodes = new QuestionNode(line);
      if(type.equals("Q:")){
         newNodes.left = readHelper(input);
         newNodes.right = readHelper(input);
      }
      return newNodes;
   }
   
   // post: stores the current QuestionNode tree and prints everything in the tree
   //       to a desired output file
   public void write(PrintStream output){
      printPreorder(output, root);
   }
   
   // post: prints each QuestionNode of the QuestionTree to standard form.
   //       For questions, it begins with "Q:" and for answers with "A:".
   private void printPreorder(PrintStream output, QuestionNode current){
      if(current != null){
         if(current.left != null){
            output.println("Q:");
         } else {
            output.println("A:");
         }
         output.println(current.content);
         printPreorder(output, current.left);
         printPreorder(output, current.right);
      }
   }
   
   // post: uses the current tree to ask user the yes/no questions untill guessing
   //       the object right or guess the wrong answer(fail). For the failed case, 
   //       expands the QuestionTree to include the correct answer and the question
   //       to identify the object.
   public void askQuestions(){
      root = askQuestionsHelper(root);
   }
   
   // post: returns the QuestionNode of current tree that prompt user for each guess, 
   //       until the object is correctly guessed or failed to guess. Adds new 
   //       QuestionNode to current tree and returns the tree      
   private QuestionNode askQuestionsHelper(QuestionNode current){
      if(current.left == null || current.right == null){
         boolean result = yesTo("Would your object happen to be " + current.content + "?");
         if(result){
            System.out.println("Great, I got it right!");
         } else { // !result
            System.out.print("What is the name of your object? ");
            String correctAnswer = console.nextLine();
            QuestionNode newAnswer = new QuestionNode(correctAnswer);
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String newQuestion = console.nextLine();
            if(yesTo("And what is the answer for your object?")){
               current = new QuestionNode(newQuestion, newAnswer, current);
            } else {
               current = new QuestionNode(newQuestion, current, newAnswer);
            }
          }
      } else { // root.left != null || root.right != null
         if(yesTo(current.content)){
            current.left = askQuestionsHelper(current.left);
         } else { // !yesTo(root.content)
            current.right = askQuestionsHelper(current.right);
         }
      }
      return current;
   }
   
   // post: asks the user a question, forcing an answer of "y " or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }

}