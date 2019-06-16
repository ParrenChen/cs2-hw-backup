// Parren Chen
// CSE 143 BH 
// TA: RanDair Porter
// Homework 7
// The QuestionNode constructs an object that stores a String content and linked
// to other left or right QuestionNode object for each yes/no response

public class QuestionNode{
   
   // the String content of the node
   public String content;
   // the left sub QuestionNode tree, representing "yes" answer
   public QuestionNode left;
   // the right sub QuestionNode tree, representing "no" answer
   public QuestionNode right;
              
   // post: constructs a leaf node with given String of content
   public QuestionNode(String content) {
      this(content, null, null);
   }
                        
   // post: constructs a branch node with given String of data, left subtree,
   //       right subtree
   public QuestionNode(String content, QuestionNode left, QuestionNode right) {
      this.content = content;
      this.left = left;
      this.right = right;
   }
       
}