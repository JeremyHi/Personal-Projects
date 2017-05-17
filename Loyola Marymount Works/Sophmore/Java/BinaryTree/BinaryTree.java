import java.util.*;

public class BinaryTree implements Collection {

	private static Object object;
  private static int size;
  private static BinaryTree leftSubtree;
  private static BinaryTree rightSubtree;
	private static BinaryTree parentTree;

	/** Constructs an empty tree */
	public BinaryTree() {
		this.object = null;
    this.size = 0;
    this.leftSubtree = null;
  	this.rightSubtree = null;
    this.parentTree = null;
	}

	/** Constructs a tree with just a root node decorated with 
	(i.e., referring to) obj */
	public BinaryTree(Object obj) {
		this.object = obj;
    this.size = 1;
    this.leftSubtree = null;
    this.rightSubtree = null;
    this.parentTree = null;
	} 

	/** Re-initializes this to an empty binary tree. */	
   	public void clear() {
      this.object = null;
      this.size = 0;
      this.leftSubtree = null;
      this.rightSubtree = null;
      this.parentTree = null;
      System.gc();
   	}

	/** Returns true iff the tree contains an object equivalent 
	to obj */
	public boolean contains(Object obj) {
		for (Object o : this) {
      if (o.equals(obj)) {
        return true;
      }
    }
    return false;
	}

	/** Returns true iff obj is an equivalent binary tree- i.e., 
	obj must have identical structure and equivalent objects */
	public boolean equals(Object obj) {
		if(!(obj instanceof BinaryTree) || this.size != ((BinaryTree)obj).size) {
      return false;
    } else {
      BinaryTree treeToCompare = (BinaryTree)obj;
      return (this.similar(treeToCompare.object) && this.leftSubtree.similar(treeToCompare.leftSubtree) && this.rightSubtree.similar(treeToCompare.rightSubtree));  
    }
	}

	private boolean simTreeChecker(BinaryTree leftTree, BinaryTree rightTree) {
    if (leftTree == null && rightTree == null)
      return true;
    if ((leftTree == null && rightTree != null) || (leftTree != null && rightTree == null))
      return false;
    return (simTreeChecker(leftTree.leftSubtree, rightTree.leftSubtree) && simTreeChecker(leftTree.rightSubtree, rightTree.rightSubtree));
  }


	/** Returns true iff obj is a similar binary tree- i.e., 
	obj must have identical structure (only) */
	private boolean similar(Object obj) {
    	if (this.size() != ((BinaryTree)obj).size())
    		return false;
    	else if (this.size() == 0 && ((BinaryTree)obj).size() == 0) 
    		return true;
    	return simTreeChecker(this.parentTree, ((BinaryTree)obj).parentTree);
   	}

   	/** Returns a hashcode for this binary tree. */
   	public int hashCode () {
      	int result = 0;
      	int counter = 1001;
      	for(Object o : this){
         	result += o.hashCode() % counter;
         	counter ++;
      	}
      	return result;
   	}

	/** Returns false if this is an empty tree */
	public boolean putCursorAtRoot() {
		if (this.size != 0) {
           	object = parentTree;
           	return true;
        }
		return false;
	}

	/** Returns false if there is no left son */
	public boolean putCursorAtLeftSon() {
		if (this.leftSubtree != null) {
			object = leftSubtree;
			return true;
		}
		return false;
	}

	/** Returns false if there is no left son */
	public boolean putCursorAtRightSon() {
		if (this.rightSubtree != null) {
			object = rightSubtree;
			return true;
		}
		return false;
	}

	/** Returns false if there is no father */
	public boolean putCursorAtFather() {
		if (!parentTree.isEmpty()) {
			object = parentTree;
			return true;
		}
		return false;
	}

	/** Returns false if a left son already exists */
	public boolean attachLeftSonAtCursor(Object obj) {
		if (leftSubtree != null) {
			leftSubtree.add(obj);
			return true;
		}
		return false;
	}

	/** Returns false if a left son already exists */
	public boolean attachRightSonAtCursor(Object obj) {
		if (rightSubtree != null) {
			rightSubtree.add(obj);
			return true;
		}
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
		if (leftSubtree != null) {
			return true;
		}
		return false;
	}

	public boolean hasRight() {
		if (rightSubtree != null) {
			return true;
		}
		return false;
	}

   	public boolean add(Object obj) {
      	return this.supremeAdd(obj, true);
   	}

   	private boolean supremeAdd (Object obj, boolean addToLeft) {
      	if (this.size == 0) {
        	this.object = obj;
      	} else {
        	BinaryTree duplicateTree = new BinaryTree(this.object);
        	duplicateTree.leftSubtree = this.leftSubtree;
        	if (duplicateTree.leftSubtree != null) {
            	duplicateTree.leftSubtree.parentTree = duplicateTree;
         	}
         	duplicateTree.rightSubtree = this.rightSubtree;
         	if (duplicateTree.rightSubtree != null) {
            	duplicateTree.rightSubtree.parentTree = duplicateTree;
         	}
         	if (addToLeft) {
            	this.leftSubtree = duplicateTree;
            	this.rightSubtree = null;
         	} else {
            	this.leftSubtree = null;
            	this.rightSubtree = duplicateTree;
         	}
         	duplicateTree.parentTree = this;
         	this.object = obj;
      	}
      	this.size++;
      	return true;
   	}

   	public boolean containsAll ( Collection c ) {
      	throw new UnsupportedOperationException();
   	}

   	/** Throws an UnsupportedOperationException(). */
  	public boolean addAll ( Collection c ) {
      	throw new UnsupportedOperationException();
   	}

