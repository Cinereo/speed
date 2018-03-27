package pl.edu.agh.to2.speed.net.services.local.maintanance;

import com.google.inject.Inject;

import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;

import pl.edu.agh.to2.speed.net.server.Server;
import pl.edu.agh.to2.speed.net.server.SynchronisationServer;
import pl.edu.agh.to2.speed.net.server.configuration.ServerConfiguration;
import pl.edu.agh.to2.speed.net.server.configuration.ServerStatus;


public class ServerMaintentanceService implements IServerMaintenanceService {
    private Server server;

    @Inject
    public ServerMaintentanceService(SpeedLogger speedLogger) {
        this.logger = speedLogger;
    }

    private SpeedLogger logger;

    @Inject
    public void setLogger(SpeedLogger logger) {
        this.logger = logger;
    }

    public Server createServerInstance(ServerConfiguration serverConfiguration) throws InterruptedException {
        logger.logAppContext();
        this.server = new SynchronisationServer(serverConfiguration, logger);
        if (!serverConfiguration.alreadyExists())
            server.start();
        else {
            this.server.getServerInfo().setServerStatus(ServerStatus.RUNNING);
            logger.serverExist(serverConfiguration);
        }

        Thread.sleep(100);
        return server;
    }

    public Server getServer() {
        return server;
    }
}
