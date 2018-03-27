package pl.edu.agh.to2.speed.net.server;


import pl.edu.agh.to2.speed.net.client.connection.ConnectionProvider;
import pl.edu.agh.to2.speed.net.exceptions.ConfigurationException;
import pl.edu.agh.to2.speed.net.server.configuration.ServerConfiguration;
import pl.edu.agh.to2.speed.net.server.configuration.ServerInfo;
import pl.edu.agh.to2.speed.net.server.configuration.ServerStatus;

public interface Server {

    /**
     * Starts a server
     *
     * @return flag if start request succed
     */
    boolean start() throws InterruptedException;

    /**
     * Stops a server
     *
     * @return flag if stop request succed
     */
    boolean stop();

    /**
     * Method to retrieve server configuraton
     *
     * @return current server configuraton dto
     */
    ServerConfiguration getServerConfiguration();

    /**
     * Method to retrieve server status
     *
     * @return current server status
     */
    ServerInfo getServerInfo();

    /**
     * Configures a server
     *
     * @param serverConfiguration - dto with server configuration properties
     */
    void configure(ServerConfiguration serverConfiguration) throws ConfigurationException;

    /**
     * @return an instance of object to managing connection
     */
    ConnectionProvider aquireConnectionProvider();
}
