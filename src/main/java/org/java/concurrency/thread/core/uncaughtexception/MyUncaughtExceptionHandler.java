package org.java.concurrency.thread.core.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自己的MyUncaughtExceptionHanlder
 *
 * 全局处理器
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，终止" + t.getName());
        System.out.println(name + "捕获了异常" + t.getName() + "异常");

        //线程环境通知告警系统
    }
}
