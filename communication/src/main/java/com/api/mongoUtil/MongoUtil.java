package com.api.mongoUtil;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MongoUtil {

    private final static Map<String,MongoDatabase> mongoClientHashMap = new ConcurrentHashMap<>();

    private volatile static MongoDatabase db = null;

    private MongoUtil() {
    }

    public static MongoDatabase getDb(String ip, int port,String datebase) {

        String key = ip+port+datebase;
        if (mongoClientHashMap.get(key) == null) {
            synchronized (MongoUtil.class) {
                if (mongoClientHashMap.get(key) == null) {
                    try {

                        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
                        getBuilder(build);

                        //与数据库建立连接的timeout设置为1分钟
                        MongoClientOptions myOptions = build.build();
                        List<ServerAddress> Servers = new ArrayList<ServerAddress>();
                        //数据库连接实例
                        ServerAddress serverAddress = new ServerAddress(ip, port);
                        Servers.add(serverAddress);
//                        ServerAddress serverAddress2 = new ServerAddress("10.2.1.227", port);
//                        Servers.add(serverAddress2);
                        MongoClient mongoClient = new MongoClient(Servers, myOptions);

                        db = mongoClient.getDatabase(datebase);
                        mongoClientHashMap.put(ip+port+datebase,db);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mongoClientHashMap.get(key);
    }

    public static MongoDatabase getDb(String ip, int port,String database,String name, String password) {

        String key = ip+port+database+name+password;
        if (mongoClientHashMap.get(key) == null) {
            synchronized (MongoUtil.class) {
                if (mongoClientHashMap.get(key) == null) {

                    try {

                        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
                        getBuilder(build);

                        //与数据库建立连接的timeout设置为1分钟
                        MongoClientOptions myOptions = build.build();

                        //数据库连接实例
                        ServerAddress serverAddress = new ServerAddress(ip, port);


                        List<MongoCredential> lstCredentials =
                                Arrays.asList(MongoCredential.createScramSha1Credential(
                                       name, "admin",password.toCharArray()));
                        MongoClient mongoClient  = new MongoClient(serverAddress, lstCredentials, myOptions);

                        db = mongoClient.getDatabase(database);
                        mongoClientHashMap.put(key,db);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mongoClientHashMap.get(key);
    }

    private static void getBuilder(MongoClientOptions.Builder build) {
        build.connectionsPerHost(50);   //与目标数据库能够建立的最大connection数量
        build.heartbeatConnectTimeout(300);   //心跳时间
        build.threadsAllowedToBlockForConnectionMultiplier(50); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
                        /*
                         * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
                         * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
                         * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
                         */

        build.maxWaitTime(120000);
        build.connectTimeout(60000);
    }
}
