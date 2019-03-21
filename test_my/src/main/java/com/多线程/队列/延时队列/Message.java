package com.多线程.队列.延时队列;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时消息实体
 * @author slimina
 *
 */
public class Message implements Delayed{

    private int id;
    private String body;  //消息内容
    private long excuteTime;//执行时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }


    public Message(int id, String body,long delayTime) {
        this.id = id;
        this.body = body;
        //纳秒转换，把delayTime毫秒转成纳秒，再加上当前纳秒
        this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public int compareTo(Delayed delayed) {
        Message msg = (Message)delayed;
        return Integer.valueOf(this.id)>Integer.valueOf(msg.id)?1:( Integer.valueOf(this.id)<Integer.valueOf(msg.id)?-1:0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return  unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

}

