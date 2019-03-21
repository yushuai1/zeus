package com.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.ArrayList;
import java.util.List;

import static com.redis.RedisUtil.getPool;

/**
 * @创建人 : 于帅帅
 * @创建时间 : 2017-11-28 下午3:03:12
 * @描述 : redis接口
 */

public class RedisApi {

    private static final Logger logger = LoggerFactory.getLogger(RedisApi.class);


    /**
     * @创建人 : 于帅
     * @创建时间 : 2017-11-28
     * @描述 : redis 入队列
     * @参数 : values ，keyArray
     * @返回 : result
     */
    public synchronized Boolean lpush(String values, String... keyArray) {
        JedisPool pool = null;
        Jedis jedis = null;
        Pipeline pl = null;
        Boolean result = true;
        try {
            pool = getPool();
            jedis = pool.getResource();
            pl = jedis.pipelined();

            List<Response<Long>> responses = new ArrayList<>();
            for (String key : keyArray) {
                Response<Long> count = pl.lpush(key, values);
                responses.add(count);
                //返回插入完之后redis的key中的条数
            }
            pl.sync();
            if (responses.size()!=keyArray.length){
                jedis.select(4);
                jedis.set("responses",responses.size()+"");
                jedis.select(0);
            }
            for (Response<Long> response : responses) {
                if (response.get() < 1) {
                    jedis.select(4);
                    jedis.set("responses",responses.size()+"");
                    jedis.select(0);
                    result = false;
                    break;
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //返还到连接池
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * @创建人 : 于帅
     * @创建时间 : 2017-11-28
     * @描述 : redis 查找队列
     * @参数 : key ，count
     * @返回 : result
     */
    public List<String> getList(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        List<String> result = new ArrayList<>();
        try {
            pool = getPool();
            jedis = pool.getResource();

            result = jedis.lrange(key, 0, -1);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //返还到连接池
            jedis.close();
        }
        return result;
    }


    public void setTTL(String key,int second){
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.expire(key,second);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    /**
     * @创建人 : 于帅
     * @创建时间 : 2017-11-28
     * @描述 : redis 查找队列并删除
     * @参数 : key ，count
     * @返回 : result
     */
    public List<String> lrange(String key, long count) {
        JedisPool pool = null;
        Jedis jedis = null;
        List<String> result = new ArrayList<>();
        try {
            pool = getPool();
            jedis = pool.getResource();
            for (int i = 0; i < count; i++) {
                result.add(jedis.lpop(key));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //返还到连接池
            jedis.close();
        }
        return result;
    }

    /**
     * @创建人 : 于帅
     * @创建时间 : 2017-11-28
     * @描述 : redis 查找队列长度
     * @参数 : key
     * @返回 : result
     */
    public long llen(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        long lens = 0;
        try {
            pool = getPool();
            jedis = pool.getResource();
            lens = jedis.llen(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } finally {
            //返还到连接池
            jedis.close();
        }
        return lens;
    }


    /**
     * @创建人 : 于帅
     * @创建时间 : 2017-11-28
     * @描述 : redis 出队列
     * @参数 : key
     * @返回 : result
     */
    public String lpop(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        String result = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            result = jedis.lpop(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //返还到连接池
            jedis.close();
        }
        return result;
    }


    public String set(String key,String values) {
        JedisPool pool = null;
        Jedis jedis = null;
        String result = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            result = jedis.set(key,values);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //返还到连接池
            jedis.close();
        }
        return result;
    }
    public static void main(String[] asd) {
        RedisApi redisApi = new RedisApi();
        List<String> list = redisApi.getList("MEMORYQUEUE");
        redisApi.lpush("sha", "yushai", "nihao");
    }
}
