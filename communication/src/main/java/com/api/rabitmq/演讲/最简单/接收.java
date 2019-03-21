package com.api.rabitmq.演讲.最简单;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class 接收 {
    private final static Logger log = LoggerFactory.getLogger(接收.class);

    public static void main(String[] args) throws Exception{
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

        channel.queueDeclare("queuetwo",true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String message = new String(bytes, "UTF-8");
                log.debug("------------------------------"+message+"-----------------------------------");
            }
        };

        channel.basicConsume("queuetwo", true, consumer);
        Thread.sleep(200L);
        channel.close();
        connection.close();

        System.out.println("接收完毕！");
    }
}
