package util;

import java.util.Arrays;

/**
 * Represents a Tuple of integers.
 * A tuple is a ordered list of elements.
 */
public class IntTuple {

    private int[] elements;

    /**
     * Constructs an IntTuple with the given length, initializing each
     * element to 0.
     */
    public IntTuple(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(
                    "Whoopsie daisy, length cannot be negative.");
        }
        this.elements = new int[length];
    }

    /**
     * Constructs an IntTuple object with the given elements.
     */
    public IntTuple(int[] elements) {
        this.elements = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            this.elements[i] = elements[i];
        }
    }

    /**
     * Returns a new IntTuple object with the same elements as this one.
     */
    public IntTuple copy() {
        return new IntTuple(elements);
    }

    /**
     * Returns the int at the given index.
     */
    public int intAt(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IllegalArgumentException("Index out of Bounds: " + index);
        }
        return elements[index];
    }

    /**
     * Sets the element at the given index.
     */
    public void set(int index, int element) {
        if (index < 0 || index >= elements.length) {
            throw new IllegalArgumentException("Index out of Bounds: " + index);
        }
        elements[index]= element;
    }

    /**
     * Returns a new IntTuple that is the sum of this IntTuple and otherTuple.
     */
    public IntTuple plus(IntTuple otherTuple) {
        if (otherTuple == null) {
            return null;
        } else if (otherTuple.elements.length != this.elements.length) {
            throw new IllegalArgumentException(
                    "Cannot add tuples of differing lengths, chief.");
        }
        int[] sumElements = new int[this.elements.length];
        for (int i = 0; i < this.elements.length; i++) {
            sumElements[i] = this.elements[i] + otherTuple.elements[i];
        }
        return new IntTuple(sumElements);
    }

    /**
     * Returns the sum of all the elements of the IntTuple.
     */
    public int sumOfElements() {
        int sum = 0;
        for (int element : elements) {
            sum += element;
        }
        return sum;
    }

    /**
     * Returns the length of the tuple.
     */
    public int length() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("<" + elements[0]);
        for (int i = 1; i < elements.length; i++) {
            builder.append(", " + elements[i]);
        }
        return builder.append(">").toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        IntTuple other = (IntTuple)obj;

        if (other.length() != this.length()) {
            return false;
        }

        for (int i = 0; i < this.elements.length; i++) {
            if (this.elements[i] != other.elements[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(elements);
        return result;
    }
}