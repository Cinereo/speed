package pl.edu.agh.to2.speed.net.datapackages;

import java.io.Serializable;

/**
 * Class for representing a network package contains a meta data information such as server conf, statuses etc.
 */
public class MetaDataPackage extends AbstractPackage implements Serializable {

    public MetaDataPackage(MetaDataPackageType type, String userName, String description) {
        this.type = type;
        this.userName = userName;
        this.description = description;
    }

    public enum MetaDataPackageType {
        PING, PONG, HELLO
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MetaDataPackageType getType() {
        return type;
    }

    public void setType(MetaDataPackageType type) {
        this.type = type;
    }

    private MetaDataPackageType type;


}
