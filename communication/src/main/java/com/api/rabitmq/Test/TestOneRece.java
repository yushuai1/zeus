package com.api.rabitmq.Test;

import com.api.rabitmq.all.run.RabbitSent;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestOneRece {

    public Connection connection = null;
    public Channel channel =null;
    private String mqQueue = "onequeue";
    private String mqExchange = "publish2";
    RabbitSent rabbitMqUtil = null;

    public TestOneRece(String ip , String username, String password) throws IOException, TimeoutException {
        connection = getConnection(ip,username,password);
        channel = connection.createChannel();
        this.rabbitMqUtil =
                new RabbitSent(ip,username,password,false);
        rabbitMqUtil.setMqQueue(mqQueue);
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

    private void getRabbitMq() throws IOException {

        //声明路由名字和类型
        channel.exchangeDeclare(this.mqExchange, "fanout", true, false, null);
       //声明队列
        channel.queueDeclare("queue5", true, false, false, null);
        //绑定路由和队列
        channel.queueBind("queue5", this.mqExchange, "routkey", null);

//        channel.exchangeDeclare(this.mqExchange, "fanout");
//        // 创建一个非持久的、唯一的且自动删除的队列且队列名称由服务器随机产生一般情况这个名称与amq.gen-JzTY20BRgKO-HjmUJj0wLg 类似。
//        String queueName = channel.queueDeclare().getQueue();
//
//        channel.exchangeBind("queue1",mqExchange,"routingKey");
        // 为转发器指定队列，设置binding
//        channel.queueBind("queue3", this.mqExchange, "routingKey");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleConsumeOk(String s) {
                System.out.println(s);
            }

            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String message = new String(bytes, "UTF-8");

                try {
                    System.out.print("nihao@@@@"+message.split("----")[0]);
                    rabbitMqUtil.sentOne("nihao@@@@"+message.split("----")[0]);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        };
        // 指定接收者，第二个参数为自动应答，无需手动应答
        channel.basicConsume("queue5", true, consumer);
    }

    public static void main(String[] szd) throws IOException, TimeoutException {
        TestOneRece testOne = new TestOneRece("10.2.1.227", "mq", "mq");
        testOne.getRabbitMq();
    }

}
