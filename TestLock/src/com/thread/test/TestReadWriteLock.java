package com.thread.test;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author wlh
 * @version 创建时间：2017年7月20日下午3:46:38 类说明:测试读写锁的使用，1个线程写，100个线程多的情况
 *
 */
public class TestReadWriteLock {

	public static void main(String[] args) {
		ReadWriteLockTest test = new ReadWriteLockTest();
		new Thread(() -> {
			test.write(new Random().nextInt(100) + "");
		}, "write").start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				test.read();
			}, "线程" + String.valueOf(i)).start();

		}

	}
}

/**
 * 资源类
 */
class ReadWriteLockTest {
	private String content;
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public void read() {
		Lock readLock = readWriteLock.readLock();
		readLock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+ "读取："+this.content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}

	public void write(String content) {

		Lock writeLock = readWriteLock.writeLock();
		writeLock.lock();
		try {
			this.content = content;
			System.out.println("线程"+Thread.currentThread().getName()+"写入："+this.content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}
}