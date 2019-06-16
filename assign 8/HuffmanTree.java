// Parren Chen
// CSE 143 BH 
// TA: RanDair Porter
// Homework 8
// The HuffmanTree constructs an object that allows the user to compress a text
// file using the Huffman algorithm. It saves the code pattern to a code file and
// be able to decode the original text based on the code file.

import java.util.*;
import java.io.*;

public class HuffmanTree{
   
   // the overall root of the HuffmanTree
   private HuffmanNode overallRoot;
   
   // post: constructs a HuffmanTree object with the given count of frequency of 
   //       characters
   public HuffmanTree(int[] count) {
      Queue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>();
      for(int i = 0; i < count.length; i++){
         if(count[i] != 0){
            HuffmanNode current = new HuffmanNode(i, count[i]);
            queue.add(current);
         }
      }
      // the eof symbol always have the occurance of 1
      HuffmanNode eof = new HuffmanNode(count.length, 1);
      queue.add(eof);
      buildTree(queue);
   }
   
   // post: builds the HuffmanTree structure with a given queue
   private void buildTree(Queue<HuffmanNode> queue){
      if(queue.size() > 1){
         HuffmanNode left = queue.remove();
         HuffmanNode right = queue.remove();
         HuffmanNode newNode = new HuffmanNode(-1, 
                           left.frequency + right.frequency, left, right);
         queue.add(newNode);
         buildTree(queue);
      } else { //queue.size() == 1
         overallRoot = queue.remove();
      }
   }
   
   // pre: the input data is in a legal, valid, standard format
   // post: constructs a new HuffmanTree with a given coding file in a Scanner
   public HuffmanTree(Scanner input) {
      while (input.hasNextLine()) {
         int value = Integer.parseInt(input.nextLine());
         String sign = input.nextLine();
         overallRoot = scannerHelper(overallRoot, value, sign);
      }
   }
   
   // post: constructs the HuffmanTree with the given current HuffmanNode, the 
   //       current value and the route sign of 0 or 1
   private HuffmanNode scannerHelper(HuffmanNode current, int value, String sign) {
      if(current == null && sign.length() == 0){
         current = new HuffmanNode(value, -1); 
         return current;
      } else if(current == null){
         current = new HuffmanNode(0, 1);
      }
      char first = sign.charAt(0);
      sign = sign.substring(1);
      if(first == '0'){
         current.left = scannerHelper(current.left, value, sign);
      } else {
         current.right = scannerHelper(current.right, value, sign);
      }
      return current;
   }
   
   // post: stores the current huffman coding to a standard format file, 
   //       the first line with character number and the second line with
   //       specific binary code
   public void write(PrintStream output) {
      writeHelper(output, overallRoot, "");
   }
   
   // post: helper method that stores the current HuffmanNode information and 
   //       other further subnodes.
   private void writeHelper(PrintStream output, HuffmanNode current, String sign){
      if(current.right == null && current.left == null){
         output.println(current.value);
         output.println(sign);
      } else {
         writeHelper(output, current.left, sign + "0");
         writeHelper(output, current.right, sign + "1");
      }
   }
   
   // pre: the encoding of characters in the BitInputStream is legal
   // post: reads the bits from the given input of BitInputStream, and stores the 
   //       output to a new file through the PrintStream, stops at the given value
   //       of eof
   public void decode(BitInputStream input, PrintStream output, int eof) {
      int route = input.readBit();
      while(route != -1){
         route = decodeHelper(input, output, overallRoot, route, eof);
      }
   }      

   // post: returns the number of either 0 or 1 to trace to find a character,
   //       returns -1 if encounters the eof end sign
   private int decodeHelper(BitInputStream input, PrintStream output, 
                                 HuffmanNode current, int route, int eof) {
      if(current.value != eof){
         if(current.left == null && current.right == null){
            output.write(current.value);
            return route;
         } else if(route == 0){
            return decodeHelper(input, output, current.left, input.readBit(), eof); 
         } else { //route == 1
            return decodeHelper(input, output, current.right, input.readBit(), eof);
         }
      }
      return -1;
   }

}