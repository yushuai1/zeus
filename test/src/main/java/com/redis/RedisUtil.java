package com.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @创建人 : 于帅帅
 * @创建时间 : 2017-11-28 下午3:03:12
 * @描述 : redis连接池
 */
public class RedisUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);
    private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();
    private static JedisPool pool = null;


    /**
     * @创建人 : 于帅
     * @创建时间 : 2017-11-28
     * @描述 : redis 获取连接池
     */
    public static JedisPool getPool() {

        if (pool == null) {

            ResourceBundle bundle = ResourceBundle.getBundle("redis");
            if (bundle == null) {
                throw new IllegalArgumentException(
                        "[redis.properties] is not found!");
            }
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.valueOf(bundle
                    .getString("redis.pool.maxTotal")));
            config.setMaxIdle(Integer.valueOf(bundle
                    .getString("redis.pool.maxIdle")));
            config.setMinIdle(Integer.valueOf(bundle
                    .getString("redis.pool.minIdle")));
            config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWaitMillis")));
            config.setTestOnBorrow(Boolean.valueOf(bundle
                    .getString("redis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(bundle
                    .getString("redis.pool.testOnReturn")));
            if ("".equals(bundle.getString("redis.pwd"))){
                pool = new JedisPool(config, bundle.getString("redis.ip"),
                        Integer.valueOf(bundle.getString("redis.port")), 30*1000);
            }else {
                pool = new JedisPool(config, bundle.getString("redis.ip"),
                        Integer.valueOf(bundle.getString("redis.port")), 30*1000,
                        bundle.getString("redis.pwd"));
            }

        }
        return pool;
    }


}
