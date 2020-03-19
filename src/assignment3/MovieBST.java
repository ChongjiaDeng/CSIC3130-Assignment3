package assignment3;

/* Class: CISC 3130
* Section: MY9
* EmplId: 23402081
* Name: Chongjia Deng
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//----------------------arraylist of movie ------------//
	class Movie{   // we create a Movie class prepare to convert array list in to read CSV file.
		 private int position; 	// the index of each movie of the play list.
		 private String title; // the title is String.
		 private String genres; //   the name of genres is String.
		 
		 public static int nElems = 0;  // Initialize the text line.

		 public Movie(int position, String title,String genres) { // A constructor for the Movie array list.
		 this.position = position;  
		 this.title = title;
		 this.genres = genres;
		 nElems++;
		}
		public void setPosition(int position) {// set value to the position.
			this.position = position;
		}
		public int getPosition() {
			return position;			// a method return the index of Movie.
		}
		public void setTitle(String title) { // set the title of the Movie to the array list
			this.title = title;
		}
		public String getTitle() { // a method return the title of the Movie.
			return title;
		}
		public void setGenres(String genres) {  // set the name of the genres to the array list.
			this.genres = genres;
		}	
	}
//----------------Tree Node constructor-----------------//
public class Node{
	public int iData;              // data item (key)
	public String title;           // data title
	public String genres;
	public Node leftChild;         // this node's left child
	public Node rightChild;        // this node's right child

	public void displayNode()      // display ourself
	 {
	 System.out.print('{');
	 System.out.print(iData);
	 System.out.print(", ");
	 System.out.print(title);
	 System.out.print(", ");
	 System.out.print(genres);
	 System.out.print("} ");
	 }
	public Node getLeft() {
		return leftChild;
	}
	
	public String getTitle() {
		
		return getTitle();
	}
	public Node getRight() {
		
		return rightChild;
	}
	public void setRight(Movie right) {
		this.rightChild = rightChild;	
	}
	public void setLeft(Movie left) {
		this.leftChild = leftChild;	
	}
	
	}  // end class Node
//-----------------BST ------------------------------------//
public class MovieBST {
	private Node root;  // first node of tree
	public int node = 0;

	public Node getRoot() {
		return root;
	}
	public MovieBST() {                 // constructor
     root = null; }            // no nodes in tree yet
//------------------insert method ------------------//
	public void insert(int id, String dd,String cc)
	 {
	 Node newNode = new Node();    // make new node
	 newNode.iData = id;           // insert data
	 newNode.title = dd;
	 newNode.genres = cc;
	 node++ ;
	 if(root==null)                // no node in root
	    root = newNode;
	 else                          // root occupied
	    {
	    Node current = root;       // start at root
	    Node parent;
	    while(true)                // (exits internally)
	       {
	       parent = current;
	       if(id < current.iData)  // go left?
	          {
	          current = current.leftChild;
	          if(current == null)  // if end of the line,
	             {                 // insert on left
	             parent.leftChild = newNode;
	             return;
	             }
	          }  // end if go left
	       else                    // or go right?
	          {
	          current = current.rightChild;
	          if(current == null)  // if end of the line
	             {                 // insert on right
	             parent.rightChild = newNode;
	             return;
	             }
	          }  // end else go right
	       }  // end while
	    }  // end else not root
	 }  // end insert()
//---------------------------delete method----------------------//	
	public boolean delete(int key) // delete node with given key
	 {                           // (assumes non-empty list)
	 Node current = root;
	 Node parent = root;
	 boolean isLeftChild = true;
	 while(current.iData != key)        // search for node
	    {
	    parent = current;
	    if(key < current.iData)         // go left?
	       {
	       isLeftChild = true;
	       current = current.leftChild;
	       }
	    else                            // or go right?
	       {
	       isLeftChild = false;
	       current = current.rightChild;
	       }
	    if(current == null)             // end of the line,
	       return false;                // didn't find it
	    }  // end while
	 // found node to delete

	 // if no children, simply delete it
	 if(current.leftChild==null &&
	                              current.rightChild==null)
	    {
	    if(current == root)             // if root,
	       root = null;                 // tree is empty
	    else if(isLeftChild)
	       parent.leftChild = null;     // disconnect
	    else                            // from parent
	       parent.rightChild = null;
	    }
	 // if no right child, replace with left subtree
	 else if(current.rightChild==null)
	    if(current == root)
	       root = current.leftChild;
	    else if(isLeftChild)
	       parent.leftChild = current.leftChild;
	    else
	       parent.rightChild = current.leftChild;

	 // if no left child, replace with right subtree
	 else if(current.leftChild==null)
	    if(current == root)
	       root = current.rightChild;
	    else if(isLeftChild)
	       parent.leftChild = current.rightChild;
	    else
	       parent.rightChild = current.rightChild;

	 else  // two children, so replace with inorder successor
	    {
	    // get successor of node to delete (current)
	    Node successor = getSuccessor(current);

	    // connect parent of current to successor instead
	    if(current == root)
	       root = successor;
	    else if(isLeftChild)
	       parent.leftChild = successor;
	    else
	       parent.rightChild = successor;

	    // connect successor to current's left child
	    successor.leftChild = current.leftChild;
	    }  // end else two children
	 // (successor cannot have a left child)
	 return true;                                // success
	 }  // end delete()
//---------------get Successor--------//
	private Node getSuccessor(Node delNode)
	 {
	 Node successorParent = delNode;
	 Node successor = delNode;
	 Node current = delNode.rightChild;   // go to right child
	 while(current != null)               // until no more
	    {                                 // left children,
	    successorParent = successor;
	    successor = current;
	    current = current.leftChild;      // go to left child
	    }
	                                      // if successor not
	 if(successor != delNode.rightChild)  // right child,
	    {                                 // make connections
	    successorParent.leftChild = successor.rightChild;
	    successor.rightChild = delNode.rightChild;
	    }
	 return successor;
	 }
///---------Display the tree---------//	
	public void displayTree()
	 {
	 Stack globalStack = new Stack();
	 globalStack.push(root);
	 int nBlanks = 32;
	 boolean isRowEmpty = false;
	 System.out.println(
	 "......................................................");
	 while(isRowEmpty==false)
	    {
	    Stack localStack = new Stack();
	    isRowEmpty = true;

	    for(int j=0; j<nBlanks; j++)
	       System.out.print(' ');

	    while(globalStack.isEmpty()==false)
	       {
	       Node temp = (Node)globalStack.pop();
	       if(temp != null)
	          {
	          System.out.print(temp.iData);
	          localStack.push(temp.leftChild);
	          localStack.push(temp.rightChild);

	          if(temp.leftChild != null ||
	                              temp.rightChild != null)
	             isRowEmpty = false;
	          }
	       else
	          {
	          System.out.print("--");
	          localStack.push(null);
	          localStack.push(null);
	          }
	       for(int j=0; j<nBlanks*2-2; j++)
	          System.out.print(' ');
	       }  // end while globalStack not empty
	    System.out.println();
	    nBlanks /= 2;
	    while(localStack.isEmpty()==false)
	       globalStack.push( localStack.pop() );
	    }  // end while isRowEmpty is false
	 System.out.println(
	 "......................................................");
	 }  // end displayTree()
//------------------------subSetRec-------------------------//
	   public void subSetRec(Set<String> s, Node node, String start, String end) {  
			 
	       if (node == null) {	return;
}
	       if (start.compareTo(node.getTitle()) < 0) {
	           subSetRec(s, node.getLeft(), start, end);	 }
	       if (start.compareTo(node.getTitle()) <= 0 && end.compareTo(node.getTitle()) >=0) {
	           s.add(node.getTitle());
}
	if (end.compareTo(node.getTitle()) > 0) {
	   subSetRec(s, node.getRight(), start, end);
}
}  
//-------------------------subSet method--------------//	   
	   public Set<String> subSet(String start, String end) {
	       // Create a new Set and fill it recursively
	       Set<String> s = new HashSet<String>();
	       subSetRec(s, this.root, start, end);
	       return s;
	   }
	   public Movie getFirst() {
	       return root;
	   }
	   public void setFirst(Node first) {
	       this.root = first;
	   } 
	   
//------------------------add node to BST -----------------//
	   public void addNode(String title, int releaseYear, int movieId, String[] genres) {
	       // Fill the info for movie
	       this.root = addNodeRec(this.root, title);
	       this.root.setReleaseYear(releaseYear);
	       this.root.setMovieId(movieId);
	       this.root.setGenres(genres);
	   }
// ------------add node and conpare to current node -------------------//
	   public Movie addNodeRec(Node root, String title,String genres) {
 // Base case
if (root == null) {
	root = new Movie(node, title, genres);
	return root;
	}
	       if (title.compareTo(root.getTitle()) < 0) {
	    	   root.setLeft(addNodeRec(root.getLeft(), title));
	       }
	else if (title.compareTo(root.getTitle()) > 0) {
		root.setRight(addNodeRec(root.getRight(), title));
	}
	return root;
	}
// ---------------traverseInOrder -------------------//
	   public void traverseInOrder() {
	       traverseInOrderRec(this.root);
	   }
// ---------------traverseInOrderRec -------------------//
	   public void traverseInOrderRec(Movie root) {
	  
	if (root.getLeft() != null) {
	traverseInOrderRec(root.getLeft());
	}
	System.out.println(root.getTitle());
	if (root.getRight() != null) {
	traverseInOrderRec(root.getRight());
	}
	}     
	}
//-------------main app-------------//
public class MovieApp {
	public  ArrayList readFiled(String fileName) throws FileNotFoundException, IOException { 
		// a method read CSV file in to the array list.
	   String name = fileName;
	   ArrayList Movies = new ArrayList<>(); 
	   BufferedReader br = new BufferedReader(new FileReader(name)); //setup a scanner
	   String line = br.readLine(); 
	    while((line = br.readLine())!= null && !line.isEmpty()){ // // while next line in CSV file is not empty.
	    String[] fields = line.split(","); // Read line in from file until see the "comma".
	    String index = fields[0];   //copy the content of the array to the String.
	    String title = fields[1];
	    String genres = fields[2];

	    int position = Integer.parseInt(index);  // convert the String to number. because the position and streaming is integer.
	    MovieBST theTree = new MovieBST();
	    Movie list = new Movie (position, title, genres); 
	    theTree.insert(position, title,genres);
	    // using the constructor which we set before, arrayList
	    Movies.add(list);  // all information adding to the list. then read next line. 
	    }
	    br.close();
	    return Movies;
	   	}
	public static void main(String[]args) {
		public void read(String filename) {
			 
			 try {
				
				ArrayList read = readFiled(filename);
				MovieBST theTree = new MovieBST();
				theTree.displayTree();
			
			}  catch(Exception e) {
	        	System.out.println(e);// if does not exit the CSV file.
			}
		 }
			public static void main(String[]args) {	
				String name = "movies.csv";

				MyApp test = new MovieApp();
				test.read(name);		
	}
	}
	}	