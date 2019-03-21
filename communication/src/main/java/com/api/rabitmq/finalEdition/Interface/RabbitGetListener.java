package com.api.rabitmq.finalEdition.Interface;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public interface RabbitGetListener {
    public abstract void mqLine(String line, Channel channel, long tag) throws IOException;
}
