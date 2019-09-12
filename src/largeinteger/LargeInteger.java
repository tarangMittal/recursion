package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits)
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		for(int i=0; i<input.length();i++) {
			head = new LLNode<Integer>(Integer.parseInt(input.substring(i, i + 1)),head);
			size++;
		}
		
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		if(head==null) {
				return null;	
				}
		if(size==1) {
			head.data=0;
			return this;
		}
		else {
			this.head = head.link;
			size--;
			return this;
			}
		
		
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		if(head==null) {
			return null;
		}
		if(head.data.equals(0)) {
			return this;
		}
		else {
			head = new LLNode<Integer>(0,head);
			size++;
			return this;
		}
		
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  carry over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		LLNode<Integer> currThis = this.head;
		LLNode<Integer> currThat = that.head;
		
		int cur =0;
		int carr = 0;
	    int a = 0;
	    int b = 0;
		
		String result = "";
		while(currThis!=null || currThat!=null) {

           if(currThis==null){
				a = 0;
             }
                else{
 				   a = currThis.data;
				}
           
			if(currThat==null){
				b = 0;
             }
                else{
 				   b = currThat.data;
				}
			
			int sum = a+b;
			
			cur = (carr+sum)%10;
			carr = (carr+sum)/10;
			result = cur+result;
			

           if(currThis==null){

				currThis=null;
             }
                else{
 				   currThis = currThis.link;
				}
			if(currThat==null){

				currThat=null;
             }
                else{
 				   currThat = currThat.link;
				}

			
		}
		
		if(carr>0) {
			result = carr + result;
		}
		LargeInteger sum = new LargeInteger(result);
		
		return sum;
	}
	
	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		
		
		if(x==0) {
			return new LargeInteger("0");
		}
		
		else {
			LargeInteger a = new LargeInteger(this.toString());
			LargeInteger b = new LargeInteger("0");
			
			while(x>0) {
				b = b.add(a);
				x--;
			}
		   return b;
		}
		
		
	}

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
		if(curr_node==null) {
			return "";
		}
		else {
			return toString(curr_node.link) + curr_node.data;
		}
		
	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
}
