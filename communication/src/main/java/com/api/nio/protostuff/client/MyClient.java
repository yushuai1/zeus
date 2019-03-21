package com.api.nio.protostuff.client;

import com.api.nio.protostuff.MyDecoder;
import com.api.nio.protostuff.MyEncoder;
import com.api.nio.protostuff.entity.MyRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;


/**
 * Created by yuyufeng on 2017/8/28.
 */
public class MyClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            ch.pipeline().addLast(new MyEncoder(MyRequest.class));
                            ch.pipeline().addLast(new MyDecoder(MyRequest.class));
                            ch.pipeline().addLast(new MyClientHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
            /*future.channel().writeAndFlush("Hello Netty Server ,I am a common client");
            future.channel().closeFuture().sync();*/
            MyRequest myRequest = new MyRequest();
            myRequest.setRequestMethod("doMethod123");
            myRequest.setRequestTime(new Date());
            future.channel().writeAndFlush(myRequest);
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}