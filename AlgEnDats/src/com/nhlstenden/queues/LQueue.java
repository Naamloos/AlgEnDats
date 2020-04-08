package com.nhlstenden.queues;
import java.util.Iterator;

public class LQueue<E> implements Queue<E> {
	class Link<E> { // Singly linked list node class
		private E e; // Value for this node
		private Link<E> n; // Point to next node in list

		Link(E it, Link<E> inn) { e = it; n = inn; }
		Link(Link<E> inn) { e = null; n = inn; }

		E element() { return e; } // Return the value
		E setElement(E it) { return e = it; } // Set element value
		Link<E> next() { return n; } // Return next link
		Link<E> setNext(Link<E> inn) { return n = inn; } // Set next link
	}

	class QueueIterator<E> implements Iterator {
		private Link<E> current;
		QueueIterator(Link<E> front) {
			current = front;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Object next() {
			Object element = current.element();
			current = current.next();
			return element;
		}
	}

	private Link<E> front; // Pointer to front queue node
	private Link<E> rear;	// Pointer to rear queue node
	private int size; // Number of elements in queue

	// Constructors
	LQueue() { init(); }
	LQueue(int size) { init(); } // Ignore size

	// Initialize queue
	void init() {
		front = rear = new Link<E>(null);
		size = 0;
	}

	/**
	 * Reset the state of the queue.
	 * After clear, the queue does not contain any elements and has size 0.
	 */
	public void clear() {
		front = rear = new Link<E>(null);
		size = 0;
	}

	/**
	 * Add an item to the end of the queue.
	 * Returns true if the item was inserted.
	 */
	public boolean enqueue(E it) {
		Link<E> newlink = new Link<E>(it, null);
		rear.setNext(newlink);
		rear = newlink;
		if(front.e == null)
		{
			front = front.n;
		}
		return true;
	}

	/**
	 * Return and removes the element on the front of the queue.
	 * This is the element that was first added. Returns null if the queue is empty.
	 */
	public E dequeue() {
		var returnValue = front.e;
		front = front.n; // TODO null check

		return returnValue;
	}

	/**
	 * Return the element on the front without removing it.
	 * Returns null if the queue is empty.
	 */
	public E frontValue() {
		// TODO null check
		return front.e;
	}

	/**
	 * Return the number of items currently in the queue.
	 */
	public int length() {
		Link<E> current = front;
		int len = 0;

		if(front.e == null)
		{
			return 0;
		}

		do
		{
			len++;
			current = current.n;
		}while(current != null);

		return len;
	}

	/**
	 * Return true if queue contains 0 items, false otherwise.
	 */
	public boolean isEmpty()
	{
		return front.e == null && front.n == null;
	}

	/**
	 * Return an iterator to iterate over all elements in the queue.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator()
	{
		return new QueueIterator<E>(front);
	}

	/**
	 * Remove a single instance of the specified element from this queue,
	 * if it is present. Returns true if removed, false otherwise.
	 */
	public boolean remove(Object o) {
		boolean removed = false;
		Link<E> prev = null;
		Link<E> current = front;

		while(current != null)
		{
			if(current.e == o)
			{
				if(front == rear)
				{
					this.clear();
				}
				else if(current == rear)
				{
					rear = prev;
					rear.n = null;
				}
				else if(current == front)
				{
					front = current.n;
				}
				else
				{
					prev.n = current.n;
				}

				removed = true;
				break;
			}

			prev = current;
			current = current.n;
		}

		return removed;
	}

	/**
	 * Return a hash code to uniquely identify the queue contents.
	 */
	public int hashCode() {
		return front.hashCode();
	}

	/**
	 * Test if the queue contents are equal to another queue.
	 */
	public boolean equals(Object o) {
		if(o instanceof LQueue)
		{
			LQueue q2 = (LQueue)o;
			return q2.front == this.front && q2.rear == this.rear;
		}
		return false;
	}

	/**
	 * Return a string representation of the queue
	 */
	public String toString() {
		return "LQueue with a length of " + length();
	}
}
