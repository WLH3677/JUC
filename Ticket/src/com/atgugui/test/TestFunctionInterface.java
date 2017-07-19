package com.atgugui.test;

@FunctionalInterface
interface Test {
	
	int add(int x, int y);
	default int div(int x, int y) {
		return x/y;
	}
	public static int  mul(int x, int y) {
		return x-y;
	}
}

public class TestFunctionInterface  {

	public static void main(String[] args) {
		
		Test test = (a,b) -> {return a+b;};
		System.out.println(test.div(4, 2));
		System.out.println(Test.mul(5, 3));
		
		
	}

}
