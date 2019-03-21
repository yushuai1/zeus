package com.api.rabitmq.commonly.util;

public interface RabbitListener {
    public abstract void mqLine(String line);
}
