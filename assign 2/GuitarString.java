// Parren Chen
// 4/16/19
// CSE 143 BH 
// TA: RanDair Porter
// Homework 2
// import the java util package
// The GuitarString models a single guitar string and simulate its sound.

import java.util.*;

public class GuitarString {
   
   //representing the decay factor of the sound is 0.996
   public static final double DECAY_FACTOR = 0.996;
   //the ring buffer feedback of the sound corresponding to the sound decaying
   private Queue<Double> ringBuffer;
  
   // pre: frequency > 0 and size >= 2(throws IllegalArgumentException if not)
   // post: constructs the string of a given frequency, initialize its state 
   //       at rest
   public GuitarString(double frequency) {
      int size = ((int)Math.round(StdAudio.SAMPLE_RATE / frequency));
      if ((frequency <= 0.0) || (size < 2)) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < size; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   //pre: init.length >= 2(throws IllegalArgumentException if not)
   //post: constructs the string with the same ring buffer value to the given array
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (double num : init) {
         ringBuffer.add(num);
      }
   }
  
   //post: updates the ring buffer values with ramdom values from -0.5(inclusive) 
   //      to 0.5(exclusive)
   public void pluck() {
      for (int i = 0; i < ringBuffer.size(); i++) {
         ringBuffer.remove();
         ringBuffer.add(Math.random() - 0.5);
      }
   }
   
   //post: changes the ring buffer value to simulate a Karplus-Strong update
   public void tic() {
      double current = ringBuffer.remove();
      ringBuffer.add(formula(current + ringBuffer.peek()));
   }
   
   //post: returns the current ring buffer value at the front
   public double sample() {
      return ringBuffer.peek();
   }
   
   //post: returns the value after applying the decay factor formula with the sum
   private double formula(double sum) {
      return DECAY_FACTOR * 0.5 * sum;
   }
   
}