// Parren Chen
// 4/16/19
// CSE 143 BH 
// TA: RanDair Porter
// Homework 2
// import the java util package
// The Guitar37 class simulates the sound of a guitar with 37 strings.

public class Guitar37 implements Guitar {
   
   //keyboard layout
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 
   //the strings need to be modeled
   private GuitarString[] strings;
   //the number of tic update
   private int time;
   
   //post: constructs a Guitar37 that have 37 different strings
   public Guitar37() {
      strings = new GuitarString[KEYBOARD.length()];
      for(int i = 0; i < KEYBOARD.length(); i++){   
         strings[i] = new GuitarString(frequency(i));
      }
   }
   
   //post: plays the specific note of the desired patch
   public void playNote(int pitch) {
      int index = pitch + 24;
      if(index >= 0 && index < KEYBOARD.length()){
         strings[index].pluck();
      }
   }
   
   //post: confirms the desired character has a matching string
   public boolean hasString(char key) {
      return (KEYBOARD.indexOf(key) != -1);
   }
   
   //pre: hasString(key)(throws IllegalArgumentException if not)
   //post: updates the ring buffer values with ramdom values from -0.5(inclusive) 
   //      to 0.5(exclusive) 
   public void pluck(char key) {
      if(!hasString(key)){
         throw new IllegalArgumentException();
      }
      int index = KEYBOARD.indexOf(key);
      strings[index].pluck();
   }
   
   //post: returns the sum of all ring buffer value of the strings
   public double sample() {
      double result = 0.0;
      for(int i = 0; i < strings.length; i++){
         result += strings[i].sample();
      }
      return result;
   }
   
   //post: changes the ring buffer value to simulate a Karplus-Strong update
   public void tic() {
      time++;
      for(int i = 0; i < strings.length; i++){
         strings[i].tic();
      }
   }
   
   //post: returns the times tic is updated
   public int time() {
      return time;
   } 
   
   //post: returns the frequency of a desired index
   private double frequency(int index) {
      return 440 * Math.pow(2, (index - 24) / 12.0);
   }
   
}
