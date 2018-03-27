package pl.edu.agh.to2.speed.commons.model;

import java.io.Serializable;

public class PlayerInfoDto implements Serializable{
    private String userName;

    public PlayerInfoDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
