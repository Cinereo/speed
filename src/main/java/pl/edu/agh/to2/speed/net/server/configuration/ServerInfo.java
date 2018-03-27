package pl.edu.agh.to2.speed.net.server.configuration;


import java.util.concurrent.atomic.AtomicInteger;

public class ServerInfo {
    private ServerStatus serverStatus;
    private AtomicInteger connectedToServer;

    public ServerInfo(ServerStatus status) {
        this.serverStatus = status;
    }

    public AtomicInteger getConnectedToServer() {
        return connectedToServer;
    }

    public void setConnectedToServer(AtomicInteger connectedToServer) {
        this.connectedToServer = connectedToServer;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }
}
