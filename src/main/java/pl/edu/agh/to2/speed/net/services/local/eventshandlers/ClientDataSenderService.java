package pl.edu.agh.to2.speed.net.services.local.eventshandlers;

import io.netty.channel.Channel;
import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;
import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.commons.model.MoveDto;
import pl.edu.agh.to2.speed.net.datapackages.AbstractPackage;
import pl.edu.agh.to2.speed.net.services.local.PackageFactory;

public class ClientDataSenderService {
    private Channel channel;
    private SpeedLogger logger;


    public ClientDataSenderService(SpeedLogger logger) {
        this.logger = logger;
    }


    public void sendPackage(AbstractPackage abstractPackage) {
        this.channel.writeAndFlush(abstractPackage);
    }

    public void sendMove(MoveDto move) {
        this.sendPackage(PackageFactory.getInstance().createMovePackage(move));
    }

    public void sendModel(ModelDto model) {
        this.sendPackage(PackageFactory.getInstance().createModelPackage(model));
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
