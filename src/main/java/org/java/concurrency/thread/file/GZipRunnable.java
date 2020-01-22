package org.java.concurrency.thread.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 单个文件使用GZip压缩
 * @author zpp
 *
 */
public class GZipRunnable implements Runnable {

	private final File file;

	public GZipRunnable(File file) {
		this.file = file;
	}

	@Override
	public void run() {
		if (!file.getName().endsWith(".gz")) {
			File outputFile = new File(file.getParent(), file.getName() + ".gz");
			if (!outputFile.exists()) {
				CountTime countTime = new CountTime(file);
				Thread t = new Thread(countTime);
				t.start();
				try (InputStream in = new BufferedInputStream(new FileInputStream(file));
						OutputStream out = new BufferedOutputStream(
								new GZIPOutputStream(new FileOutputStream(outputFile)));) {
					int b;
					while ((b = in.read()) != -1)
						out.write(b);
					out.flush();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				t.interrupt();
			} else {
				System.out.println(outputFile + "文件已经存在，无法压缩!");
			}
		}
	}
}