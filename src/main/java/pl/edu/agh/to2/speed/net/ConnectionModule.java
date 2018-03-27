package pl.edu.agh.to2.speed.net;

import pl.edu.agh.to2.speed.game.controllers.ModelController;
import pl.edu.agh.to2.speed.net.guice.NetModule;
import pl.edu.agh.to2.speed.net.server.Server;
import pl.edu.agh.to2.speed.net.server.configuration.ServerConfiguration;
import pl.edu.agh.to2.speed.net.services.facade.GameFacade;
import pl.edu.agh.to2.speed.net.services.local.eventshandlers.ClientDataSenderService;
import pl.edu.agh.to2.speed.net.services.local.maintanance.ServerMaintentanceService;

public class ConnectionModule {
    public static ModelController modelController;
    private static GameFacade gameFacade;
    private ConnectionModule() {
    }

    public static GameFacade getGameFacade() throws InterruptedException {
        if (gameFacade == null) {
            ServerMaintentanceService serverMaintentanceService = NetModule.injector.getInstance(ServerMaintentanceService.class);
            ServerConfiguration serverConfiguration = NetModule.injector.getInstance(ServerConfiguration.class);
            Server server = serverMaintentanceService.createServerInstance(serverConfiguration);
            Thread.sleep(500);
            ClientDataSenderService senderService = server.aquireConnectionProvider().connect(modelController);
            Thread.sleep(500);
            gameFacade = new GameFacade(senderService, serverMaintentanceService);
        }
        return gameFacade;
    }
}
