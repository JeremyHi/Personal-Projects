import java.util.*;

public class BinaryTreeCopy {
	private Object data;
	private BinaryTreeCopy left, right;

	/** Constructs an empty tree */
	public BinaryTreeCopy() {
		this.data = null; 
		this.left = null; 
		this.right = null;
	}

	/** Constructs a tree with just a root node decorated with 
	(i.e., referring to) obj */
	public BinaryTreeCopy(Object obj) {
		this.data = obj; 
		this.left = null; 
		this.right = null;
	} 

	/**
	 * Constructs inner nodes
	 */
	public BinaryTreeCopy(Object data, BinaryTree left, BinaryTree right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/** Returns true iff the tree contains an object equivalent 
	to obj */
	public boolean contains(Object obj) {
		if (left.contains(obj) || right.contains(obj)) {
			return true;
		}
		return false;
	}

	/** Returns true iff obj is a similar binary tree- i.e., 
	obj must have identical structure (only) */
	public boolean similar(Object obj) {
		
		return false;
	} 

	/** Returns true iff obj is an equivalent binary tree- i.e., 
	obj must have identical structure and equivalent objects */
	public boolean equals(Object obj) {
		if(!(obj instanceof BinaryTreeCopy))
			return false;
		BinaryTreeCopy tempTree = (BinaryTreeCopy) obj;
		if (hasLeft() != tempTree.hasLeft() || hasRight() != tempTree.hasRight()) {
			return false;
		}
		if (!data.equals(tempTree.data)) {
			return false;
		}
		if (hasLeft() && !left.equals(tempTree.left)) {
			return false;
		}
		if (hasRight() && !right.equals(tempTree.right)) {
			return false;
		}
		return true;
	} 

	/** Should do the obvious thing- and the same for public int 
	size() and public int hashCode() */
	public boolean isEmpty() {
		
		return false;
	}

	/** Should return a preorder iterator over the tree */
	public Iterator iterator() {
		
		return null;
	}

	/** Should return an inorder iterator over the tree */
	public Iterator inOrder() {
		
		return null;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
	//root node == top node of tree
	//inner node == has children
	//outer node/leaf node == no children


	/** Returns false if this is an empty tree */
	public boolean putCursorAtRoot() {
		

		return true;
	}

	/** Returns false if there is no left son */
	public boolean putCursorAtLeftSon() {
		if (left == null) {
			return false;
		}


		return true;
	}

	/** Returns false if there is no right son */
	public boolean putCursorAtRightSon() {
		if (right == null) {
			return false;
		}


		return true;
	}

	/** Returns false if there is no father */
	public boolean putCursorAtFather() {

		return false;
	}

	/** Returns false if a left son already exists */
	public boolean attachLeftSonAtCursor(Object obj) {
		
		return false;
	}

	/** Returns false if a right son already exists */
	public boolean attachRightSonAtCursor(Object obj) {
		
		return false;
	}

	/** Removes everything that descends from the cursor, 
	including the node to which the cursor refers, then 
	relocates the cursor to the root node, returning true 
	iff something (including the cursor) changed */
	public boolean pruneFromCursor() {
		
		return false;
	} 

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}


////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int attempts = 0;
    private static int successes = 0;
	//Method to assist in testing carried out in "main"
	private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    /** Tests the Methods */
	public static void main(String[] args) {
		
		BinaryTree test = new BinaryTree();
		BinaryTree test2 = new BinaryTree();
		System.out.println("Testing BinaryTree()");
		
		System.out.println();

		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing BinaryTree(Object)");
		
		System.out.println();

		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing contains");
		
		System.out.println();

		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing similar");
		
		System.out.println();

		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing equals");
		
		System.out.println();
		
		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing isEmpty");
		
		System.out.println();

		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing iterator");
		
		System.out.println();









		System.out.println(successes + "/" + attempts + " tests passed.");
	}
}


