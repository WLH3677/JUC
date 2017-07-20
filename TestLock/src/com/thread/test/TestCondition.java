package com.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* 
*@author wlh
*@version  创建时间：2017年7月20日下午2:00:17
*类说明：使用Java.util.condition,进行线程之间的通信
*
*/
public class TestCondition {
	
	public static void main(String[] args) {
		MyCondition condition = new MyCondition();
		new Thread(() -> {for (int i = 0; i < 5; i++) { condition.loopA();}},"A").start();
		new Thread(() -> {for (int i = 0; i < 5; i++) { condition.loopB();}},"B").start();
		new Thread(() -> {for (int i = 0; i < 5; i++) { condition.loopC();}},"C").start();
		
	}
}
class MyCondition {
	
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	private int  number = 1;
	
	public void loopA () {
		lock.lock();
		try {
			while(number != 1) {
				condition1.await();
			} 
			for (int i = 0; i < 5; i++) {
				System.out.println("线程"+Thread.currentThread().getName()+"执行"+i+"次");
			}
			System.out.println();
			number = 2;
			condition2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void loopB () {
		lock.lock();
		try {
			while(number != 2) {
				condition2.await();
			} 
			for (int i = 0; i < 5; i++) {
				System.out.println("线程"+Thread.currentThread().getName()+"执行"+i+"次");
			}
			System.out.println();
			number =3;
			condition3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void loopC () {
		lock.lock();
		try {
			while(number != 3) {
				condition3.await();
			} 
			for (int i = 0; i < 15; i++) {
				System.out.println("线程"+Thread.currentThread().getName()+"执行"+i+"次");
			}
			System.out.println();
			number =1;
			condition1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}
