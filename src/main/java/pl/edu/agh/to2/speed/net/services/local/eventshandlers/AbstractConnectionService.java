package pl.edu.agh.to2.speed.net.services.local.eventshandlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public abstract class AbstractConnectionService extends ChannelInboundHandlerAdapter {

    @Override
    public abstract void channelActive(ChannelHandlerContext ctx);

    @Override
    public abstract void channelRead(ChannelHandlerContext ctx, Object msg);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
