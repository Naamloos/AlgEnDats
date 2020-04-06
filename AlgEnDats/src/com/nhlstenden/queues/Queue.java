package com.nhlstenden.queues;
import java.util.Iterator;

public interface Queue<E> {
	public void clear();
	public boolean enqueue(E it);
	public E dequeue();
	public E frontValue();
	public int length();
	public boolean isEmpty();
	public Iterator<E> iterator();
	public boolean remove(Object o);
	public int hashCode();
	public boolean equals(Object o);
	public String toString();
}
