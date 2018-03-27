package pl.edu.agh.to2.speed.net.client.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;
import pl.edu.agh.to2.speed.game.controllers.ModelController;
import pl.edu.agh.to2.speed.net.server.Server;
import pl.edu.agh.to2.speed.net.services.local.eventshandlers.ClientDataReceiverService;
import pl.edu.agh.to2.speed.net.services.local.eventshandlers.ClientDataSenderService;

public class ConnectionProvider {
    private Server server;
    private SpeedLogger logger;

    public ConnectionProvider(Server server, SpeedLogger logger) {
        this.server = server;
        System.out.println(server);
        this.logger = logger;
    }

    public ClientDataSenderService connect(ModelController modelController) {
        ClientDataSenderService senderService = new ClientDataSenderService(logger);
        final String host = server.getServerConfiguration().getHost();
        final int port = server.getServerConfiguration().getPort();

        new Thread(() -> {

            EventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap b = new Bootstrap();
                ChannelFuture f = b.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .handler(new CommonChannelInitializer(new ClientDataReceiverService(senderService, modelController)))
                        .connect(host, port).sync();
                logger.clientConnectedToServer(server.getServerConfiguration());
                f.channel().closeFuture().sync();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return senderService;
    }
}





