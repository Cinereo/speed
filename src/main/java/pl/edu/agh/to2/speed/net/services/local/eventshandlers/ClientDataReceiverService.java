package pl.edu.agh.to2.speed.net.services.local.eventshandlers;

import io.netty.channel.ChannelHandlerContext;
import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;
import pl.edu.agh.to2.speed.game.controllers.ModelController;
import pl.edu.agh.to2.speed.net.datapackages.AbstractPackage;
import pl.edu.agh.to2.speed.net.datapackages.GameBusinessPackage;
import pl.edu.agh.to2.speed.net.datapackages.MetaDataPackage;
import pl.edu.agh.to2.speed.net.exceptions.SpeedNetException;
import pl.edu.agh.to2.speed.net.services.local.PackageFactory;

import java.util.LinkedList;
import java.util.Objects;

public class ClientDataReceiverService extends AbstractConnectionService {
    private SpeedLogger logger = new SpeedLogger();
    private ModelController modelController;
    private ClientDataSenderService senderService;

    public ClientDataReceiverService(ClientDataSenderService senderService, ModelController modelController) {
        this.senderService = senderService;
        this.modelController = modelController;
    }

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
    }

    public void processPackage(AbstractPackage abstractPackage) {
        if (abstractPackage instanceof MetaDataPackage) {
            processMetaDataPackage((MetaDataPackage) abstractPackage);
        } else processGamePackage((GameBusinessPackage) abstractPackage);
    }

    private void processGamePackage(GameBusinessPackage gamePackage) {
        logger.getGamePackage(gamePackage);
        try {
            switch (gamePackage.getType()) {
                case MODEL:
                    if (Objects.isNull(modelController))
                        throw new SpeedNetException("Cannot execute method updateModel(ModelDto) due to not injected dependecny");
                    modelController.updateModel(gamePackage.getModelDto());
                    break;
                case MOVE:
                    if (Objects.isNull(modelController))
                        throw new SpeedNetException("Cannot execute method updateModel(MoveDto) due to not injected dependecny");
                    modelController.updateModel(gamePackage.getMoveDto());
                    break;
            }
        } catch (SpeedNetException e) {
            System.out.println(e.getMessage());
        }
    }

    private void processMetaDataPackage(MetaDataPackage metaDataPackage) {
        logger.getMetaPackage(metaDataPackage);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.senderService.setChannel(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        LinkedList<AbstractPackage> packages = (LinkedList<AbstractPackage>) msg;
        for (AbstractPackage abstractPackage : packages) {
            processPackage(abstractPackage);
        }
    }
}
