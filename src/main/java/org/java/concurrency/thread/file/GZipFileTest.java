package org.java.concurrency.thread.file;

import java.io.File;

/**
 * 使用线程池递归压缩文件夹下面的所有子文件
 * @author zpp
 *
 */
public class GZipFileTest {
	public static void main(String[] args) {
		File file = new File("D://yy");
		GZipFiles.GZip(file);
		GZipFiles.shutdown();
	}
}
