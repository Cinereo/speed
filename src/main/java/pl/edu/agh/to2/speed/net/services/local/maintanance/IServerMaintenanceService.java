package pl.edu.agh.to2.speed.net.services.local.maintanance;

import pl.edu.agh.to2.speed.net.server.Server;
import pl.edu.agh.to2.speed.net.server.configuration.ServerConfiguration;

public interface IServerMaintenanceService {
    /**
     * Creates a server on host, which request this method
     *
     * @param serverConfiguration - server configuration dto
     * @return server instance
     */
    Server createServerInstance(ServerConfiguration serverConfiguration) throws InterruptedException;

    /**
     * Gets instance of server, which already exists
     *
     * @return server instance
     */
    Server getServer();
}
