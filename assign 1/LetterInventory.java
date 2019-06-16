// Parren Chen
// CSE 143 BH 
// TA: RanDair Porter
// Homework 1
// import the java util package
// The LetterInventory object represents each character counts of a given String. It
// constructs a LetterInvtory object and manipulating each count, adding or subtracting
// two LetterInventory.

import java.util.*;

public class LetterInventory{
   
   // represent the alphabetical number of English characters is 26
   public static final int LETTER = 26;   
   // the number of occurrence of each letter in a given word
   private int[] inventory;
   // the total number of the letter occurrence 
   private int size;
   
   // post: Contructs a LetterInventory object that counts the occurrence of each letter
   //       in a given String, counts only the alphabetic characters and ignores cases
   public LetterInventory(String data){
      inventory = new int[LETTER];
      char current;
      for(int i = 0; i < data.length(); i++){
         current = data.toLowerCase().charAt(i);
         if(Character.isLetter(current)){
            inventory[current - 'a']++;
            size++;
         }
      } 
   }
 
   // pre: letter is an alphabetic character(throws IllegalArgumentException if not)
   // post: Returns the number counts of the specified letter, ignores cases
   public int get(char letter){
      if(!Character.isLetter(letter)){
         throw new IllegalArgumentException();
      }
      return inventory[Character.toLowerCase(letter) - 'a'];
   }
   
   // pre: letter is an alphabetic character, and value >= 0(throws 
   //      IllegalArgumentException if not)
   // post: Replaces the number of the specified letter in LetterInventory 
   //       with the specified number, ignores cases
   public void set(char letter, int value){
      if(value < 0 || !Character.isLetter(letter)){
         throw new IllegalArgumentException();
      }
      size = size - inventory[Character.toLowerCase(letter) - 'a'] + value;
      inventory[Character.toLowerCase(letter) - 'a'] = value;
   }
   
   // post: Returns the number of the total letter occurrence
   public int size(){
      return size;
   }
   
   // post: Returns true if this LetterInventory has no letter occurrence
   //       false otherwise
   public boolean isEmpty(){
      return size == 0;
   }
   
   // post: Returns a String of the LetterInventory objects with occurrence
   //       of each letter in accssending order and in all lower case.
   public String toString(){
      String result = "[";
      for(int i = 0; i < inventory.length; i++){
         for(int j = 0; j < inventory[i]; j++){
            result += (char)('a' + i);
         }
      }
      result += "]";
      return result;
   }
   
   // post: Constructs and returns a new LetterInventory which is the sum to another 
   //       LetterInventory of each letter occurrence 
   public LetterInventory add(LetterInventory other){
      LetterInventory result = new LetterInventory(this.toString() + other.toString());
      return result;
   }
   
   // post: Constructs and returns a new LetterInventory which is the subtraction to
   //       another LetterInventory of each letter occurrence, return null if any letter
   //       count becomes negative
   public LetterInventory subtract(LetterInventory other){
      LetterInventory result = new LetterInventory("");
      for(int i = 0; i < LETTER; i++){
         result.inventory[i] = this.inventory[i] - other.inventory[i];
         if(result.inventory[i] < 0){
            return null;
         }
      }
      result.size = this.size - other.size;
      return result;
   }
   
}

