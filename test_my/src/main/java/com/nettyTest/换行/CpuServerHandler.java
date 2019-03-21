
package com.nettyTest.换行;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import static com.nettyTest.换行.UtilTool.mapctx;

public class CpuServerHandler extends ChannelHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        mapctx.put(1L,ctx);
        String body = (String) msg;
        System.out.println(body);
        //服务端获取数据
        /****************/
//        业务处理
        String result = "myService";
        /****************/

        sentMsg(ctx, result);
    }

    private void sentMsg(ChannelHandlerContext ctx, String info) {
        info = info + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(info.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
