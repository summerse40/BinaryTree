//package esummersproj3;

import java.util.ArrayList;

/**
 * @author Emily Summers
 * Data Structures And Algorithms 
 * COP 5416/202101
 * Project 3 
 * BinaryTree class that defines the binary tree, encodes and 
 * decodes symbols and their meanings 
 */
public class BinaryTree {
	
	private Node root = null; // root of binary tree
	private int count = 0;  // used for incrementing
	private int dur = 0; // used for incrementing
	
	/**
	 * Class Node - Inner Class 
	 * @author Emily Summers
	 * Defines what a node within the BinaryTree would hold.
	 * It is later used to populate the data structure.
	 */
	private class Node
	{
	    char key; // holds place of + or - in binary tree
	    String word; // word that is represented by symbols 
	    Node left; // left node for when symbol is '-'
	    Node right; // right node for when symbol is '+'
	   
	    /**
	     * Parameterized Constructor 
	     * Sets the parameter values to values 
	     * from the Node Inner Class to be accessed
	     * after traversing the tree
	     * @param kee
	     * @param wrd
	     */
	    public Node(char kee, String wrd){
	    	
	    	this.key = kee; 
	    	this.word = wrd; 
	    	left = right = null; 
	    	};
	    	}
	
	/**
	 * Insert method to insert the symbols and their meaning. 
	 * Method goes to call the insert recursive method where
	 * the tree is further encoded. Sets K to -1 to allow for correct
	 * index placement.
	 * @param key
	 * @param value
	 */
	public void insert( String key, String value) {
		
		int k = -1;
		root = insert(root,key,value, k);
		}
	
	/**
	 * Recursive insert method that determines whether symbols 
	 * encode to the left or to the right depending on what 
	 * the symbols are. '-' goes the the left. '+' goes to 
	 * the right. When node hits a null, a new node is created 
	 * that holds the place of the decoded word. 
	 * @param r the Node
	 * @param x the word
	 * @param v the symbols
	 * @param k the index of the symbols to be examined
	 * @return
	 */
	public Node insert (Node r, String x, String v, int k ) {
		
		int f; // works as a placeholder for k to allow for new node creation
		
		f = k; 
		k = k + 1; 
		
		// this sets f to 0 to allow for the root to be created 
		if ( f == -1) {
			f = 0;
			}
		
		// allows for a new node to be created once null 
		if (r == null) {
			return new Node(v.charAt(f), x);
			}
		
		// allows for right node to be created recursively 
		if (v.charAt(k) == '+') { 
			r.right = insert(r.right, x, v, k);
			}
		
		// allows for left node to be created recursively 
		else {
			r.left = insert(r.left, x, v, k); 
			}
		return r;
		}
	
	/**
	 * Decode method to pass on the symbols to be 
	 * decoded. Inserts -1 as an index to the recursive 
	 * method decoded. 
	 * @param code
	 */
	public void decode(String code) {
		
		int t = 0; // used as index to later increment through counting arrayList
		
		// set t to dur if not equal to zero
		if (dur != 0) {
			t = dur;
		}
		
		int a = -1; 
		
		decode(root, code, a, t );
		}
	
	/**
	 * Recursive function decode that searches 
	 * binary tree to find the following decoded word. Uses a as 
	 * an index to go through symbols to be translated and determines whether 
	 * to branch to a left or a right node. Once symbols length has been 
	 * indexed through, function prints out the word at that node. 
	 * @param p
	 * @param y
	 * @param a
	 * @return
	 */
	public Node decode(Node p, String y, int a, int t) {
		
		a = a + 1;
        
		// prints out word at the node
		if (a == y.length() ) {
			
            count = count + 1; 
            
			System.out.print(p.word + " ");

			// A way to increment through counting and output a new line depending on word count
			// allows for proper new lines and spaces according encoded.txt 
			if (count >= FileInfo.counting.get(t)) {
				System.out.println(""); 
				dur = dur + 1; 
				count = 0; 
			}

			// input here, about if it succeeds 
			}
		// recursively calls function to go right
		else if (y.charAt(a) == '+') {
			decode (p.right, y, a, t);
			}
		
		// recursively calls function to go left
		else {
			decode (p.left, y, a, t);
			}
		
		return p; 
	}
	
	/**
	 * Main method that calls on functions from FileInfo to read info into files 
	 * and then calls on functions from BinaryTree to further translate the info from 
	 * those files. 
	 * @param args
	 */
	public static void main(String[] args) {

		// making an instance of BinaryTree Class 
		BinaryTree b  = new BinaryTree(); 
		
		// reading in encoding files
		FileInfo.readTranslateInfo(); 
		
		// reading in decoding files
		FileInfo.readMessage();  
		
		// insertion for empty root
		b.insert("node", "node"); 
		
		// calls on arrayLists to insert into function insert parameters
		for (int a = 0; a < FileInfo.wordsMeaning.size(); a ++) {
			b.insert ( FileInfo.wordsMeaning.get(a), FileInfo.wordSymbol.get(a));
			}
		
		// calls on arrayList encoded to insert into function encoded parameters
		for (int k = 0; k < FileInfo.encodedV.size(); k++) {			
			b.decode(FileInfo.encodedV.get(k)); 
			}
		}
	}
		  

	

	