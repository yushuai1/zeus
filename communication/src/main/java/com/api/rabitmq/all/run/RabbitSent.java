package com.api.rabitmq.all.run;

import com.api.rabitmq.all.util.ConfirmSent;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class RabbitSent {

    public Connection connection = null;
    public Channel channel =null;
    private String mqQueue = "";
    private String mqExchange = "";
    private ConfirmListener confirmListener = null;
    public SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
    boolean confirm=false;


    public void setConfirmListener(ConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    public RabbitSent(String ip , String username, String password,boolean confirm) throws IOException, TimeoutException {
        this.connection = getConnection(ip,username,password);
        this.channel = connection.createChannel();
        this.confirm = confirm;
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



    public void setMqExchange(String mqExchange) throws IOException {
        this.mqExchange = mqExchange;
        /**
         * 不持久化
         */
        channel.exchangeDeclare(mqExchange, "fanout");
        /**
         * 持久化
         */
//        channel.exchangeDeclare(mqExchange, "fanout",true);
        if (confirm){
            channel.confirmSelect();
            channel.addConfirmListener(confirmListener);
        }
    }



    public void setMqQueue(String mqQueue) throws IOException {
        this.mqQueue = mqQueue;
        /*
         * 声明（创建）队列
         * 参数1：队列名称
         * 参数2：为true时server重启队列不会消失
         * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
         * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
         * 参数5：建立队列时的其他参数
         */
        channel.queueDeclare(mqQueue, false, false, false, null);
        if (confirm){
            channel.confirmSelect();
            channel.addConfirmListener(confirmListener);
        }

    }


    public void sentOne(String mm) throws IOException, TimeoutException {
        /**
         * 1：交换机（空就是默认的交换机）
         * 2：队列名字
         * 3：是否持久化  MessageProperties.PERSISTENT_TEXT_PLAIN(null就是不持久化)
         * 4：对象内容
         */
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        /**
         * public BasicProperties(
         *             String contentType,//消息类型如：text/plain
         *             String contentEncoding,//编码
         *             Map<String,Object> headers,
         *             Integer deliveryMode,//1:nonpersistent 2:persistent
         *             Integer priority,//优先级
         *             String correlationId,
         *             String replyTo,//反馈队列
         *             String expiration,//expiration到期时间
         *             String messageId,
         *             Date timestamp,
         *             String type,
         *             String userId,
         *             String appId,
         *             String clusterId)
         *             public static final BasicProperties PERSISTENT_TEXT_PLAIN =
         *     new BasicProperties("text/plain",
         *                         null,
         *                         null,
         *                         2,
         *                         0, null, null, null,
         *                         null, null, null, null,
         *                         null, null);
         */
        builder.deliveryMode(1);
        AMQP.BasicProperties properties = builder.build();
        if (confirm){
            long nextSeqNo = channel.getNextPublishSeqNo();
            confirmSet.add(nextSeqNo);
        }
        /**
         * mandatory 默认是false
         */
        channel.basicPublish("", mqQueue, properties, mm.getBytes());

    }
    public void sentOnePersistent(String mm) throws Exception {
        if (confirm){
            long nextSeqNo = channel.getNextPublishSeqNo();
            confirmSet.add(nextSeqNo);
        }
        channel.basicPublish("", mqQueue, MessageProperties.PERSISTENT_TEXT_PLAIN, mm.getBytes());

    }
    public void sentMany(String mm) throws Exception{
        if (confirm){
            long nextSeqNo = channel.getNextPublishSeqNo();
            confirmSet.add(nextSeqNo);
        }
        channel.basicPublish(mqExchange, "", null, mm.getBytes());
    }

    public void sentManyPersistent(String mm) throws Exception {
        if (confirm){
            long nextSeqNo = channel.getNextPublishSeqNo();
            confirmSet.add(nextSeqNo);
        }
        channel.basicPublish(mqExchange, "", MessageProperties.PERSISTENT_TEXT_PLAIN, mm.getBytes());
    }

    public void mqClose() throws Exception {
        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        RabbitSent rabbitMqUtil =
                new RabbitSent("10.2.1.227","mq","mq",true);
        rabbitMqUtil.setConfirmListener(new ConfirmSent(rabbitMqUtil.confirmSet));
//        rabbitMqUtil.setMqExchange("nihao");
        rabbitMqUtil.setMqQueue("hello");


        for (int i=0;i<10;i++){
            rabbitMqUtil.sentOne("买保险"+"-"+i);
            Thread.sleep(1000L);
        }


        rabbitMqUtil.mqClose();
    }
}
