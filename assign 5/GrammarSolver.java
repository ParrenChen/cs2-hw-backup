// Parren Chen
// 5/8/19
// CSE 143 BH 
// TA: RanDair Porter
// Homework 5
// import the java util package
// The GrammarSolver generate random grammar message using the giving 
// list of grammar rules.

import java.util.*;

public class GrammarSolver{
   
   // the grammarMap is storing the value to each grammar key
   private SortedMap<String, String[]> grammarMap;
   
   // pre: !grammar.isEmpty() && no duplicated nonterminal key(throw 
   //                                    IllegalArgumentException if not)
   // post: constructs a GrammarSolver that using a list of gramamr that 
   //       initialize the grammar pattern for each nonterminal symbol
   public GrammarSolver(List<String> grammar){
      if(grammar.isEmpty()){
         throw new IllegalArgumentException();
      }
      grammarMap = new TreeMap<String, String[]>();
      for(String current : grammar){
         String[] parts = current.trim().split("::=");
         if(grammarMap.containsKey(parts[0])){
            throw new IllegalArgumentException();
         }  
         String[] words = parts[1].trim().split("[|]"); 
         grammarMap.put(parts[0], words); 
      }
   }
   
   // post: returns true if the given symbol is in the nonterminal symbol
   public boolean grammarContains(String symbol) {
      return grammarMap.containsKey(symbol);
   }
   
   // pre: times >= 0 && grammarContains(symbol)(throws 
   //                                     IllegalArgumentException if not)
   // post: returns the String array with a given symbol and the desired times
   //       that were randomly generated(equal chance) 
   public String[] generate(String symbol, int times) {
      if(times < 0 || !grammarContains(symbol)){
         throw new IllegalArgumentException();
      }
      String[] current = new String[times];
      for(int i = 0; i < times; i++){
         current[i] = singleSentence(symbol);
      }
      return current;
   }  
   
   // post: returns the single sentence with the given symbol
   private String singleSentence(String symbol){
      if(!grammarContains(symbol)){ 
         return symbol;
      } else { 
         String sentence = "";  
         String[] current = grammarMap.get(symbol);
         String choosenSymbol = current[(int)(Math.random() * current.length)];
         String[] words = choosenSymbol.trim().split("[ \t]+");
         for(String word : words){
           sentence += singleSentence(word) + " "; 
         }
         return sentence.trim();
      }
   }
   
   // post: returns the string representation of nonterminal symbols in order
   //       and comma-separating each symbol enclosed in []
   public String getSymbols() {
      return grammarMap.keySet().toString();
   }
   
}