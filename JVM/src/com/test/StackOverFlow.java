package com.test;

public class StackOverFlow {
	public static void add(int x, int y) {
		add(x, y);
	}

	public static void main(String[] args) {
		int x = 5;
		int  y = 6;
		add(x,y);
		
	}

}
