package pl.edu.agh.to2.speed.net.services.local.synchronisation;

import pl.edu.agh.to2.speed.net.datapackages.AbstractPackage;
import pl.edu.agh.to2.speed.net.services.local.eventshandlers.ServerDataSenderService;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class SynchronisationService {
    private ConcurrentLinkedList<AbstractPackage> packages = new ConcurrentLinkedList<>();


    public SynchronisationService(ServerDataSenderService senderService) {
        /*
         * Whole loop equals server tick
         */
        Observable.interval(50, TimeUnit.MILLISECONDS)
                .filter(i -> !packages.isEmpty())
                .subscribe(aLong -> senderService.broadcastPackages(packages.getCopyAndClearList()));
    }

    public void addPackage(AbstractPackage abstractPackage) {
        packages.add(abstractPackage);
    }
}
