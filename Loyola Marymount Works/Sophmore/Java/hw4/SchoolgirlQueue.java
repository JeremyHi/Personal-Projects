import java.util.ArrayList;

public class SchoolgirlQueue implements Queue {

	private ArrayList<Schoolgirl> girls;

	private static final int FIRST_INDEX = 0;

	/**
	 * rflag represents whether or not one loop has tried every Schoolgirl in
	 * the queue. This is the responsibility of whoever is using the Queue.
	 */
	private boolean rflag;

	/**
	 * Default constructor. Generally should not be used
	 */
	public SchoolgirlQueue() {
		girls = new ArrayList<Schoolgirl>();
	}

	/**
	 * Creates a SchoolgirlQueue to a specific size and fills it with unique
	 * Schoolgirl objects
	 * 
	 * @param size
	 *            The size of the queue (and thereby the number of Schoolgirls
	 *            created)
	 */
	public SchoolgirlQueue(int size) {
		girls = new ArrayList<Schoolgirl>();

		for (int i = 0; i < size; i++) {
			girls.add(new Schoolgirl(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.lmu.cs.ksutton.hw4.Queue#dequeue()
	 */
	public Schoolgirl dequeue() {
		return girls.remove(FIRST_INDEX);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.lmu.cs.ksutton.hw4.Queue#enqueue(java.lang.Object)
	 */
	public void enqueue(Object item) {
		assert (item instanceof Schoolgirl);
		girls.add((Schoolgirl) item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.lmu.cs.ksutton.hw4.Queue#isEmpty()
	 */
	public boolean isEmpty() {
		return girls.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.lmu.cs.ksutton.hw4.Queue#peek()
	 */
	public Schoolgirl peek() {
		return girls.get(FIRST_INDEX);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.lmu.cs.ksutton.hw4.Queue#size()
	 */
	public int size() {
		return girls.size();
	}

	/**
	 * Rotates the first element to the back
	 */
	public void rotate() {
		girls.add(girls.remove(FIRST_INDEX));
	}

	public void setFlag(boolean b) {
		rflag = b;
	}

	public boolean getFlag() {
		return rflag;
	}
}