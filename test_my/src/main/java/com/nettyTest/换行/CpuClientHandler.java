
package com.nettyTest.换行;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

//import static com.nettyTest.换行.UtilTool.mapctx;

public class CpuClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(CpuClientHandler.class.getName());

    /**
     * 启动激活的时候执行
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String info = "ACTIVE";
        sentMsg(ctx,info);
    }

    private void sentMsg(ChannelHandlerContext ctx, String info) {
        info = info + System.getProperty("line.separator");
        ByteBuf message = null;
        byte[] req;
        req = info.getBytes();
        message = Unpooled.buffer(req.length);
        message.writeBytes(req);
        ctx.writeAndFlush(message);
    }

    /**
     * 读取服务端发送过来的内容
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        mapctx.put(2L,ctx);
        String body = (String) msg;
        System.out.println(body);
        //服务端获取数据
        /****************/
//        业务处理
//        String result = "nyClient";
        /****************/

//        sentMsg(ctx, result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        logger.warning("Unexpected exception from downstream : "
                + cause);
        ctx.close();
    }
}
