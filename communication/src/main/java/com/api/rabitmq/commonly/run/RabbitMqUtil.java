package com.api.rabitmq.commonly.run;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtil {
    public Connection connection = null;
    public Channel channel =null;
    public String queueORfanout = null;



    public RabbitMqUtil(String ip , String username, String password) throws IOException, TimeoutException {
        connection = getConnection(ip,username,password);
        channel = connection.createChannel();
    }

    protected static Connection getConnection(String ip ,String username,String password) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setPort(AMQP.PROTOCOL.PORT);// MQ端口
        factory.setUsername(username);// MQ用户名
        factory.setPassword(password);// MQ密码
        Connection connection = factory.newConnection();
        return connection;
    }
    public String getQueueORfanout() {
        return queueORfanout;
    }

    public void setQueueORfanout(String queueORfanout) {
        this.queueORfanout = queueORfanout;
    }


    public void sentMany(String mm) throws IOException, TimeoutException {

        channel.exchangeDeclare(queueORfanout, "fanout");
        channel.basicPublish(queueORfanout, "", null, mm.getBytes());
        channel.close();
        connection.close();
    }

    public void sentOne(String mm) throws IOException, TimeoutException {
        /*
         * 声明（创建）队列
         * 参数1：队列名称
         * 参数2：为true时server重启队列不会消失
         * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
         * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
         * 参数5：建立队列时的其他参数
         */
        channel.queueDeclare(queueORfanout, false, false, false, null);
        channel.basicPublish("", queueORfanout, null, mm.getBytes());

        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        RabbitMqUtil rabbitMqUtil = new RabbitMqUtil("10.3.1.166","hxb","hxb");
        rabbitMqUtil.setQueueORfanout("hello");
        rabbitMqUtil.sentOne("买保险");
    }
}
