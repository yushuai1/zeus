package com.api.nio.protostuff.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * Created by yuyufeng on 2017/8/28.
 */
public class MyClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush("");          //protobuf的获取

    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("read Message:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}