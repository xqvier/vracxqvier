package com.xqvier.test.iiil;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E>, Cloneable {

	/**
	 * Maillon de tête de la liste chaînée
	 */
	private MyNode<E> head = null;

	/**
	 * Maillon de fin de la liste chaînée
	 */
	private MyNode<E> queue = null;

	/**
	 * Taille de la liste.
	 */
	private int size = 0;

	public MyLinkedList() {
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
			return false;
		}
		for (E element : this) {
			if (o.equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new MyListIterator();

		return it;
	}

	private class MyListIterator implements ListIterator<E> {

		private MyNode<E> lastReturned = head;

		private int index = 0;

		public MyListIterator(int pIndex) {
			if (pIndex < 0 || pIndex >= MyLinkedList.this.size) {
				throw new IndexOutOfBoundsException();
			}
			index = pIndex;
		}

		public MyListIterator() {
		}

		@Override
		public boolean hasNext() {
			return lastReturned.getNext() != null;
		}

		@Override
		public E next() {
			lastReturned = lastReturned.getNext();
			index++;
			return lastReturned.getValue();
		}

		@Override
		public boolean hasPrevious() {
			return lastReturned.getPrevious() != null;
		}

		@Override
		public E previous() {
			lastReturned = lastReturned.getPrevious();
			index--;
			return lastReturned.getValue();
		}

		@Override
		public int nextIndex() {
			return index + 1;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			lastReturned.getPrevious().setNext(lastReturned.getNext());
			lastReturned.getNext().setPrevious(lastReturned.getPrevious());
			lastReturned = lastReturned.getPrevious();
		}

		@Override
		public void set(E e) {
			lastReturned.setValue(e);
		}

		@Override
		public void add(E e) {
			MyNode<E> node = new MyNode<E>(e);
			node.setPrevious(lastReturned.getPrevious());
			node.setNext(lastReturned);
			node.getPrevious().setNext(node);
			lastReturned.setPrevious(node);
		}

	}

	private static class MyNode<E> {
		private E value;

		private MyNode<E> next;

		private MyNode<E> previous;

		MyNode(E pValue) {
			value = pValue;
		}

		MyNode<E> getNext() {
			return next;
		}

		void setNext(MyNode<E> next) {
			this.next = next;
		}

		MyNode<E> getPrevious() {
			return previous;
		}

		void setPrevious(MyNode<E> previous) {
			this.previous = previous;
		}

		E getValue() {
			return value;
		}

		void setValue(E value) {
			this.value = value;
		}

	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (E element : this) {
			array[i] = element;
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		Object[] array = a;
		if (a.length <= size) {
			array = new Object[size];
		}
		int i = 0;

		for (E element : this) {
			array[i] = element;
			i++;
		}

		return (T[]) array;
	}

	@Override
	public boolean add(E e) {
		if (e == null) {
			return false;
		}
		MyNode<E> newNode = new MyNode<E>(e);

		if (head == null) {
			head = newNode;
			queue = newNode;
		} else {
			MyNode<E> node = new MyNode<E>(e);
			if (head.getNext() == null) {
				node.setPrevious(head);
			} else {
				node.setPrevious(queue);
			}
			queue = node;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (o == null) {
			return false;
		}
		if (!this.contains(o)) {
			return false;
		}

		MyNode<E> node = head;
		while (node.getNext() != null && !node.getValue().equals(o)) {
			node = node.getNext();
		}
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		size--;
		return true;

	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object element : c) {
			if (!this.contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (c == null) {
			return false;
		}
		for (E element : c) {
			add(element);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (c == null) {
			return false;
		}
		if (index >= size) {
			return false;
		}
		int i = index;

		for (E element : c) {
			add(i, element);
			i++;
		}
		return true;

	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null) {
			return false;
		}
		if (!this.containsAll(c)) {
			return false;
		}
		for (Object element : c) {
			remove(element);
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (c == null) {
			return false;
		}
		if (!this.containsAll(c)) {
			return false;
		}
		for (E element : this) {
			if (!c.contains(element)) {
				remove(element);
			}
		}
		return true;
	}

	@Override
	public void clear() {
		size = 0;
		head = null;
		queue = null;
	}

	@Override
	public E get(int index) {
		if (index >= size) {
			return null;
		}
		// si on cherche un indice proche de la fin, on part de la fin
		if (index == size - 1) {
			return queue.getValue();
		}
		if (size / 2 < index) {
			int i = size - 1;
			E element = null;
			ListIterator<E> listIterator = this.listIterator(size - 1);
			while (listIterator.hasPrevious() && i != index) {
				element = listIterator.previous();
				i--;
			}
			if (i == index) {
				return element;
			}
		} else {
			int i = 0;
			for (E element : this) {
				if (i == index) {
					return element;
				}
				i++;
			}
		}
		return null;
	}

	@Override
	public E set(int index, E element) {
		if (index > size) {
			return null;
		}
		if (index == size) {
			add(element);
		}
		int i = 0;
		MyNode<E> node = head;
		while (node.getNext() != null && i < index) {
			i++;
		}
		node.setValue(element);
		return element;
	}

	@Override
	public void add(int index, E element) {
		if (index > size) {
			return;
		}
		if (index == size) {
			add(element);
		}
		int i = 0;
		MyNode<E> node = head;
		while (node.getNext() != null && i < index) {
			i++;
		}
		MyNode<E> newNode = new MyNode<E>(element);
		newNode.setPrevious(node.getPrevious());
		node.getPrevious().setNext(newNode);
		node.setPrevious(newNode);
		newNode.setNext(node);

	}

	@Override
	public E remove(int index) {
		if (index >= size) {
			return null;
		}
		int i = 0;
		MyNode<E> node = head;
		while (node.getNext() != null && i < index) {
			node = node.getNext();
		}
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		return node.getValue();
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			return -1;
		}
		if (!this.contains(o)) {
			return -1;
		}
		int i = 0;
		for (E element : this) {
			if (o.equals(element)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			return -1;
		}
		if (!this.contains(o)) {
			return -1;
		}
		int index = 0;
		int i = 0;
		for (E element : this) {
			if (o.equals(element)) {
				index = i;
			}
			i++;
		}
		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		ListIterator<E> listIterator = new MyListIterator();
		return listIterator;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		ListIterator<E> listIterator = new MyListIterator(index);

		return listIterator;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex >= size || toIndex < 0
				|| toIndex >= size || fromIndex > toIndex) {
			return null;
		}
		List<E> newList = new MyLinkedList<E>();

		for (int i = fromIndex; i < toIndex; i++) {
			newList.add(get(i));
		}

		return newList;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("MyLinkedList [");
		for (E element : this) {
			result.append(" " + element + ",");
		}
		if (size > 0) {
			result.setCharAt(result.length() - 1, ']');
		} else {
			result.append("]");
		}

		return result.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		MyLinkedList<E> newList = new MyLinkedList<E>();

		newList.addAll(this);

		return newList;
	}

}
