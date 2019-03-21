package com.api.rabitmq.all.util;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public interface RabbitListener {

    public abstract void mqLine(String line,Channel channel,long tag) throws IOException;
}
