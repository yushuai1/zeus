package com.api.redisUtil;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yushuai  by 2017/11/27
 * 对虹膜数据进行识别
 */

public class RedisUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private final static Map<String, JedisPool> redisClientHashMap = new ConcurrentHashMap<>();

    private RedisUtil() {
    }

    private volatile static JedisPool pool = null;

    public static JedisPool getPool(String ip, int port) {
        String key = ip + port;
        if (redisClientHashMap.get(key) == null) {
            synchronized (RedisUtil.class) {
                if (redisClientHashMap.get(key) == null) {
                    JedisPoolConfig config = getJedisPoolConfig();
                    pool = new JedisPool(config, ip, port, 30 * 1000);
                    redisClientHashMap.put(key, pool);

                }
            }
        }
        return redisClientHashMap.get(key);
    }


    public static JedisPool getPool(String ip, int port, String password) {
        String key = ip + port + password;
        if (redisClientHashMap.get(key) == null) {
            synchronized (RedisUtil.class) {
                if (redisClientHashMap.get(key) == null) {
                    JedisPoolConfig config = getJedisPoolConfig();
                    pool = new JedisPool(config, ip, port, 30 * 1000, password);
                    redisClientHashMap.put(key, pool);
                }
            }
        }
        return redisClientHashMap.get(key);
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
