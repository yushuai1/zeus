package com.api.redisUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.api.redisUtil.RedisUtil.getPool;

public class RedisClient {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private JedisPool pool;

    public RedisClient(String ip, int port, String password) {
        pool = getPool(ip, port, password);
    }

    public RedisClient(String ip, int port) {
        pool = getPool(ip, port);
    }

    /**
     * @创建人 : 于帅
     * @创建时间 : 2017-11-28
     * @描述 : redis 快速入队列
     * @参数 : values ，keyArray
     * @返回 : result
     */
    public Boolean lpush(String values,int index, String... keyArray) {

        Jedis jedis = null;
        Pipeline pl = null;
        Boolean result = true;
        try {
            jedis = pool.getResource();
            jedis.select(index);
            pl = jedis.pipelined();
            List<Response<Long>> responses = new ArrayList<>();
            for (String key : keyArray) {
                Response<Long> count = pl.lpush(key, values);
                responses.add(count);
                //返回插入完之后redis的key中的条数
            }
            pl.sync();
            for (Response<Long> response : responses) {
                if (response.get() < 1) {
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
    public List<String> getList(String key,int index) {
        Jedis jedis = null;
        List<String> result = new ArrayList<>();
        try {
            jedis = pool.getResource();
            jedis.select(index);
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

    /**
     * set 值
     * @param key
     * @param values
     * @return
     */
    public String Set(String key,String values) {
        Jedis jedis = null;
        String result = null;
        try {
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

    /**
     *
     * @param key
     * @param values
     * @param index 库名
     * @return
     */
    public String Set(String key,String values,int index) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
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

    /**
     *
     * @param key
     * @param index 库名
     * @return
     */
    public String Get(String key,int index) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
            result = jedis.get(key);
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
     * @描述 : redis 查找队列并删除
     * @参数 : key ，count
     * @返回 : result
     */
    public List<String> lrange(String key, long count,int index) {
        Jedis jedis = null;
        List<String> result = new ArrayList<>();
        try {
            jedis = pool.getResource();
            jedis.select(index);
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
    public long llen(String key,int index) {
        Jedis jedis = null;
        long lens = 0;
        try {
            jedis = pool.getResource();
            jedis.select(index);
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
    public String lpop(String key,int index) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
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


    /**
     * 一次性查出不返回cursor
     * @param count   每次扫描多少条记录，值越大消耗的时间越短，但会影响redis性能。建议设为一千到一万
     * @return 匹配的key集合
     */
    public List<Map.Entry<String, String>> hscan(String key,String cursor, int count,int index) {
        List<Map.Entry<String, String>> list = new ArrayList<>();
        Jedis jedis = pool.getResource();
        try {
            jedis.select(index);
            ScanParams scanParams = new ScanParams();
            scanParams.count(count);
//            scanParams.match(pattern);
            do {
                ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(key,cursor,scanParams);
                list.addAll(scanResult.getResult());
                cursor = scanResult.getStringCursor();
            } while (!"0".equals(cursor));
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return list;
    }


    /**
     * 返回cursor，可以分页查询
     * @param count   每次扫描多少条记录，值越大消耗的时间越短，但会影响redis性能。建议设为一千到一万
     * @return 匹配的key集合
     */
    public  ScanResult<Map.Entry<String, String>> hscans(String key,String cursor, int count,int index) {
        ScanResult<Map.Entry<String, String>> lists =null;
        Jedis jedis = pool.getResource();
        try {
            jedis.select(index);
            ScanParams scanParams = new ScanParams();
            scanParams.count(count);
            lists  = jedis.hscan(key,cursor,scanParams);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return lists;
    }

    /**
     * 设置过期时间
     * @param key
     * @param second
     */
    public void setTTL(String key,int second,int index){

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
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
     * 返回map中的所有key
     * @param key
     * @param index
     * @return
     */
    public Set<String> hmgetkeys(String key,int index) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
            result = jedis.hkeys(key);
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
     * @描述 : redis 获取map
     * @参数 : key，map
     * @返回 : result
     */
    public Map<String, String> hmget(String key,int index) {
        Jedis jedis = null;
        Map<String, String> result = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
            result = jedis.hgetAll(key);
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
     * @描述 : redis 插入map
     * @参数 : key，map
     * @返回 : result
     */
    public String hmset(String key, Map<String, String> map,int index) {

        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
            result = jedis.hmset(key, map);
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
     * 往map中添加
     * @param key
     * @param filed
     * @param value
     * @param index
     * @return
     */
    public Long hset(String key, String filed, String value,int index) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = pool.getResource();
            jedis.select(index);
            result = jedis.hset(key, filed, value);
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
     * 获取对象
     * @param key
     * @param tClass
     * @param count
     * @param <T>
     * @return
     */
    public <T> T getObject(String key,Class<T> tClass,int count) {
        byte[] keys = key.getBytes();
        return JSON.parseObject(getByte(keys,count), tClass, new Feature[0]);
    }
    /**
     * 获取对应key的第一个数据
     * @param key
     * @return
     */
    public byte[] getByte(byte[] key,int count) {

        Jedis jedis = null;
        byte[] result = null;
        try {
            jedis = pool.getResource();
            jedis.select(count);
            result = jedis.get(key);
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
     * 存入对象
     * @param key
     * @param value
     * @param count
     * @return
     */
    public String setObject(String key,Object value,int count){
        String result=null;
        byte[] types=null;
        byte[] keys =null;
        try {
            types= JSON.toJSONBytes(value);
            keys = key.getBytes("UTF-8");
            result= setByte(keys,types,count);
            types.clone();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            types.clone();
            keys.clone();
        }
        return result;
    }
    /**
     * set
     * @param key
     * @return
     */
    public String setByte(byte[] key,byte[] bytes,int count) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            jedis.select(count);
            result = jedis.set(key,bytes);
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





        RedisClient redisClient = new RedisClient("127.0.0.1",6379);
        redisClient.setTTL("0",20,0);

        RedisClient redisApi = new RedisClient("10.2.1.35", 6379,"123456");


        long t1 = System.currentTimeMillis();
        for (int i=0;i<10000;i++){

        }
        Set<String> set = redisApi.hmgetkeys("MEMORY1",0);
        System.out.println(System.currentTimeMillis()-t1);
        long t2 = System.currentTimeMillis();
        String cursor = ScanParams.SCAN_POINTER_START;
        ScanResult<Map.Entry<String, String>> scanResult = redisApi.hscans("MEMORY1",cursor,20000,0);
        List<Map.Entry<String, String>> list = new ArrayList<>();
        list.addAll(scanResult.getResult());
        for (Map.Entry<String,String> map:list) {
            map.getKey();
        }
        System.out.println(scanResult.getStringCursor());
        ScanResult<Map.Entry<String, String>> scanResult1 = redisApi.hscans("MEMORY1",scanResult.getStringCursor(),10000,0);
        System.out.println(System.currentTimeMillis()-t2);

    }
}
