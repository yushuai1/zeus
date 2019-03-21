package com.api.rabitmq.commonly.run;

import com.api.rabitmq.commonly.util.RabbitListener;
import com.api.rabitmq.commonly.util.RabbitRunableStart;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqRecv {

    public Connection connection = null;
    public Channel channel =null;
    public String queueORfanout = null;
    public boolean flag = true;

    private RabbitListener tailListener = null;

    public RabbitMqRecv(String ip , String username, String password, RabbitListener tailListener) throws IOException, TimeoutException {
        connection = getConnection(ip,username,password);
        channel = connection.createChannel();
        this.tailListener = tailListener;
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

    public void setQueueORfanout(String queueORfanout) {
        this.queueORfanout = queueORfanout;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void getOne() throws IOException {
        // 声明队列(如果你已经明确的知道有这个队列,那么下面这句代码可以注释掉,
        // 如果不注释掉的话,也可以理解为消费者必须监听一个队列,如果没有就创建一个)
        channel.queueDeclare(queueORfanout, false, false, false, null);

        // 定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                tailListener.mqLine(message);
            }
        };

        channel.basicConsume(queueORfanout, flag, consumer);
    }

    public void getMany() throws IOException {
        channel.exchangeDeclare(queueORfanout, "fanout");
        // 创建一个非持久的、唯一的且自动删除的队列且队列名称由服务器随机产生一般情况这个名称与amq.gen-JzTY20BRgKO-HjmUJj0wLg 类似。
        String queueName = channel.queueDeclare().getQueue();
        // 为转发器指定队列，设置binding
        channel.queueBind(queueName, queueORfanout, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleConsumeOk(String s) {
                System.out.println(s);
            }

            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String message = new String(bytes, "UTF-8");
                tailListener.mqLine(message);
            }
        };
        // 指定接收者，第二个参数为自动应答，无需手动应答
        channel.basicConsume(queueName, flag, consumer);
    }

    public static void main(String[] as) throws IOException, TimeoutException {
        RabbitListener runableStart = new RabbitRunableStart();
        RabbitMqRecv rabbitMqUtil = new RabbitMqRecv("10.3.1.166","hxb","hxb",runableStart);
        rabbitMqUtil.setQueueORfanout("hello");
        rabbitMqUtil.getOne();

        System.out.println("接受程序启动完毕！");
    }
}
