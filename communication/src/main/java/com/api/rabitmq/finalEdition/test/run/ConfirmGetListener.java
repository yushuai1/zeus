package com.api.rabitmq.finalEdition.test.run;

import com.api.rabitmq.finalEdition.Interface.RabbitGetListener;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class ConfirmGetListener implements RabbitGetListener {

    @Override
    public void mqLine(String line, Channel channel, long tag) throws IOException {
        long n=tag%3;
        if(n==0){
            // basicAck 方法的第二个参数 multiple
            // 取值为 false 时，表示通知 RabbitMQ 当前消息被确认；
            // 如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认。
            System.out.println("当前消息被确认！---"+n);
            channel.basicAck(tag, false);
        }else if(n==1){
            //requeue，true重新进入队列
            System.out.println("当前消息被重新进入队列中！---"+n);
            channel.basicNack(tag, false, true);
        }else{
            //requeue，true重新进入队列,与basicNack差异缺少multiple参数
            System.out.println("当前消息被重新进入队列中！---"+n);
            channel.basicReject(tag, true);
        }
//        try {
//            Thread.sleep(2*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
