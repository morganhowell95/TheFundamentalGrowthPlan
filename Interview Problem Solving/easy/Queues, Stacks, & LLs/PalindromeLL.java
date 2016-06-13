/*Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

Proposed methods:
	1. Calculate the midpoint: O(n) time O(1) space
	2. Reverse list up unto the midpoint: O(n) time O(1) space
	3. Two pointers check equality from midpoint to ends in opposite directions <-> O(n) time O(1) space
	4. Total: O(n) time O(1) space
	--------------------------
	**Recursive implementation is simply for practice, since every recursive call adds to the execution stack
	we would not be dealing with constant space.
*/
import java.util.ArrayList;

public class PalindromeLL<T extends Comparable<T>> {
	public ArrayList<Node<T>> listOfNodes;

	public PalindromeLL() {
		listOfNodes = new ArrayList<Node<T>>();
	}

	/* Calculate number of nodes up unto the midpoint of a linked list
	 *
	 *
	 * @param1: non-empty linked list
	 * @rvalue: mid point object holding parity and the integer representing nodes until midpoint
	 */
	private midPointStruct calculateMidPoint(Node<T> root) {
		int lengthOfList = 0;
		Node<T> tail = root;
		
		//find number of links to the end
		while(tail!=null) {
			tail = tail.next;
			lengthOfList++;
		}
		//reset pointer you used to count to the beginning of the list
		tail = root;
		//if total length is an odd number we increment mid-point up one
		int midpoint;
		boolean isEven;
		if((lengthOfList%2)==1) {
			midpoint = lengthOfList/2;
			isEven = false;
		} else {
			midpoint = lengthOfList/2;
			isEven = true;
		}
		return this.new midPointStruct(midpoint, isEven);
	}

	/* Reverse linked list up unto the given point. Technically we don't need to return mp because it is passed 
	 * as a reference to an object (still pass by value but retains changes done within object),
	 * but we keep it here for good measure (some may call it redundancy).
	 *
	 * @param1: root of the linked list
	 * @param2: midpoint struct that has parity and midpoint count
	 * @rvalue: mid point struct with two nodes representing middle loaded
	 */
	private midPointStruct reverseUpUntoMidPoint(Node<T> root, midPointStruct mp) {
		Node<T> mid = root;
		Node<T> prev = null;

		for(int i=0; i<mp.midpoint;i++) {
			Node<T> temp = mid.next;
			mid.next = prev;
			prev = mid;
			mid = temp;
		}

		if(mp.isEven) {
			mp.lowerHalf = prev;
			mp.upperHalf = mid;
		} else {
			mp.lowerHalf = prev;
			mp.upperHalf = mid.next;
		}
		return mp;
	}
	
	/* Tests to see if the nodes of the linked list make up a palindrome
	 *
	 * @param1: root of the linked list
	 * @rvalue: true if linked list contains palindrome, false otherwise
	 */
	public boolean isPalindromeIterativeConstantSpace(Node<T> root) {
		midPointStruct mp = calculateMidPoint(root);
		mp = reverseUpUntoMidPoint(root, mp);

		Node<T> tail = mp.lowerHalf;
		while(tail != null) {
			tail = tail.next;
		}
		tail = mp.upperHalf;
		while(tail != null) {
			tail = tail.next;
		}

		//ensure that all the characters match from midpoint to the end to ensure string is palindrome
		boolean isPalindrome = true;
		while(mp.lowerHalf!=null && mp.upperHalf!=null) {
			if(!mp.lowerHalf.data.equals(mp.upperHalf.data)){
				isPalindrome = false;
			}
			mp.lowerHalf = mp.lowerHalf.next;
			mp.upperHalf = mp.upperHalf.next;
		}

		return isPalindrome;
	}

	//I'm doing this just for practice, though it's not an efficient solution
	public Node<T> tracker = null;
	public boolean isPalindromeRecursive(Node<T> root, Node<T> last) {
		if(last == null) {
			tracker = root;
			return true;
		}

		boolean isPalindrome = isPalindromeRecursive(root, last.next);

		if(isPalindrome == true) {
			if(last.data.equals(tracker.data)) {
				tracker = tracker.next;
				return true;
			} else if(tracker == last) {
				return true;
			} else {
				return false;
			}
		}

		return isPalindrome;
	}

	//object to keep track of data related to midpoint calculation, including two nodes at the center where
	// the direction of the LL diverge <-X-Y->, X being the lower half and Y being the upper half
	public class midPointStruct {
		public int midpoint;
		public boolean isEven;
		public Node<T> upperHalf;
		public Node<T> lowerHalf;

		public midPointStruct(int midpoint, boolean isEven) {
			this.midpoint = midpoint;
			this.isEven = isEven;
		}
	}

	public static void main(String[] args) throws Exception{

		PalindromeLL<String> p = new PalindromeLL<String>();
		p.listOfNodes.add(new Node<String>("a"));
		p.listOfNodes.add(new Node<String>("t"));
		p.listOfNodes.add(new Node<String>("r"));
		p.listOfNodes.add(new Node<String>("b"));

		Node<String> root = p.listOfNodes.get(0);
		String preEmbed = "anna";
		for(int i=0;i<4;i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}

		root = p.listOfNodes.get(1);
		preEmbed = "ezfxitz";
		for(int i=0;i<7;i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}


		root = p.listOfNodes.get(2);
		preEmbed = "racecar";
		for(int i=0;i<7;i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}

		root = p.listOfNodes.get(3);
		preEmbed = "holaadf9879123b";
		for(int i=0;i<preEmbed.length();i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}

		
		boolean[] testCases = {true, false, true, false};
		for(int i=0;i<p.listOfNodes.size();i++) {
			if(testCases[i] != p.isPalindromeIterativeConstantSpace(p.listOfNodes.get(i))) {
				throw new Exception("test case " + (i+1)  +" failed.");
			}
		}

		root = p.listOfNodes.get(0);
		preEmbed = "anna";
		for(int i=0;i<4;i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}

		root = p.listOfNodes.get(1);
		preEmbed = "ezfxitz";
		for(int i=0;i<7;i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}


		root = p.listOfNodes.get(2);
		preEmbed = "racecar";
		for(int i=0;i<7;i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}

		root = p.listOfNodes.get(3);
		preEmbed = "holaadf9879123b";
		for(int i=0;i<preEmbed.length();i++) {
			root.data = Character.toString(preEmbed.charAt(i));
			if(i+1!=preEmbed.length()) {
				root.next = new Node<String>();
			}
			root = root.next;
		}

		for(int i=0;i<4;i++) {
			if(testCases[i] != p.isPalindromeRecursive(p.listOfNodes.get(i), p.listOfNodes.get(i))) {
				throw new Exception("test case " + (i+1)  +" failed.");
			}
		}

		System.out.println("Cool, you were able to test palindromes held in linked lists");
	
	}

}
