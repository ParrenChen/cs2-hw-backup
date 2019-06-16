// Parren Chen
// 4/23/19
// CSE 143 BH 
// TA: RanDair Porter
// Homework 3
// import the java util package
// The AssassinManager simulates an assassin game. It keeps track of the kill ring of 
// who is stalking who, and the graveyard of who was killed by who.

import java.util.*;

public class AssassinManager {
   
   // this AssassinNode object keeps track of the current kill ring
   private AssassinNode killFront;
   
   // this AssassinNode object keeps track of the dead people
   private AssassinNode graveFront;
   
   // pre: list.size() != 0 (throw IllegalArgumentException if not) 
   // post: constructs the AssassinManager object that takes in a list of names
   //       to produce the kill ring in the order from the given list.
   public AssassinManager(List<String> names) {
      if(names.size() == 0){
         throw new IllegalArgumentException();
      }
      killFront = new AssassinNode(names.get(0));
      AssassinNode current = killFront;
      for(int i = 1; i < names.size(); i++){
         while(current.next != null) {
            current = current.next;
         }
         current.next = new AssassinNode(names.get(i));
      }
   }
   
   // post: prints the names in order of killing is stalking the victum, 
   //       the last is stalking the first
   public void printKillRing() {
      AssassinNode current = killFront;
      while(current.next != null) {
         //indents 4 space for each line
         System.out.print("    " + current.name + " is stalking ");
         current = current.next;
         System.out.println(current.name);
      }
      if(current.next == null){
         //indents 4 space for each line
         System.out.println("    " + current.name + " is stalking " + killFront.name);
      }
   }
   
   // post: prints the names in the graveyard(people already dead) in 
   //       opposite kill order
   public void printGraveyard() {
      AssassinNode current = graveFront;
      while(current != null) {
         //indents 4 space for each line
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }
   }
   
   // post: returns true if the current kill ring has the given name(alive), ignores cases,
   //       returns false otherwise
   public boolean killRingContains(String name) {
      return ifContains(killFront, name);
   }
   
   // post: returns true if the current grave yard has the given name(dead), ignores cases,
   //       returns false otherwise
   public boolean graveyardContains(String name) {
      return ifContains(graveFront, name);
   }
   
   // post: returns true if the current list has the given name, ignores cases,
   //       returns false otherwise
   private boolean ifContains(AssassinNode type, String name) {
      AssassinNode current = type;
      while(current != null){
         if(current.name.toLowerCase().equals(name.toLowerCase())){
            return true;
         }
         current = current.next;
      }
      return false;
   }
   
   // post: returns true if the kill ring has only one person left which means
   //       the game is finished, returns false otherwise
   public boolean gameOver() {
      return killFront.next == null;
   }
   
   // post: returns the name the last person left in the kill ring at game over, returns
   //       null if the game is still going
   public String winner() {
      if(!gameOver()){
         return null;
      }
      return killFront.name;
   }
   
   // pre: killRingContains(name) (throw IllegalArgumentException if not)
   // pre: !gameOver() (throw IllegalStateException if not)
   // post: simulates the kill of a given name as victim in the kill ring, ignores cases, 
   //       moves the person from the kill ring to the grave yard, the most recent victim 
   //       appears at the front of the grave yard.
   public void kill(String name) {
      if(!killRingContains(name)) {
         throw new IllegalArgumentException();
      }
      if(gameOver()) {
         throw new IllegalStateException();
      }
      AssassinNode killCurrent = killFront;
      AssassinNode graveCurrent;
      while(killCurrent.next != null && !killCurrent.next.name.equalsIgnoreCase(name)){
         killCurrent = killCurrent.next;
      }
      if(killCurrent.next != null){
         graveCurrent = killCurrent.next;
         killCurrent.next = killCurrent.next.next;
      } else {
         graveCurrent = killFront;
         killFront = killFront.next;
      }
      graveCurrent.killer = killCurrent.name;
      graveCurrent.next = graveFront;
      graveFront = graveCurrent;
   }
   
} 