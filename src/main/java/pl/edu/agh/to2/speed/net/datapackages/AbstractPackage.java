package pl.edu.agh.to2.speed.net.datapackages;

import io.netty.channel.Channel;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Class for representing network package being sent from and to server
 */
public abstract class AbstractPackage implements Serializable {

    protected Channel channel;
    protected Timestamp timestamp;
    protected String description;

    protected AbstractPackage() {
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
