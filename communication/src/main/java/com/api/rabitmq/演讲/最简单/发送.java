package com.api.rabitmq.演讲.最简单;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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
        channel.exchangeDeclare("test","direct",true,false,null);

        /**
         * 申明队列
         */
        channel.queueDeclare("queueone",true,false,false,null);

        /**
         * 申明队列
         */
        channel.queueDeclare("queuetwo",true,false,false,null);

        /**
         * 绑定test到queueone使用routingkey为routingone
         */
        channel.queueBind("queueone","test","routingone",null);
        channel.queueBind("queuetwo","test","routingtwo",null);

        /**
         * 发送消息
         */
        channel.basicPublish("test","routingone",MessageProperties.PERSISTENT_TEXT_PLAIN,"my isss queueone".getBytes());
        channel.basicPublish("test","routingtwo",MessageProperties.PERSISTENT_TEXT_PLAIN,"my isss queuetwo".getBytes());





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
