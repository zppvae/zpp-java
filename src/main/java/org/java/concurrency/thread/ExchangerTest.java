package org.java.concurrency.thread;


import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 两个线程间的数据交换
 * Description:
 * @author zpp
 * @date   2017年8月1日
 */
public class ExchangerTest {
 
    public static void main(String[] args) {
         
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();
        service.execute(new Runnable(){
            public void run() {
                try {              
 
                    String data1 = "张三";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据'" + data1 +"'换出去");
                    Thread.sleep((long)(Math.random()*10000));
                    String data2 = (String)exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为'" + data2+"'");
                }catch(Exception e){
                     
                }
            }  
        });
        service.execute(new Runnable(){
            public void run() {
                try {              
                    String data1 = "李四";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据'" + data1 +"'换出去");
                    Thread.sleep((long)(Math.random()*10000));                 
                    String data2 = (String)exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为'" + data2 + "'");
                }catch(Exception e){
                     
                }              
            }  
        });    
    }
}