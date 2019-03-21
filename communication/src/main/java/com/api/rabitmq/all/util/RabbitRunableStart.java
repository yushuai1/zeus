package com.api.rabitmq.all.util;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public class RabbitRunableStart implements RabbitListener{
    @Override
    public void mqLine(String line,Channel channel,long tag) throws IOException {
        long n=tag%3;
        if(n==0){
            // basicAck 方法的第二个参数 multiple
            // 取值为 false 时，表示通知 RabbitMQ 当前消息被确认；
            // 如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认。
            channel.basicAck(tag, false);
        }else if(n==1){
            //requeue，true重新进入队列
            channel.basicNack(tag, false, true);
        }else{
            //requeue，true重新进入队列,与basicNack差异缺少multiple参数
            channel.basicReject(tag, true);
        }
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println((n==0?"Ack":n==1?"Nack":"Reject")+" mes :"+line+" done");
    }
}
