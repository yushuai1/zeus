package com.api.rabitmq.confirm;

import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

public class ConfirmImpl implements ConfirmListener {
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
}
