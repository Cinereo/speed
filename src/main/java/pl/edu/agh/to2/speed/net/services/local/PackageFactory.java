package pl.edu.agh.to2.speed.net.services.local;

import pl.edu.agh.to2.speed.commons.model.ModelDto;
import pl.edu.agh.to2.speed.commons.model.MoveDto;
import pl.edu.agh.to2.speed.net.datapackages.AbstractPackage;
import pl.edu.agh.to2.speed.net.datapackages.GameBusinessPackage;
import pl.edu.agh.to2.speed.net.datapackages.MetaDataPackage;

public class PackageFactory {
    public static PackageFactory getInstance() {
        return new PackageFactory();
    }

    public MetaDataPackage createHelloApproveMessage() {
        return new MetaDataPackage(MetaDataPackage.MetaDataPackageType.HELLO, "server", "hello approved");
    }

    public AbstractPackage createPingMsg(String desc) {
        return new MetaDataPackage(MetaDataPackage.MetaDataPackageType.PING, "USER", desc);
    }

    public MetaDataPackage createPongMessage() {
        return new MetaDataPackage(MetaDataPackage.MetaDataPackageType.PONG, "Server", "PONG");
    }

    public AbstractPackage createMovePackage(MoveDto move) {
        GameBusinessPackage game = new GameBusinessPackage(null, move, GameBusinessPackage.GamePackageType.MOVE);
        return game;
    }

    public AbstractPackage createModelPackage(ModelDto model) {
        GameBusinessPackage game = new GameBusinessPackage(model, null, GameBusinessPackage.GamePackageType.MODEL);
        return game;
    }
}
