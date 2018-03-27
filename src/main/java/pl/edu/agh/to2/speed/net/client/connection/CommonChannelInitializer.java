package pl.edu.agh.to2.speed.net.client.connection;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class CommonChannelInitializer extends ChannelInitializer<SocketChannel> {

    private ChannelHandler handler;

    public CommonChannelInitializer(ChannelHandler handler) {
        this.handler = handler;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ch.pipeline()
                .addLast("encoder", new ObjectEncoder())
                .addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())))
                .addLast("handler", handler);
    }
}

