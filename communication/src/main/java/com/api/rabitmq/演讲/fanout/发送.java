package com.api.rabitmq.演讲.fanout;

import com.rabbitmq.client.*;

public class 发送 {

    public static void main(String[] args) throws Exception {

        /**
         * 初始化rabbitmq
         */
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.2.1.227");
        connectionFactory.setPort(AMQP.PROTOCOL.PORT);
        connectionFactory.setPassword("mq");
        connectionFactory.setUsername("mq");
        Connection connection = connectionFactory.newConnection();

        /**
         * 创建管道
         */
        Channel channel = connection.createChannel();

        /**
         * 申明交换机
         */
        channel.exchangeDeclare("testfanout","fanout",true,false,null);


        /**
         * 发送消息
         */
        channel.basicPublish("testfanout","",MessageProperties.PERSISTENT_TEXT_PLAIN,"于帅你是好人！".getBytes());






//
//        /**
//         * 队列申明
//         */
//        channel.queueDeclare("queueTest",false,false,false,null);
//
//        /**
//         * 因为exchange是空，所以采用了direct模式，ronting key  和队列名是一样的
//         */
//        channel.basicPublish("","queueTest",MessageProperties.PERSISTENT_TEXT_PLAIN,"wofasong的是你好".getBytes());

        channel.close();
        connection.close();

        System.out.println("发送完毕！");

    }
}
