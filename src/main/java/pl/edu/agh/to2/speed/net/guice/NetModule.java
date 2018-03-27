package pl.edu.agh.to2.speed.net.guice;

import com.google.inject.*;
import com.google.inject.name.Names;
import pl.edu.agh.to2.speed.commons.helpers.AppContext;
import pl.edu.agh.to2.speed.commons.logger.SpeedLogger;
import pl.edu.agh.to2.speed.net.server.configuration.ServerConfiguration;
import pl.edu.agh.to2.speed.net.server.configuration.ServerInfo;
import pl.edu.agh.to2.speed.net.server.configuration.ServerStatus;


public class NetModule extends AbstractModule {
    public static final Injector injector = Guice.createInjector(new NetModule());

    @Override
    protected void configure() {
        bind(SpeedLogger.class).in(Singleton.class);
        bind(ServerInfo.class).annotatedWith(Names.named("newlyCreatedServer")).toInstance(new ServerInfo(ServerStatus.STOPPED));
    }

    @Provides
    ServerConfiguration provideServerConfiguration() {
        return new ServerConfiguration(AppContext.host, AppContext.port, !AppContext.isHost);
    }
}
