package pl.edu.agh.to2.speed.commons.logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import pl.edu.agh.to2.speed.commons.helpers.AppContext;
import pl.edu.agh.to2.speed.net.datapackages.AbstractPackage;
import pl.edu.agh.to2.speed.net.datapackages.GameBusinessPackage;
import pl.edu.agh.to2.speed.net.datapackages.MetaDataPackage;
import pl.edu.agh.to2.speed.net.server.configuration.ServerConfiguration;

import javax.inject.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class SpeedLogger {
    private final Logger logger = Logger.getLogger("A");

    public void logAppContext() {
        logger.log(Level.INFO, String.format("AppContext: host: %s, port %s, username: %s, isHost: %s ", AppContext.host, AppContext.port, AppContext.userName, AppContext.isHost));
    }


    public void startServer(ServerConfiguration serverConfiguration) {
        logger.log(Level.INFO, String.format("Server started at %s : %s", serverConfiguration.getHost(), serverConfiguration.getPort()));
    }

    public void serverExist(ServerConfiguration serverConfiguration) {
        logger.log(Level.INFO, String.format("Server exist on %s:%s", serverConfiguration.getHost(), serverConfiguration.getPort()));
    }

    public void clientConnectedToServer(ServerConfiguration serverConfiguration) {
        logger.log(Level.INFO, String.format("Client connected on %s:%s", serverConfiguration.getHost(), serverConfiguration.getPort()));
    }

    public void getMetaPackage(MetaDataPackage metaDataPackage) {
        logger.log(Level.INFO, String.format("Client got metaPackage( type: %s, description: %s", metaDataPackage.getType(), metaDataPackage.getDescription()));
    }

    public void getGamePackage(GameBusinessPackage gamePackage) {
        logger.log(Level.INFO, String.format("Client got gamePackage %s", gamePackage.getTimestamp()));
    }

    public void serverBroadCastMsg(int size) {
        logger.log(Level.INFO, String.format("Server broadcasted %d packages ", size));
    }

    public void serverGetPackage(AbstractPackage abstractPackage) {
        logger.log(Level.INFO, String.format("Server get package %s", abstractPackage.getDescription()));
    }
}
