package pl.edu.agh.to2.speed.commons.helpers;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class AppContext {
    private static final String CONFIG_SERVER_PATH = "./conf/conf.xml";
    private static XMLConfiguration config;
    static {
        try {
            config = new XMLConfiguration(CONFIG_SERVER_PATH);
        } catch (ConfigurationException e1) {
            e1.printStackTrace();
        }
    }

    public static final String host =  config.getString("server.host");
    public static final int port = config.getInt("server.port");;
    public static final boolean isHost = config.getBoolean("server.isHost");
    public static final String userName = config.getString("user.name");
}
