package pl.edu.agh.to2.speed.app;

import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.net.ConnectionModule;
import pl.edu.agh.to2.speed.net.services.facade.GameFacade;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {

        Thread.sleep(1000);
        //Application.launch(InitSceneLauncher.class, args);
        //gameFacade.sendMove(new MoveDto(player, card, destination));
        //ConnectionModule.modelController = new ModelControllerImplementation();
        GameFacade gameFacade = ConnectionModule.getGameFacade();
        gameFacade.sendModel(new ModelDto());
        gameFacade.sendModel(new ModelDto());
        gameFacade.sendModel(new ModelDto());
        gameFacade.sendModel(new ModelDto());
        gameFacade.sendModel(new ModelDto());
        gameFacade.sendModel(new ModelDto());
        gameFacade.sendModel(new ModelDto());

        //PlayerControllers playerControllers = new PlayerControllers();

        //playerControllers.initializeTable();
        //Application.launch(InitSceneLauncher.class, args);
    }

}
