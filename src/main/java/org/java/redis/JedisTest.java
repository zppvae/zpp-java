package org.java.redis;

import redis.clients.jedis.Transaction;


public class JedisTest {

    private static final Redis redis = new Redis();

    public static void main(String[] args) {
        transaction();
    }

    /**
     * 保存变量
     */
    private static void hold(){
        Holder<Long> countHolder = new Holder<>();
        redis.execute(jedis -> {
            long count = jedis.zcard("codehole");
            countHolder.value(count);
        });
        System.out.println(countHolder.value());
    }

    /**
     * redis事务
     */
    private static void transaction(){
        redis.execute(jedis -> {
            Transaction transaction = jedis.multi();
            transaction.lpush("key", "11");
            transaction.lpush("key", "22");
            transaction.lpush("key", "33");

//            int i = 1 / 0; //手动加异常
            transaction.exec();

            System.out.println(jedis.lrange("key",0,10));

        });


    }
}