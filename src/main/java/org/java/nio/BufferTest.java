package org.java.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class BufferTest {

    /**
     * limit，指定还有多少数据需要取出(在从缓冲区写入通道时)，或者还有多少空间可以放入数据(在从通道读入缓冲区时)
     */
    @Test
    public void test(){
        //初始化
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("aaaacccx".getBytes());
        log.info("capacity: {}, position: {}, limit : {}",
                buf.capacity(),buf.position(),buf.limit());
        //开启读模式
        // 1.将position置0
        // 2.把limit设置为当前的position值
        buf.flip();
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        log.info("read buf : {}",new String(bytes));

        //重复读，将position置为上次读的位置
        buf.rewind();
        log.info("capacity: {}, position: {}, limit : {}",
                buf.capacity(),buf.position(),buf.limit());
        //清空缓冲区（数据被遗忘）
        buf.clear();
    }

    @Test
    public void markAndReset(){
        ByteBuffer buf = ByteBuffer.allocate(10);
        String str = "abcd";
        buf.put(str.getBytes());

        buf.flip();
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes,0,2);

        //打标记
        buf.mark();
        //重置到打标记的位置
        buf.reset();
    }

    /**
     * 非直接缓冲区
     */
    @Test
    public void testChannel() throws Exception{
        FileInputStream fis = new FileInputStream(new File("F:/1.jpg"));

        FileOutputStream fos = new FileOutputStream(new File("F:/2.jpg"));
        //写入通道
        FileChannel inChannel = fis.getChannel();
        //写出通道
        FileChannel outChannel = fos.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (inChannel.read(buf) != -1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        inChannel.close();
        outChannel.close();

        fos.close();
        fis.close();

    }

    /**
     * 直接缓冲区 文件操作
     * @throws Exception
     */
    @Test
    public void testFileChannle() throws Exception{
        //读取管道
        FileChannel inChannle = FileChannel.open(Paths.get("F:/1.jpg"), StandardOpenOption.READ);

        FileChannel outChannle = FileChannel.open(Paths.get("F:/2.jpg"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        //定义映射文件
        MappedByteBuffer inBuf = inChannle.map(FileChannel.MapMode.READ_ONLY,0,inChannle.size());
        MappedByteBuffer outBuf = outChannle.map(FileChannel.MapMode.READ_WRITE,0,inChannle.size());

        byte[] b = new byte[inBuf.limit()];
        inBuf.get(b);
        outBuf.put(b);

        inChannle.close();
        outChannle.close();
    }

    /**
     * 分散读取与聚集写入
     */
    @Test
    public void testScatterAndGather() throws Exception{
        RandomAccessFile raf1 = new RandomAccessFile("F:/test.txt", "rw");
        // 1.获取通道
        FileChannel channel = raf1.getChannel();
        // 2.分配指定大小的指定缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        // 3.分散读取
        ByteBuffer[] bufs = { buf1, buf2 };
        channel.read(bufs);
        for (ByteBuffer byteBuffer : bufs) {
            // 切换为读取模式
            byteBuffer.flip();
        }
        log.info(new String(bufs[0].array(), 0, bufs[0].limit()));

        log.info(new String(bufs[1].array(), 0, bufs[1].limit()));
        // 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("F:/2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);

    }

    /**
     * 字符集
     * @throws Exception
     */
    @Test
    public void testCharset() throws Exception{
        Charset cs1 = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        // 获取解码器
        CharsetDecoder cd = cs1.newDecoder();
        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("字符集!");
        cBuf.flip();
        // 编码
        ByteBuffer bBuf = ce.encode(cBuf);
        for (int i = 0; i < 6; i++) {
            System.out.println(bBuf.get());
        }
        // 解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        log.info(cBuf2.toString());

        Charset cs2 = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer cbeef = cs2.decode(bBuf);
        log.info(cbeef.toString());

    }
}