   	/** Throws an UnsupportedOperationException. */
   	public boolean removeAll ( Collection c ) {
      	throw new UnsupportedOperationException ();
   	}

   	/** Throws an UnsupportedOperationException(). */
   	public boolean retainAll ( Collection c ) { // overrides Collection
      	throw new UnsupportedOperationException ();
   	}

   	/** Throws an UnsupportedOperationException(). */
   	public Object[] toArray ( Object[] a ) {
      	throw new UnsupportedOperationException ();
    }
   	
   	/** Throws an UnsupportedOperationException(). */
   	public Object[] toArray () {
      	throw new UnsupportedOperationException ();
   	}

   	public boolean remove ( Object obj ) {
    	throw new UnsupportedOperationException();
   	}

   	/** Returns the number of nodes in this binary tree. */
   	public int size () {
      	return this.size;
   	}

	/** Should do the obvious thing- and the same for public int 
	size() and public int hashCode() */
	public boolean isEmpty() {
		return (this.size == 0);
	}
	/** Should return a preorder iterator over the tree */
	public Iterator iterator() {
    	return new PreorderIterator(this);
  	}

  	/** Returns an inorder iterator for this binary tree */
   	public Iterator InOrder () {
      	return new InOrder(this);
   	}

/**

*/

	private class PreorderIterator implements Iterator {
    	private Stack<BinaryTree> stack;
    	private BinaryTree preOrderBinTree;
    	private BinaryTree currentTree;

    	private PreorderIterator (BinaryTree preOrderBinTree) {
        	this.stack = new Stack<BinaryTree>();
        	this.preOrderBinTree = preOrderBinTree;
        	this.currentTree = null;
    	}
      	
      	
		public boolean hasNext () {
         	if (preOrderBinTree.size == 0) {
         		return false;
         	}
         	if (this.currentTree == null || this.currentTree.leftSubtree != null || this.currentTree.rightSubtree != null || !this.stack.empty()) {
            	return true;
         	} 

         	return false;
      	}

      	public Object next () {
         	if (this.currentTree == null) {
            	this.currentTree = this.preOrderBinTree;
         	} else {
            	if (this.currentTree.rightSubtree != null) {
               		this.stack.push(this.currentTree.rightSubtree);
            	}

            	if (this.currentTree.leftSubtree != null) {
               	this.currentTree = this.currentTree.leftSubtree;
            	} else if (this.currentTree.leftSubtree == null) {
               		this.currentTree = this.stack.pop();
            	} else if (this.currentTree.leftSubtree == null && this.currentTree.rightSubtree == null) {
               		this.currentTree = this.stack.pop();
            	} 
         	}
         	return this.currentTree.object;
        }

		public void remove () {
			throw new UnsupportedOperationException();
      	}
   	}
/**

*/
   	public class InOrder implements Iterator {
      	private Stack<BinaryTree> stack;
      	private BinaryTree currentTree;
      	private BinaryTree inOrderBinTree;

      	public InOrder (BinaryTree inOrderBinTree) {
         	this.stack = new Stack<BinaryTree>();
         	this.inOrderBinTree = inOrderBinTree;
         	this.currentTree = inOrderBinTree;
      	}

      	public boolean hasNext () {
         	if (inOrderBinTree.size == 0) {
            	return false;
         	}
         	if (this.currentTree != null || !this.stack.empty()) {
            	return true;
         	} 

         	return false;
      	}

      	public Object next () {
        	while (this.currentTree != null) {
            	this.stack.push(this.currentTree);
            	this.currentTree = this.currentTree.leftSubtree;
         	}

         	this.currentTree = this.stack.pop();
         	Object result = this.currentTree.object;
         	this.currentTree = this.currentTree.rightSubtree;
         	return result;
      	}

      	public void remove () {
      		throw new UnsupportedOperationException();
      	}
   	}

/**

*/

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
		System.out.println("Testing Constructor");
		try {
			displaySuccessIfTrue(test.object == null && test.size == 0 && test.leftSubtree == null && test.rightSubtree == null && test.parentTree == null);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			Object testVar = "Eight";
			test2 = new BinaryTree(testVar);
			displaySuccessIfTrue(test2.object == testVar && test2.size == 1 && test2.leftSubtree == null && test2.rightSubtree == null && test2.parentTree == null);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		System.out.println();

		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing contains");
		try {
			Object testVar = "Eight";
			test2 = new BinaryTree(testVar);
			displaySuccessIfTrue(test2.contains(testVar));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		System.out.println();

		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing similar");
		try {
			Object testVar = "Eight";
			test = new BinaryTree(testVar);
			test2 = new BinaryTree(testVar);
			displaySuccessIfTrue(test.similar(test2));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			Object testVar = "Eight";
			Object testVar2 = "Eight";
			test = new BinaryTree(testVar);
			test2 = new BinaryTree(testVar2);
			displaySuccessIfTrue(test.similar(test2));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		test = new BinaryTree();
		test2 = new BinaryTree();
		try {
			Object testVar = "Eight";
			Object testVar2 = "eleven";
			test = new BinaryTree(testVar);
			test2 = new BinaryTree(testVar2);
			displaySuccessIfTrue(test.similar(test2));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		System.out.println();
		
		test = new BinaryTree();
		test2 = new BinaryTree();
		System.out.println("Testing isEmpty");
		try {
			displaySuccessIfTrue(test.isEmpty());
		} catch (Exception e) {
			displaySuccessIfTrue(false);			
		}
		System.out.println();

		System.out.println(successes + "/" + attempts + " tests passed.");
	}
}