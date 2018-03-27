package pl.edu.agh.to2.speed.net.exceptions;

public class SpeedNetException extends RuntimeException{
    public SpeedNetException() {
    }

    public SpeedNetException(String msg) {
        super(msg);
    }

    public SpeedNetException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public SpeedNetException(Throwable throwable) {
        super(throwable);
    }
}
