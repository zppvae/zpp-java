package org.java.concurrency.thread.file;

import java.io.File;

public class CountTime implements Runnable {

	private File file;

	public CountTime(File file) {
		super();
		this.file = file;
	}

	@Override
	public void run() {
		System.out.println("正在压缩" + file.getAbsolutePath());
		try {
			while (true) {
				System.out.print(".");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println();
			System.out.println(file.getAbsolutePath() + ".gz已经压缩完成！");
		}
	}

}