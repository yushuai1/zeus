package com.api.rabitmq.Test;

import com.api.rabitmq.all.util.RabbitListener;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class RabbitRunableStart implements RabbitListener{
//    RabbitRecv rabbitMqUtil = null;
//
//    public RabbitRunableStart(RabbitRecv rabbitMqUtil){
//        this.rabbitMqUtil = rabbitMqUtil;
//    }
    @Override
    public void mqLine(String line,Channel channel,long tag) throws IOException {

        try {
            channel.basicAck(tag, false);
            System.out.println(line+" ---- "+System.currentTimeMillis());
//            rabbitMqUtil.channel.queueDeclare("nihao", true, false, true, null);
//            rabbitMqUtil.channel.basicPublish("", "nihao", null, (line+"--1").getBytes());
        }catch (Exception e){
            channel.basicReject(tag, true);
        }


    }
}
