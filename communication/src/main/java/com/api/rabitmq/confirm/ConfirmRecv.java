package com.api.rabitmq.confirm;

import java.io.IOException;

import com.rabbitmq.client.*;
import org.apache.commons.lang3.SerializationUtils;

/**
 * 应答模式之confirm机制：消息接收
 * @author sheungxin
 *
 */
public class ConfirmRecv {
    private static String queue_name="tx_queue";

    /**
     * confirm机制：暂时未弄明白confirm机制在consumer的应用，ConfirmListener在consumer中无效
     * basicNack、basicReject：参数requeue=true时，消息会重新进入队列
     * autoDelete队列在消费者关闭后不管是否还有未处理的消息都会关闭掉
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
//      channel.confirmSelect();
        //autoDelete,true只要被消息
        channel.queueDeclare(queue_name,false,false,true,null);
        //关闭自动应答模式
        channel.basicConsume(queue_name, false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties,byte[] body) throws IOException{
                String mes=SerializationUtils.deserialize(body);
                //multiple批量提交，true提交小于参数中tag消息
                long n=envelope.getDeliveryTag()%3;
                if(n==0){
                    // basicAck 方法的第二个参数 multiple
                    // 取值为 false 时，表示通知 RabbitMQ 当前消息被确认；
                    // 如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认。
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }else if(n==1){
                    //requeue，true重新进入队列
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }else{
                    //requeue，true重新进入队列,与basicNack差异缺少multiple参数
                    channel.basicReject(envelope.getDeliveryTag(), true);
                }
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println((n==0?"Ack":n==1?"Nack":"Reject")+" mes :"+mes+" done");
            }
        });
    }

    public static void main(String[] args) throws Exception {
        txRecv();
    }
}
