// Parren Chen
// CSE 143 BH 
// TA: RanDair Porter
// Homework 6
// import the java util package
// The AnagramSolver object uses a list of dictionary to find combination of words 
// of a specific phrase with same letters.

import java.util.*;

public class AnagramSolver{
   
   // stores all words in dictionary
   private List<String> dictionary;
   // stores a word and its LetterInventory object
   private Map<String, LetterInventory> dictionaryMap;
   
   // pre: list contains no duplicates, list is nonempty and contains nonempty
   //      sequences of letters
   // post: constructs an AnagramSolver object using a given list of words, and
   //       initializes each word in the list with its LetterInventory object
   public AnagramSolver(List<String> list){
      dictionary = new ArrayList<String>();
      for(int i = 0; i < list.size(); i++){
         dictionary.add(list.get(i));
      }
      dictionaryMap = new HashMap<String, LetterInventory>();
      for(int i = 0; i < dictionary.size(); i++){
         LetterInventory temp = new LetterInventory(dictionary.get(i));
         dictionaryMap.put(dictionary.get(i), temp);   
      }
   }
   
   // pre: max >= 0 (throw IllegalArgumentException if not) 
   // post: finds and prints combination of words that has the same letter of the 
   //       given String s in the limit word number of max(unlimited word number if 
   //       max is 0). The combination order has the same order to the dictionary 
   //       order
   public void print(String s, int max){
      if(max < 0){
         throw new IllegalArgumentException();
      }
      LetterInventory current = new LetterInventory(s);
      List<String> smallDic = new ArrayList<String>();
      for(int i = 0; i < dictionary.size(); i++){
         LetterInventory temp = new LetterInventory(dictionary.get(i));
         if(current.subtract(temp) != null){
            smallDic.add(dictionary.get(i));
         }
      }
      List<String> output = new ArrayList<String>();
      printHelper(smallDic, current, max, output);
   }

   // post: helper method that takes in a desired string, max number of max, 
   //       a smaller dictionary of related words and list of output to print
   //       out the combination result
   private void printHelper(List<String>smallDic, LetterInventory current, int max,
                                                    List<String> output){
      if(current.isEmpty() && (max == 0 || output.size() <= max)){
         System.out.println(output);
      } else {
         for(String dictionaryKey : smallDic){
            if(current.subtract(dictionaryMap.get(dictionaryKey)) != null){
               output.add(dictionaryKey);
               printHelper(smallDic, current.subtract(dictionaryMap.get(dictionaryKey))
                                                               , max, output);
               output.remove(output.size() - 1);
            } 
         }
      }
   }

}