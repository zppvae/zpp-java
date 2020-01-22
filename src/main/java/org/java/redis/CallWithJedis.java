package org.java.redis;

import redis.clients.jedis.Jedis;

interface CallWithJedis {
    public void call(Jedis jedis);
}
