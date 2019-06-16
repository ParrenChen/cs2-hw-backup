// Parren Chen
// CSE 143 BH 
// TA: RanDair Porter
// Homework 8
// The HuffmanNode class constructs a HuffmanNode object that stores a 
// character's representing int value, the freqyency it appears, and linked 
// to other left or right HuffmanNode representing 0 or 1.


public class HuffmanNode implements Comparable<HuffmanNode> {
   
   // the character value
   public int value;
   // the frequency of character appearence
   public int frequency;
   // the left subtree
   public HuffmanNode left;
   // the right subtree
   public HuffmanNode right;
   
   // post: constructs a HuffmanNode with a given value of the character and its frequency
   public HuffmanNode(int value, int frequency) {
      this(value, frequency, null, null);
   }
   
   // post: constructs a HuffmanNode with a given value, given frequency, and given left
   //       and right subtree
   public HuffmanNode(int value, int frequency, HuffmanNode left, HuffmanNode right) {
      this.value = value;
      this.frequency = frequency;
      this.left = left;
      this.right = right;
   }
   
   // post: returns the frequency difference number between current HuffmanNode and a
   //       given other HuffmanNode
   public int compareTo(HuffmanNode other) {
      return this.frequency - other.frequency;
   }

}