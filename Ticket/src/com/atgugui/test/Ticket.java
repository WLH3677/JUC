package com.atgugui.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		final TestTicket t1 = new TestTicket();
		//lambda表达式
		new Thread(() -> {for (int i = 0; i < 30; i++) {t1.sale();}},"1号").start();
		new Thread(() -> {for (int i = 0; i < 30; i++) {t1.sale();}},"2号").start();
		new Thread(() -> {for (int i = 0; i < 30; i++) {t1.sale();}},"3号").start();
/*		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {

					t1.sale();
				}

			}
		}, "1号").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {

					t1.sale();
				}

			}
		}, "2号").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {

					t1.sale();
				}

			}
		}, "3号").start();*/

	}

}

class TestTicket {

	private int tickets = 30;
	private Lock lock = new ReentrantLock();

	public void sale() {
		lock.lock();
		try {
			if (tickets > 0) {
				System.out.println(Thread.currentThread().getName() + ",卖出第" + tickets-- + "剩余" + tickets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
