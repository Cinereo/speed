package pl.edu.agh.to2.speed.net.exceptions;

public class ConfigurationException extends SpeedNetException{
    public ConfigurationException() {
    }

    public ConfigurationException(String msg) {
        super(msg);
    }

    public ConfigurationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ConfigurationException(Throwable throwable) {
        super(throwable);
    }

}
