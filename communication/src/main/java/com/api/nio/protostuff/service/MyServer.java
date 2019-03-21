package com.api.nio.protostuff.service;


import com.api.nio.protostuff.MyDecoder;
import com.api.nio.protostuff.MyEncoder;
import com.api.nio.protostuff.entity.MyRequest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by yuyufeng on 2017/8/28.
 */
public class MyServer {

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 2048)    //已完成三次握手的请求的队列的最大长度
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws IOException {
                        ch.pipeline().addLast("decode",new MyDecoder(MyRequest.class));
                        ch.pipeline().addLast(new MyEncoder(MyRequest.class));
                        ch.pipeline().addLast(new MyServerHandler());
                    }
                });

        // 绑定端口，同步等待成功
        b.bind("127.0.0.1", 8881).sync();
        System.out.println("Netty server start ok : "
                + ("127.0.0.1" + " : " + 8881));
    }



//    private int port;
//
//    public MyServer(int port) {
//        this.port = port;
//    }

//    public void start(){
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap sbs = new ServerBootstrap().group(bossGroup,workerGroup).
//                    channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new MyDecoder(MyRequest.class));
//                            ch.pipeline().addLast(new MyEncoder(MyRequest.class));
//                            ch.pipeline().addLast(new MyServerHandler());
//                        };
//
//                    }).option(ChannelOption.SO_BACKLOG, 128)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true);
//            // 绑定端口，开始接收进来的连接
//            ChannelFuture future = sbs.bind(port).sync();
//
//            System.out.println("Server start listen at " + port );
//            future.channel().closeFuture().sync();
//        } catch (Exception e) {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }

    public static void main(String[] args) throws Exception {
        new MyServer().bind();
//        int port;
//        if (args.length > 0) {
//            port = Integer.parseInt(args[0]);
//        } else {
//            port = 8080;
//        }
//        new MyServer(port).start();
    }
}