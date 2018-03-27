package pl.edu.agh.to2.speed.net.services.local.eventshandlers;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;
import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.net.datapackages.AbstractPackage;
import pl.edu.agh.to2.speed.net.datapackages.GameBusinessPackage;
import pl.edu.agh.to2.speed.net.datapackages.MetaDataPackage;
import pl.edu.agh.to2.speed.net.services.local.PackageFactory;
import pl.edu.agh.to2.speed.net.services.local.synchronisation.SynchronisationService;

import java.util.LinkedList;

@ChannelHandler.Sharable
public class ServerDataReceiverService extends AbstractConnectionService {
    private final SpeedLogger logger = new SpeedLogger();
    private ServerDataSenderService senderService;
    private SynchronisationService synchronisationService;

    public ServerDataReceiverService() {
        this.senderService = new ServerDataSenderService(logger);
        this.synchronisationService = new SynchronisationService(this.senderService);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        senderService.addChannel(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        AbstractPackage abstractPackage = (AbstractPackage) msg;
        logger.serverGetPackage(abstractPackage);
        synchronisationService.addPackage(abstractPackage);
    }
}
