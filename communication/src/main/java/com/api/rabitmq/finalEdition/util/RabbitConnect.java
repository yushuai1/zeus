package com.api.rabitmq.finalEdition.util;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.*;

public class RabbitConnect {


    /**
     * 单个rabbitmq连接  端口和线程数默认
     * @param ip
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection(String ip , String username, String password) throws IOException, TimeoutException {
        return getConnection(ip,username,password,AMQP.PROTOCOL.PORT,1);
    }

    /**
     * 单个rabbitmq连接 默认线程为1
     * @param ip
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection(String ip , String username, String password,int port) throws IOException, TimeoutException {
        return getConnection(ip,username,password,port,1);
    }

    /**
     * 单个rabbitmq连接  指定线程个数  一般消费者连接的时候需要快速消费信息可以多加几个线程
     * @param ip
     * @param username
     * @param password
     * @param excutor
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection(String ip , String username, String password,int port,int excutor) throws IOException, TimeoutException {
        ExecutorService es = Executors.newFixedThreadPool(excutor);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        // 端口
        factory.setPort(port);
        // 用户名
        factory.setUsername(username);
        // 密码
        factory.setPassword(password);
//        //断开重新连接
//        factory.setAutomaticRecoveryEnabled(true);
//        //重连之后重新声明交换器队列等信息
//        factory.setTopologyRecoveryEnabled(true);
//        //设置连接超时
//        factory.setConnectionTimeout(60000);
//        //设置心跳时间
//        factory.setRequestedHeartbeat(60);

        Connection connection = factory.newConnection(es);
        return connection;
    }

    public static Connection getConnection(String username ,String password, Address[] addrArr) throws IOException, TimeoutException {
        return getConnection(username,password,addrArr,1);
    }

    public static Connection getConnection(String username ,String password, Address[] addrArr,int excutor) throws IOException, TimeoutException {
        ExecutorService es = Executors.newFixedThreadPool(excutor);
        ConnectionFactory factory = new ConnectionFactory();

        // 用户名
        factory.setUsername(username);
        // 密码
        factory.setPassword(password);
        //断开重新连接
        factory.setAutomaticRecoveryEnabled(true);
        //重连之后重新声明交换器队列等信息
        factory.setTopologyRecoveryEnabled(true);
        //设置连接超时
        factory.setConnectionTimeout(30*000);
        //设置心跳时间
        factory.setRequestedHeartbeat(60);
        Connection connection = factory.newConnection(es,addrArr);
        return connection;
    }

}
