package com.api.redisUtil.类加载实现;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClassLoad {

    static JedisPoolConfig config = getJedisPoolConfig();
    static String ip;
    static int port;
    private static class getRedis{
        public static JedisPool pool = new JedisPool(config, ip, port, 30 * 1000);
    }

    public static JedisPool getRedisPool(){
        return getRedis.pool;
    }
    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1024);
        config.setMaxIdle(200);
        config.setMinIdle(50);
        config.setMaxWaitMillis(30000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        return config;
    }
}
