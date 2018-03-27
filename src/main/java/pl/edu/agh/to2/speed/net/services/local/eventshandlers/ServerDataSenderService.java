package pl.edu.agh.to2.speed.net.services.local.eventshandlers;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;
import pl.edu.agh.to2.speed.net.datapackages.AbstractPackage;
import rx.Completable;

import java.sql.Timestamp;
import java.util.LinkedList;

public class ServerDataSenderService {
    private final static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final SpeedLogger logger;

    public ServerDataSenderService(SpeedLogger logger) {
        this.logger = logger;
    }

    public void sendPackage(AbstractPackage networkPackage) {
        networkPackage.setTimestamp(new Timestamp(System.currentTimeMillis()));
        LinkedList<AbstractPackage> packages = new LinkedList<>();
        packages.add(networkPackage);
        Channel channel = networkPackage.getChannel();
        networkPackage.setChannel(null); // Channel cannot be send
        channel.writeAndFlush(packages);
    }

    public Completable broadcastPackages(LinkedList<AbstractPackage> packages) {
        for (Channel channel : channels) {
            channel.writeAndFlush(packages);
        }
        logger.serverBroadCastMsg(packages.size());
        return Completable.complete();
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }
}
