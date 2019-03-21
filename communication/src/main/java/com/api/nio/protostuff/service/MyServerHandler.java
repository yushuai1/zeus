package com.api.nio.protostuff.service;

import com.api.nio.protostuff.entity.MyRequest;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


import java.util.Date;

/**
 * Created by yuyufeng on 2017/8/28.
 */
public class MyServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyRequest myRequest = (MyRequest) msg;
        System.out.println(msg);
        myRequest.setRequestTime(new Date());
        ctx.writeAndFlush(myRequest).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}