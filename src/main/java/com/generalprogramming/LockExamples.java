package com.generalprogramming;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExamples {

	ReadWriteLock mReadWriteLock = new ReentrantReadWriteLock();
	Lock mReadLock = mReadWriteLock.readLock();
	Lock mWriteLock = mReadWriteLock.writeLock();

	public static void main(String[] args) {
		final LockExamples examples = new LockExamples();
		Thread tr = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Before locking read");
				try {
					examples.mReadLock.lock();
					System.out.println("Reading started.");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Reading finished.");
				} finally {
					examples.mReadLock.unlock();
				}
			}
		});
		Thread tw = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Before locking write");
				try {
					examples.mWriteLock.lock();
					System.out.println("Writing finished.");
				} finally {
					examples.mWriteLock.unlock();
				}
			}
		});
		Thread tw2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Before locking write");
				try {
					examples.mWriteLock.lock();
					System.out.println("Writing finished.");
				} finally {
					examples.mWriteLock.unlock();
				}
			}
		});
		tr.start();
		tw.start();
		tw2.start();
	}
}
