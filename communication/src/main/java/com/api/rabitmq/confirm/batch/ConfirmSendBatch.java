package com.api.rabitmq.confirm.batch;

import com.rabbitmq.client.*;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class ConfirmSendBatch {

    private static String exchange_name = "batch";
    private static String queue_name = "";

    public static void txSend(Serializable mes) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.3.1.166");
        factory.setPort(AMQP.PROTOCOL.PORT);// MQ端口
        factory.setUsername("hxb");// MQ用户名
        factory.setPassword("hxb");// MQ密码
        Connection conn = factory.newConnection();

        Channel channel = conn.createChannel();
        //开启transaction机制
        channel.confirmSelect();
        channel.exchangeDeclare(exchange_name, "fanout");
        //异步实现发送消息的确认(此部分的消息确认是指发送消息到队列，并非确认消息的有效消费)
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleNack(long deliveryTag, boolean multiple)
                    throws IOException {
                //multiple：测试发现multiple随机true或false，原因未知
                System.out.println("Nack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
            }

            @Override
            public void handleAck(long deliveryTag, boolean multiple)
                    throws IOException {
                System.out.println("Ack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
            }
        });
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(exchange_name, queue_name, null, SerializationUtils.serialize(mes.toString() + i));
        }
//        channel.waitForConfirms();//同步实现发送消息的确认
        System.out.println("-----------");
        channel.close();
        conn.close();
    }

    public static void main(String[] args) throws Exception {
        txSend("hello world!");
    }
}
