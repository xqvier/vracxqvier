package com.xqvier.test.iiil;



public class MyNode<E> {
	private E value;
	
	private MyNode<E> next;
	
	private MyNode<E> previous;
	
	MyNode(E pValue){
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
