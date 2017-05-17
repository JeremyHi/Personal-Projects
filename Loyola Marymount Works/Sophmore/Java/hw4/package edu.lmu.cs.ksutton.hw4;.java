package edu.lmu.cs.ksutton.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;

public class SchoolSolver {

	/**
	 * hasStoodNextTo is a table of past standings-next-to. A true value at
	 * hasStoodNextTo[i][j]indicates that schoolgirl number i has stood next to
	 * schoolgirl j.
	 * 
	 * hasStoodNextTo[i][j] == hasStoodNextTo[j][i]
	 */
	protected static boolean[][] hasStoodNextTo;

	/**
	 * usedToday is an array of booleans that keeps track of whether or not the
	 * girl has already been placed in a slot for the day.
	 */
	protected static boolean[] usedToday;

	/**
	 * rows is quite simply the organization of schoolgirls into their
	 * respective rows.
	 * 
	 * rows[i][j] represents the the ith row at position j. (i = {0, 1, 2, 3, 4}
	 * and j = {0, 1, 2})
	 */
	// private static RowStack rows;
	/**
	 * The week object is a stack of stacks of stacks. Each week holds its own
	 * days which then holds in own rows stack. The stacks inside a stack make
	 * it easy to backtrack.
	 */

	private static WeekStack week = new WeekStack();

	private static int nodes = 0;

	private static final int DAYS = 7;

	private static final int NUM_GIRLS = 15;

	private static final int NUM_ROWS = 5;

	private static final int NUM_GIRLS_IN_A_ROW = 3;

	public static void main(String[] args) {

		hasStoodNextTo = fillMatrixWithFalse();

		boolean dayFailed = false;

		while (week.size() < DAYS) { // 7 days

			// Add a new empty RowStack to our DayStack
			week.push(new DayStack(NUM_GIRLS));

			DayStack currentDay = week.peek();
			SchoolgirlQueue girlQueue = currentDay.getSchoolgirlQueue();
			while (currentDay.size() < NUM_ROWS) { // 5 rows

				// we need to pop back
				// this should be rare or should never happen
				if (dayFailed) {
					week.pop();
					week.peek().peek().pop();
					week.peek().peek().add(
							week.peek().getSchoolgirlQueue().dequeue());

					dayFailed = false;
					break;
				}

				// try to add three schoolgirls
				// days.peek().push(tryToAddNewRow(new Integer[] { 0, 1, 2 }));

				currentDay.push(new RowStack());

				RowStack currentRow = currentDay.peek();

				while (currentRow.size() < NUM_GIRLS_IN_A_ROW) { // 3 girls
					// to a row

					// we've already been through the girls once
					if (girlQueue.getFlag() == true) {

						// the current row is no good
						if (currentDay.size() == 1) {
							dayFailed = true;
						}

						girlQueue.enqueue(currentDay.pop());

						// step one back one row and pop
						girlQueue.enqueue(currentDay.peek().pop());

						// when we break, we'll reset the currentRow and try to
						// add the next girl in the queue. Hopefully we can
						// avoid
						// a freak bug where we get caught in an infinite loop
						break;
					}

					currentRow.push(girlQueue.dequeue());

					if (isRowOkay(currentRow)) { // march on
						girlQueue.setFlag(false);

						if (currentRow.size() == NUM_GIRLS_IN_A_ROW)
							break;
					}

					else { // pop off and try adding the next
						Schoolgirl popped = currentRow.pop();
						eraseAdjacents(currentRow, popped);
						girlQueue.enqueue(popped);
					}
				}

				// addAdjacents(currentRow, currentRow.peek()); //our day checks
				// out
				addAdjacents(currentRow);
			}
			// usedToday = returnArrayFullOfFalse(15);
		}

		System.out.println(week);
	}

	private static boolean[][] fillMatrixWithFalse() {
		boolean[][] tmp = new boolean[15][15];

		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				tmp[i][j] = false;
			}
		}

		return tmp;
	}

	private static boolean[] returnArrayFullOfFalse(int size) {
		boolean[] tmp = new boolean[size];

		for (int i = 0; i < tmp.length; i++)
			tmp[i] = false;

		return tmp;
	}

	/**
	 * This messy private helper method returns whether or not r is a valid row
	 * 
	 * @param r
	 * @return
	 */
	private static boolean isRowOkay(RowStack r) {

		if (r.size() == 3) {
			if (hasStoodNextTo[r.elementAt(0).getIndex()][r.elementAt(1)
					.getIndex()]
					|| hasStoodNextTo[r.elementAt(1).getIndex()][r.elementAt(2)
							.getIndex()]
					|| hasStoodNextTo[r.elementAt(0).getIndex()][r.elementAt(2)
							.getIndex()])
				return false;
		}

		else if (r.size() == 2) {
			if (hasStoodNextTo[r.elementAt(0).getIndex()][r.elementAt(1)
					.getIndex()]
					|| hasStoodNextTo[r.elementAt(1).getIndex()][r.elementAt(0)
							.getIndex()])
				return false;
		}

		return true;
	}

	/**
	 * addAdjacents updates the hasStoodNextTo table with the appropriate values
	 * 
	 * @param r
	 *            the row to add
	 * @param girl
	 *            the Schoolgirl to be added
	 */
	public static void addAdjacents(RowStack r, Schoolgirl girl) {

		// this nastiness logs the students walking next to each other
		for (Object o : r.toArray()) {
			Schoolgirl s;
			if (o instanceof Schoolgirl)
				s = (Schoolgirl) o;

			else
				throw new IllegalArgumentException();
			// TODO what type of exception should this be?
			SchoolSolver.hasStoodNextTo[girl.getIndex()][s.getIndex()] = true;
			SchoolSolver.hasStoodNextTo[s.getIndex()][girl.getIndex()] = true;
		}
	}

	/**
	 * Updates the hasStoodNextTo table with the an entire row's Schoolgirls
	 * 
	 * @param r
	 *            A Row
	 */
	public static void addAdjacents(RowStack r) {

		assert (r.size() == 3);

		for (Object o : r.toArray()) {
			Schoolgirl s;
			if (o instanceof Schoolgirl)
				s = (Schoolgirl) o;

			else
				throw new IllegalArgumentException();

			addAdjacents(r, s);
		}
	}

	/**
	 * Flips the values to false for the corresponding cells in hasStoodNextTo
	 * 
	 * @param r
	 *            Row to remove
	 * @param girl
	 *            The Schoolgirl to remove
	 */
	public static void eraseAdjacents(RowStack r, Schoolgirl girl) {

		// this nastiness undoes addAdjacents
		for (Object o : r.toArray()) {

			Schoolgirl s;
			if (o instanceof Schoolgirl)
				s = (Schoolgirl) o;
			else
				throw new IllegalArgumentException();
			// TODO what type of exception should this be?

			SchoolSolver.hasStoodNextTo[girl.getIndex()][s.getIndex()] = false;
			SchoolSolver.hasStoodNextTo[s.getIndex()][girl.getIndex()] = false;
		}
	}
}