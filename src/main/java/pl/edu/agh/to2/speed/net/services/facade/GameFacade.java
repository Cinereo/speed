package pl.edu.agh.to2.speed.net.services.facade;

import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.commons.model.MoveDto;
import pl.edu.agh.to2.speed.game.controllers.ModelController;
import pl.edu.agh.to2.speed.net.services.local.eventshandlers.ClientDataSenderService;
import pl.edu.agh.to2.speed.net.services.local.maintanance.ServerMaintentanceService;

/**
 * A facade for game module, which
 */
public class GameFacade {
    private ClientDataSenderService clientDataSenderService;
    private ServerMaintentanceService serverMaintentanceService;

    public GameFacade(ClientDataSenderService senderService, ServerMaintentanceService serverMaintentanceService) {
        this.clientDataSenderService = senderService;
        this.serverMaintentanceService = serverMaintentanceService;
    }

    public void sendMove(MoveDto move) {
        this.clientDataSenderService.sendMove(move);
    }

    public void sendModel(ModelDto model) {
        this.clientDataSenderService.sendModel(model);
    }

    public void endGame(){
        serverMaintentanceService.getServer().stop();
    }

}







