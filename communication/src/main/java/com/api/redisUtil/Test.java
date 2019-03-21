package com.api.redisUtil;

import com.irisking.open.common.domain.developer.AppToken;

public class Test {

    public static void main(String[] args){
        RedisClient redisClient = new RedisClient("127.0.0.1",6379);

//        redisClient.setByte("37ad986b47f0a52e8d".getBytes(),"3d2a67a4b7a44137ad986b47f0a52e8d".getBytes(),15);
//        byte[] asdf = redisClient.getByte("3d2a67a4b7a44137ad986b47f0a52e8d".getBytes(),15);
//        System.out.println(asdf.length);
        String result = null;
        long t1 = System.currentTimeMillis();
        for (int y=0;y<10000;y++){
            redisClient.Set("yu","shuai");
            result=redisClient.Get("yu",1);
        }

        System.out.println(result+"-----------"+(System.currentTimeMillis()-t1));
    }
}
