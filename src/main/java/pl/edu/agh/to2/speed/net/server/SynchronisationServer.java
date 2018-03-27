package pl.edu.agh.to2.speed.net.server;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;
import pl.edu.agh.to2.speed.net.client.connection.CommonChannelInitializer;
import pl.edu.agh.to2.speed.net.client.connection.ConnectionProvider;
import pl.edu.agh.to2.speed.net.exceptions.ConfigurationException;
import pl.edu.agh.to2.speed.net.exceptions.SpeedNetException;
import pl.edu.agh.to2.speed.net.server.configuration.ServerConfiguration;
import pl.edu.agh.to2.speed.net.server.configuration.ServerInfo;
import pl.edu.agh.to2.speed.net.server.configuration.ServerStatus;
import pl.edu.agh.to2.speed.net.services.local.eventshandlers.ServerDataReceiverService;

public class SynchronisationServer implements Server {
    @Inject
    private SpeedLogger logger;
    private EventLoopGroup eventLoopGroup;

    private ServerConfiguration serverConfiguration;

    @Inject
    @Named("serverInfo")
    private ServerInfo serverInfo;

    public SynchronisationServer(ServerConfiguration serverConfiguration, SpeedLogger logger) {
        try {
            this.configure(serverConfiguration);
            this.logger = logger;
            this.serverInfo = new ServerInfo(ServerStatus.STOPPED);
        } catch (ConfigurationException e) {
            throw new SpeedNetException("Can not instantiate server!");
        }
    }


    @Override
    public boolean start() {
        this.serverInfo.setServerStatus(ServerStatus.STARTING);
        Thread task = new Thread(() -> {
            EventLoopGroup group = new NioEventLoopGroup();
            this.eventLoopGroup = group;
            //EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                ChannelFuture f = b.group(group)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new CommonChannelInitializer(new ServerDataReceiverService()))
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                        .bind(serverConfiguration.getPort()).sync();
                f.channel().closeFuture().sync();
                serverInfo.setServerStatus(ServerStatus.RUNNING);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        });
        task.start();
        logger.startServer(serverConfiguration);
        return true;

    }

    @Override
    public boolean stop() {
        eventLoopGroup.shutdownGracefully();
        this.getServerInfo().setServerStatus(ServerStatus.STOPPED);
        return true;
    }

    @Override
    public ServerConfiguration getServerConfiguration() {
        return serverConfiguration;
    }

    @Override
    public ServerInfo getServerInfo() {
        return this.serverInfo;
    }

    @Override
    public void configure(ServerConfiguration serverConfiguration) throws ConfigurationException {
        serverConfiguration.validate();
        this.serverConfiguration = serverConfiguration;
    }

    @Override
    public ConnectionProvider aquireConnectionProvider() {
        return new ConnectionProvider(this, logger);
    }
}
