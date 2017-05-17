public class Schoolgirl {

	private final int index;

	public Schoolgirl(int i) {
		index = i;
	}

	public int getIndex() {
		return index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(index);
	}
}