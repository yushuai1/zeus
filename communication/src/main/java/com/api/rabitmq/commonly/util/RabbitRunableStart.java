package com.api.rabitmq.commonly.util;

public class RabbitRunableStart implements RabbitListener{
    @Override
    public void mqLine(String line) {
        try {
            System.out.println(line);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
