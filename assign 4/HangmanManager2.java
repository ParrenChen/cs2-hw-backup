// Parren Chen
// 5/14/19
// CSE 143 BH 
// TA: RanDair Porter
// Homework 4 bonus
// import the java util package
// The HangmanManager2 simulates a more evil hangman game. It changes the wordlist 
// to guess while the user is guessing the word. It choose the word that has no guess
// character includes in the last guess.(part1 of bonus)

import java.util.*;

public class HangmanManager2 extends HangmanManager{

   // the guessLeft represents the guess the user lefts
   private int guessLeft;
   
   // the wordList is the current wordList pending to be selected
   private Set<String> wordList;
   
   // post: constructs a hangmanManager using a collection of dictionary, a desired word
   //       length and a desired max guess number at the initial state of the game. The 
   //       dictionary eliminates all duplicate words.
   public HangmanManager2(Collection<String> dictionary, int length, int max) {
      super(dictionary, length, max);
   }
   
   // post: returns the number of occurrences of the letter user guessed. Chooses the word
   //       (if exist) immediately as the user has only 1 guess left.
   public int record(char guess){
      this.guessLeft = super.guessesLeft();
      this.wordList = super.words();
      if(guessLeft == 1){
         String word = occurance(guess);
         if(word != null){
            wordList.clear();
            wordList.add(word);
         }
      }
      int result = super.record(guess);
      this.guessLeft = super.guessesLeft();
      return result;     
   }
   
   // post: returns a string if there is a word has no guess character occure, returns
   //       null otherwise
   private String occurance(char guess){
      for(String word : wordList){
         if(word.indexOf(guess) == -1){
            return word;
         }
      }
      return null;
   }
   
}