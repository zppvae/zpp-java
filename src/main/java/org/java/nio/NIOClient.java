package org.java.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

@Slf4j
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //创建socket通道
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        //切换异步非阻塞
        sc.configureBlocking(false);
        // 指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner=  new Scanner(System.in);
        while (scanner.hasNext()) {
            String str=scanner.next();
            byteBuffer.put((new Date().toString()+"\n"+str).getBytes());

            //切换读取模式
            byteBuffer.flip();
            sc.write(byteBuffer);
            byteBuffer.clear();
        }
        sc.close();
    }

}