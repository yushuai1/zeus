package com.api.rabitmq.all.util;

import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;
import java.util.SortedSet;

public class ConfirmSent implements ConfirmListener {
    private SortedSet<Long> confirmSet = null;

    public ConfirmSent(SortedSet<Long> confirmSet){
        this.confirmSet = confirmSet;
    }

    @Override
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("Nack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
        if (multiple) {
            confirmSet.headSet(deliveryTag + 1).clear();
        } else {
            confirmSet.remove(deliveryTag);
        }
    }

    @Override
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("Ack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
        if (multiple) {
            confirmSet.headSet(deliveryTag + 1).clear();
        } else {
            confirmSet.remove(deliveryTag);
        }
    }
}
