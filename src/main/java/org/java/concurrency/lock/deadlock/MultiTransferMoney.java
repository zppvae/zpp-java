package org.java.concurrency.lock.deadlock;

import java.util.Random;

/**
 * 多人同时转账，依然很危险
 */
public class MultiTransferMoney {

    /**
     * 账户总数
     */
    private static final int NUM_ACCOUNTS = 500;
    /**
     * 账户金额
     */
    private static final int NUM_MONEY = 1000;
    /**
     * 转账次数
     */
    private static final int NUM_ITERATIONS = 1000000;
    /**
     * 线程数
     */
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {

        Random rnd = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }
        /**
         * 转账线程
         */
        class TransferThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    //随机转账账户
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    //随机接收账户
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    //随机金额
                    int amount = rnd.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
                System.out.println("运行结束");
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
