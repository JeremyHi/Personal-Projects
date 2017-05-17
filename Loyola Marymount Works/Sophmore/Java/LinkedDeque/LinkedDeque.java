public class LinkedDeque {
	public Object[] deckArray;
	public static int size;

	// default constructor
	public LinkedDeque() {
		size = 0;
		deckArray = new Object[size];
	}

	public LinkedDeque(Object[] obj) {
		size = obj.length;
		deckArray = obj;
	}

	public void insertLeft(Object o) {
		if (!(o instanceof Object)) {
			throw new IllegalArgumentException();
		}
		Object[] newArray = new Object[size + 1];
		newArray[0] = o;
		System.arraycopy(deckArray, 0, newArray, 1, deckArray.length);
		deckArray = newArray;
		size++;
	}

	public void insertRight(Object o) {
		if (!(o instanceof Object)) {
			throw new IllegalArgumentException();
		}
		Object[] newArray = new Object[size + 1];
		newArray[newArray.length - 1] = o;
		System.arraycopy(deckArray, 0, newArray, 0, deckArray.length);
		deckArray = newArray;
		size++;
	}

	public void deleteLeft() {
		if (size == 0) {
			throw new UnsupportedOperationException("Cannot delete a size of 0.");
		}
		Object[] newArray = new Object[size - 1];
		System.arraycopy(deckArray, 1, newArray, 0, (deckArray.length - 1));
		deckArray = newArray;
		size--;
	}

	public void deleteRight() {
		if (size == 0) {
			throw new UnsupportedOperationException("Cannot delete a size of 0.");
		}
		Object[] newArray = new Object[size - 1];
		System.arraycopy(deckArray, 0, newArray, 0, (deckArray.length - 1));
		deckArray = newArray;
		size--;
	}

	// returns the left element without modifiying the deque
	public Object left() {
		if (size == 0) {
			throw new UnsupportedOperationException("Cannot return left of Deque size of 0.");
		}
		return deckArray[0];
	}

	// returns the right element without modifiying the deque
	public Object right() {
		if (size == 0) {
			throw new UnsupportedOperationException("Cannot return right of Deque size of 0.");
		}
		return deckArray[deckArray.length - 1];
	}

	public int size() {
		return size;
	}

	// returns [obj][obj]...[obj]
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		String deckString = "";
		for (int i = 0; i < size; i++) {
			deckString += "[" + deckArray[i] + "]";
		}
		return deckString;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int attempts = 0;
    private static int successes = 0;
	//method to assist in testing carried out in "main"
	private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

	// runs a comprehensive set of unit tests
	public static void main(String[] args) {
		LinkedDeque test1 = new LinkedDeque(new Object[5]);
		LinkedDeque test = new LinkedDeque();
		
		//constructor test
		System.out.println("Testing constructor");
		try {
			displaySuccessIfTrue(test.size == 0);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			displaySuccessIfTrue(test1.deckArray.length == 5);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		System.out.println();

		//insertLeft test
		System.out.println("Testing insertLeft");
		try {
			Object obj = new Object();
			test.insertLeft(obj);
			displaySuccessIfTrue(test.deckArray[0].equals(obj));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			test1 = new LinkedDeque(new Object[5]);
			test1.insertLeft(new Object());
			displaySuccessIfTrue((test1.deckArray.length == 6));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			test1 = new LinkedDeque(new Object[5]);
			int x = 7;
			test1.insertLeft(x);
			displaySuccessIfTrue(test1.deckArray[0] == x); 
		} catch (IllegalArgumentException e) {
			displaySuccessIfTrue(true);
		}
		System.out.println();

		//insertRight Test
		System.out.println("Testing insertRight");
		try{
			Object obj = new Object();
			test.insertRight(obj);
			displaySuccessIfTrue(test.deckArray[size-1].equals(obj));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			test1 = new LinkedDeque(new Object[5]);
			test1.insertRight(new Object());
			displaySuccessIfTrue((test1.deckArray.length == 6));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			test1 = new LinkedDeque(new Object[5]);
			int x = 7;
			test1.insertRight(x);
			displaySuccessIfTrue(test1.deckArray[size-1] == x); 
		} catch (IllegalArgumentException e) {
			displaySuccessIfTrue(true);
		}
		System.out.println();

		test = new LinkedDeque();
		
		//deleteLeft Test
		System.out.println("Testing deleteLeft");
		try {
			test = new LinkedDeque();
			test.deleteLeft();
		} catch (UnsupportedOperationException e) {
			displaySuccessIfTrue(true);
		}
		try {
			test1 = new LinkedDeque(new Object[5]);
			test1.deleteLeft();
			displaySuccessIfTrue(test1.deckArray.length == 4);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			test1 = new LinkedDeque();
			test1.deleteLeft();
			displaySuccessIfTrue(size == 0 );
		} catch (UnsupportedOperationException e) {
			displaySuccessIfTrue(true);
		}
		System.out.println();

		//deleteRight Test
		System.out.println("Testing deleteRight");
		try {
			test = new LinkedDeque();
			test.deleteRight();
		} catch (UnsupportedOperationException e) {
			displaySuccessIfTrue(true);
		}
		try {
			test1 = new LinkedDeque(new Object[5]);
			test1.deleteRight();
			displaySuccessIfTrue(test1.deckArray.length == 4);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		try {
			test1 = new LinkedDeque();
			test1.deleteRight();
			displaySuccessIfTrue(size == 0 );
		} catch (UnsupportedOperationException e) {
			displaySuccessIfTrue(true);
		}
		System.out.println();

		//Left Test
		System.out.println("Testing left");
		try {
			test.insertLeft(new Object());
			displaySuccessIfTrue(test.left() == test.deckArray[0]);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		System.out.println();

		//Right Test
		System.out.println("Testing right");
		try {
			displaySuccessIfTrue(test.right() == (test.deckArray[size - 1]));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		System.out.println();

		//Size Test
		System.out.println("Testing size");
		try {
			displaySuccessIfTrue(test.size() == size);
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}
		System.out.println();

		//toString Test
		System.out.println("Testing toString");
		try{
			test1 = new LinkedDeque(new Object[5]);
			String test1String = test1.toString();
			displaySuccessIfTrue(test1String.equals("[null][null][null][null][null]"));
		} catch (Exception e) {
			displaySuccessIfTrue(false);
		}

		System.out.println(successes + "/" + attempts + " tests passed.");
	}
}