package com.alprojects.gentest;

public class ThePair<T1, T2> {

	private T1 first;
	private T2 second;

	public ThePair(T1 o1, T2 o2) {
		first = o1;
		second = o2;
	}

	public T1 getFirst() {
		return first;
	}

	public T2 getSecond() {
		return second;
	}
}


