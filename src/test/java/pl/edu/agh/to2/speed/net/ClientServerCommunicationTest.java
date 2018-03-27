package pl.edu.agh.to2.speed.net;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.speed.net.services.local.eventshandlers.ClientDataSenderService;

public class ClientServerCommunicationTest {

    private ClientDataSenderService senderService;

    @Before
    public void runServerAndConnect() throws ConfigurationException, InterruptedException {
       /* XMLConfiguration config = new XMLConfiguration(App.CONFIG_SERVER_PATH);
        AppContext.host = config.getString("server.host");
        AppContext.port = config.getInt("server.port");
        AppContext.isHost = config.getBoolean("server.isHost");
        AppContext.userName = config.getString("user.name");

        ServerMaintentanceService serverMaintentanceService = ConnectionModule.injector.getInstance(ServerMaintentanceService.class);
        ServerConfiguration serverConfiguration = ConnectionModule.injector.getInstance(ServerConfiguration.class);
        Server server = serverMaintentanceService.createServerInstance(serverConfiguration);
        senderService = server.aquireConnectionProvider().connect();*/
        //TODO
    }

    @Test
    public void PingPongTest() throws InterruptedException {
        /*String description = "TEST_DESCRIPTION";
        senderService.sendPackage(PackageFactory.getInstance().createPingMsg(description));
        Thread.sleep(1000);*/
        //TODO
    }
}
