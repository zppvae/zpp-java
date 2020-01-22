package org.java.concurrency.thread.file;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程池进行递归压缩
	递归找出所有子文件，然后调用压缩线程进行压缩
 * @author zpp
 *
 */
public class GZipFiles {

	private final static int THREAD_COUNT = 4;
	private static ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);

	public static void GZip(File fileArgs) {
		if (!fileArgs.isDirectory()) {
			GZipRunnable gZipRunnable = new GZipRunnable(fileArgs);
			service.submit(gZipRunnable);
		} else {
			File[] files = fileArgs.listFiles();
			for (File f : files) {
				GZip(f);
			}
		}
	}

	public static void shutdown() {
		service.shutdown();
	}
}