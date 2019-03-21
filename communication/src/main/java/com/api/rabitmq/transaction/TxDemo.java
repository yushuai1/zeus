package com.api.rabitmq.transaction;

import java.io.IOException;
import java.io.Serializable;

import com.rabbitmq.client.*;
import org.apache.commons.lang3.SerializationUtils;

/**
 * 应答模式之transaction机制
 * @author sheungxin
 *
 */
public class TxDemo {
    private static String exchange_name="";
    private static String queue_name="tx_queue";

    /**
     * transaction机制发送消息,事务机制：手动提交和回滚
     * 执行txCommit，消息才会转发给队列进入ready状态
     * 执行txRollback，消息被取消
     * @param mes
     * @throws Exception
     */
    public static void txSend(Serializable mes) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.3.1.166");
        factory.setPort(AMQP.PROTOCOL.PORT);// MQ端口
        factory.setUsername("hxb");// MQ用户名
        factory.setPassword("hxb");// MQ密码
        Connection conn = factory.newConnection();
        Channel channel=conn.createChannel();
        //开启transaction机制
        channel.txSelect();
        channel.queueDeclare(queue_name,false,false,true,null);
        for(int i=0;i<10;i++){
            try{
                channel.basicPublish(exchange_name, queue_name, null, SerializationUtils.serialize(mes.toString()+i));
                //do something
//              int n=5/0;//试验消息回滚
                channel.txCommit();//提交消息
                System.out.println("发布消息"+mes.toString()+i);
            }catch(Exception e){
                channel.txRollback();//异常，取消消息
                System.out.println("回滚消息"+mes.toString()+i);
            }
        }
    }

    /**
     * transaction机制接收消息,事务机制：手动提交和回滚
     * 消费者需要执行basicAck，并txCommit(自动应答模式自动处理，本例中采用手动应答模式)
     * @throws Exception
     */
    public static void txRecv() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.3.1.166");
        factory.setPort(AMQP.PROTOCOL.PORT);// MQ端口
        factory.setUsername("hxb");// MQ用户名
        factory.setPassword("hxb");// MQ密码
        Connection conn = factory.newConnection();
        Channel channel=conn.createChannel();
        //开启transaction机制
        channel.txSelect();
        channel.queueDeclare(queue_name,false,false,true,null);
        //关闭自动应答模式(自动应答模式不需要ack、txCommit)，需要手动basicAck，并执行txCommit
        channel.basicConsume(queue_name, false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,Envelope envelope,AMQP.BasicProperties properties,byte[] body) throws IOException{
                String mes=SerializationUtils.deserialize(body);
                System.out.println("tx Received :'"+mes+"' done");
                channel.basicAck(envelope.getDeliveryTag(), false);
                channel.txCommit();
            }
        });
    }

    public static void main(String[] args) throws Exception {
//        txSend("hello world!");
        txRecv();
    }
}
