package pl.edu.agh.to2.speed.net.server.configuration;

import org.apache.commons.lang3.ObjectUtils;
import pl.edu.agh.to2.speed.net.exceptions.ConfigurationException;
import pl.edu.agh.to2.speed.net.exceptions.SpeedNetException;

import java.util.Objects;

public class ServerConfiguration {


    private String host;
    private Integer port;
    private boolean alreadyExists = false;

    public ServerConfiguration(String host, int port, boolean alreadyExists) {
        this.host = host;
        this.port = port;
        this.alreadyExists = alreadyExists;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean alreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }

    public void validate() throws ConfigurationException {
        StringBuilder errors = new StringBuilder();
        if (Objects.isNull(host))
            errors.
                    append(System.getProperty("line.separator")).
                    append("Host is required");
        if (Objects.isNull(port))
            errors.
                    append(System.getProperty("line.separator")).
                    append("Port is required");
        if (errors.length() > 0)
            throw new ConfigurationException(errors.toString());

    }
}
