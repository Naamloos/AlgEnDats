package com.nhlstenden.queues;
import java.util.Iterator;

public class AQueue<E> implements Queue<E> {
	class QueueIterator<E> implements Iterator {
		private E queueArray[];
		private int current;
		private int front;
		private int length;
		private int maxSize;

		QueueIterator(E[] queueArray, int front, int length, int maxSize) {
			this.queueArray = queueArray;
			this.front = front;
			this.length = length;
			this.maxSize = maxSize;
			this.current = 0;
		}

		@Override
		public boolean hasNext() {
			return current < length;
		}

		@Override
		public Object next() {
			E it = queueArray[(front + current++) % maxSize];
			return it;
		}
	}

	private E queueArray[]; // Array holding queue elements
	private static final int DEFAULT_SIZE = 10;
	private int maxSize; // Maximum size of queue
	private int front; // Index of front element
	private int rear; // Index of rear element

	// Constructors
	@SuppressWarnings("unchecked")
	AQueue(int size) {
		maxSize = size + 1;
		rear = 0;
		front = 1;
		queueArray = (E[]) new Object[maxSize];
	}
	AQueue() { this(DEFAULT_SIZE); }

	/**
	 * Reset the state of the queue.
	 * After clear, the queue does not contain any elements and has size 0.
	 */
	public void clear() {
		rear = 0;
		front = 1;
		queueArray = (E[]) new Object[maxSize];
	}

	/**
	 * Add an item to the end of the queue.
	 * Returns false if the item could not be inserted because the queue is full, or true otherwise.
	 */
	public boolean enqueue(E it)
	{
		if(maxSize == rear)
		{
			return false;
		}
		queueArray[rear] = it;
		rear++;
		return true;
	}

	/**
	 * Return and removes the element on the front of the queue.
	 * This is the element that was first added. Returns null if the queue is empty.
	 */
	public E dequeue() {
		E returnvalue = queueArray[front - 1];

		for (int i = 0; i < queueArray.length - 1; i++)
		{
			queueArray[i] = queueArray[i + 1];
		}
		rear--;

		return returnvalue;
	}

	/**
	 * Return the element on the front without removing it.
	 * Returns null if the queue is empty.
	 */
	public E frontValue() {
		if(isEmpty())
		{
			return  null;
		}
		return queueArray[front - 1];
	}

	/**
	 * Return the number of items currently in the queue.
	 */
	public int length() {
		return rear - (front - 1);
	}

	/**
	 * Return true if queue contains 0 items, false otherwise.
	 */
	public boolean isEmpty() {
		return rear < front;
	}

	/**
	 * Return an iterator to iterate over all elements in the queue.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		return new QueueIterator<E>(this.queueArray, this.front - 1, this.length(), this.maxSize);
	}

	/**
	 * Remove a single instance of the specified element from this queue,
	 * if it is present. Returns true if removed, false otherwise.
	 */
	public boolean remove(Object o) {
		boolean removed = false;

		for (int i = 0; i < rear - 1; i++)
		{
			if(queueArray[i] == o)
			{
				removed = true;
			}

			queueArray[i] = queueArray[removed? i + 1 : i];
		}

		rear--;

		return removed;
	}

	/**
	 * Return a hash code to uniquely identify the queue contents.
	 */
	public int hashCode() {
		return queueArray.hashCode();
	}

	/**
	 * Test if the queue contents are equal to another queue.
	 */
	public boolean equals(Object o) {
		var other = (E[])o;

		for (int i = 0; i < queueArray.length - 1; i++)
		{
			if(queueArray[i] != other[i])
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Return a string representation of the queue
	 */
	public String toString() {
		return "AQueue with a length of " + length();
	}
}
