// Parren Chen
// 4/23/19
// CSE 143 BH 
// TA: RanDair Porter
// Homework 3
// import the java util package
// The HangmanManager simulates an hangman game. It changes the wordlist to guess while
// the user is guesing the word.

import java.util.*;

public class HangmanManager{
   
   // the guessLeft represents the guess the user lefts
   private int guessLeft;
   
   // the wordList is the current wordList pending to be selected
   private Set<String> wordList;
   
   // the guesses stores all characters that the user previously guessed
   private Set<Character> guesses;
   
   // the pattern is the pattern currently displaying to the user
   private String pattern;
   
   // pre: length >= 1 && max >= 0 (throw IllegalArgumentException if not)
   // post: constructs a hangmanManager using a collection of dictionary, a desired word
   //       length and a desired max guess number at the initial state of the game. The 
   //       dictionary eliminates all duplicate words.
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if(length < 1 || max < 0){
         throw new IllegalArgumentException();
      }
      wordList = new TreeSet<String>();
      guesses = new TreeSet<Character>();
      guessLeft = max;
      pattern = ""; //initialize the display pattern with dash
      for(int i = 0; i < length - 1; i++){
         pattern += "- ";
      }
      pattern += "-"; //fence-post to eliminate trailing space 
      for(String word : dictionary){ 
         if(word.length() == length){
            wordList.add(word);
         }
      }
   }
   
   // post: returns the current word list that are consider potential answer
   public Set<String> words() {
      return this.wordList;
   }
   
   // post: returns the number of allowed guesses left
   public int guessesLeft() {
      return this.guessLeft;
   }
   
   // post: returns all the previous guessed letters by the user
   public Set<Character> guesses() {
      return this.guesses;
   }
   
   // pre: !wordList.isEmpty() (throw IllegalStateException if not)
   // post: returns the current pattern displayed to the user, no space at 
   //       front or at end. Unguessed letters are in dashes and guessed letters are
   //       displayed. 
   public String pattern() {
      if(wordList.isEmpty()){
         throw new IllegalStateException();
      }
      return pattern;
   }
   
   // pre: guessLeft >= 1 && !wordList.isEmpty() (throw IllegalStateException if not)
   // pre: guess is in previous guesses && (guessLeft < 1 || wordList.isEmpty()) 
   //      (throw IllegalArgumentException if not)
   // post: returns the number of occurrences of the letter user guessed. Records guess
   //       of the user and updates the current word list as well as the pattern to
   //       display.
   public int record(char guess) {
      if(guessLeft < 1 || wordList.isEmpty()){
         throw new IllegalStateException();
      }
      if(guesses.contains(guess)){
         throw new IllegalArgumentException();
      }
      guesses.add(guess);
      Map<String, Set<String>> patternMap = updateMap(guess);
      mostPattern(patternMap);
      int occurrence = 0;
      for(int i = 0; i < pattern.length(); i++){
         if(pattern.charAt(i) == guess){
            occurrence++;
         }
      }
      if(occurrence == 0){
         guessLeft--;
      }
      return occurrence;
   }
   
   // post: finds the set with largest size in the given map and chooses 
   //       it as the current wordList
   private void mostPattern(Map<String, Set<String>> patternMap){
      int max = -1; // set the max to -1
      for(String wordFamilyKey : patternMap.keySet()){
         if(patternMap.get(wordFamilyKey).size() > max){
            max = patternMap.get(wordFamilyKey).size();
            wordList = patternMap.get(wordFamilyKey);
            pattern = wordFamilyKey;
         }
      }
   }
   
   // post: returns the updated map with current wordList with different guess display. 
   //       Adds the set to the existing pattern map, create a new set if not
   //       previously exist
   private Map<String, Set<String>> updateMap(char guess){
      Map<String, Set<String>> patternMap = new TreeMap<String, Set<String>>();
      for(String word : wordList){
         String currentPattern = pattern;
         for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == guess){
               currentPattern = replace(currentPattern, guess, i);
            }
         }
         if(!patternMap.containsKey(currentPattern)){
            patternMap.put(currentPattern, new TreeSet<String>());
         }
         patternMap.get(currentPattern).add(word);  
      }
      return patternMap;
   }
   
   // pre: index > 0
   // post: replace the current String with the given guess at the given index
   private String replace(String preString ,char guess, int index){
      return preString.substring(0, 2 * index) + guess + 
                       preString.substring(2 * index + 1);
   }
   
}