package org.java.jdk8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * StampedLock是Java8引入的一种新的所机制,简单的理解,可以认为它是读写锁的一个改进版本,读写锁虽然分离了读和写的功能,
 * 使得读与读之间可以完全并发,但是读和写之间依然是冲突的,读锁会完全阻塞写锁,它使用的依然是悲观的锁策略.如果有大量的读线程,他也有可能引起写线程的饥饿
 * 
 * StampedLock提供了一种乐观的读策略,这种乐观策略的锁非常类似于无锁的操作,使得乐观锁完全不会阻塞写线程
 * StampedLock的内部实现是基于CLH锁的,CLH锁是一种自旋锁,它保证没有饥饿的发生,并且可以保证FIFO(先进先出)的服务顺序
 * StampedLock的锁方法会返回表示为long的标记
 *
 * http://blog.didispace.com/books/java8-tutorial/ch5.html
 * 乐观的读锁通过调用tryOptimisticRead()获取，它总是返回一个标记而不阻塞当前线程，无论锁是否真正可用。
 * 如果已经有写锁被拿到，返回的标记等于0。你需要总是通过lock.validate(stamp)检查标记是否有效。
 * Description:
 * 
 * @author zpp
 * @date 2018年5月16日
 */
public class StampedLockDemo {
	public static void main(String[] args) throws InterruptedException {
//		final StampedLock lock = new StampedLock();
//		new Thread() {
//			public void run() {
//				long readLong = lock.writeLock();
//				LockSupport.parkNanos(6100000000L);//保持获取写锁6s
//				lock.unlockWrite(readLong);
//			}
//		}.start();
//		Thread.sleep(100);
//		for (int i = 0; i < 3; ++i)
//			new Thread(new OccupiedCPUReadThread(lock)).start();



		semaphore();
	}

	private static class OccupiedCPUReadThread implements Runnable {
		private StampedLock lock;

		public OccupiedCPUReadThread(StampedLock lock) {
			this.lock = lock;
		}

		public void run() {
			Thread.currentThread().interrupt();
			long lockr = lock.readLock();
			System.out.println(Thread.currentThread().getName() + " get read lock");
			lock.unlockRead(lockr);
		}
	}

	public static void sleep(int m){
		try {
			TimeUnit.SECONDS.sleep(m);
		}catch (Exception e) {

		}
	}

	public static void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("termination interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("killing non-finished tasks");
			}
			executor.shutdownNow();
		}
	}
	/**
	 * 乐观锁
	 */
	public static void optimisticLock(){
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
			long stamp = lock.tryOptimisticRead();
			try {
				System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
				sleep(1);
				System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
				sleep(2);
				System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
			} finally {
				lock.unlock(stamp);
			}
		});

		executor.submit(() -> {
			long stamp = lock.writeLock();
			try {
				System.out.println("Write Lock acquired");
				sleep(2);
			} finally {
				lock.unlock(stamp);
				System.out.println("Write done");
			}
		});

		stop(executor);
	}

	/**
	 * 信号量
	 */
	public static void semaphore(){
		ExecutorService executor = Executors.newFixedThreadPool(10);

		Semaphore semaphore = new Semaphore(5);

		Runnable longRunningTask = () -> {
			boolean permit = false;
			try {
				permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
				if (permit) {
					System.out.println("Semaphore acquired");
					sleep(5);
				} else {
					System.out.println("Could not acquire semaphore");
				}
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			} finally {
				if (permit) {
					semaphore.release();
				}
			}
		};

		IntStream.range(0, 10)
				.forEach(i -> executor.submit(longRunningTask));

		stop(executor);
	}
}
