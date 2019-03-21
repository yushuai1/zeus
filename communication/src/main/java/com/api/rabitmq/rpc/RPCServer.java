package com.api.rabitmq.rpc;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCServer {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    //具体处理方法
    private static int fib(int n) {

        return n+100;
    }

    public static void main(String[] argv) {
        //建立连接、通道，并声明队列
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.2.1.227");
        factory.setPort(AMQP.PROTOCOL.PORT);// MQ端口
        factory.setUsername("mq");// MQ用户名
        factory.setPassword("mq");// MQ密码

        Connection connection = null;
        try {
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

            //指该消费者在接收到队列里的消息但没有返回确认结果之前,它不会将新的消息分发给它。
            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                            .correlationId(properties.getCorrelationId()).build();

                    String response = "";
                    System.out.println("2222222222222222222");
                    try {
                        String message = new String(body, "UTF-8");
                        int n = Integer.parseInt(message);

                        System.out.println(" [.] fib(" + message + ")");
                        response += fib(n);
                    } catch (RuntimeException e) {
                        System.out.println(" [.] " + e.toString());
                    } finally {
                        // 返回处理结果队列
                        channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                        //  确认消息，已经收到后面参数
                        // multiple：是否批量.
                        // true:将一次性确认所有小于envelope.getDeliveryTag()的消息。
                        channel.basicAck(envelope.getDeliveryTag(), false);
                        // RabbitMq consumer worker thread notifies the RPC
                        //RabbMQ消费者工作线程通知RPC
                        // server owner thread
                        //服务器所有者线程
                        System.out.println("333333333333333333333");
                        synchronized (this) {
                            System.out.println("444444444444444444");
                            this.notify();
                        }
                    }
                }
            };
            //取消自动确认
            boolean autoAck = false ;
            channel.basicConsume(RPC_QUEUE_NAME, autoAck, consumer);
            // 等待并准备从RPC客户端消费消息。
            while (true) {
                synchronized (consumer) {
                    try {
                        System.out.println("11111111111111111");
                        consumer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (IOException _ignore) {
                }
            }

        }
    }
}