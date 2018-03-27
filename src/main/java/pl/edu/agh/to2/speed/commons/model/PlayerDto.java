package pl.edu.agh.to2.speed.commons.model;

import java.io.Serializable;
import java.util.List;

public class PlayerDto implements Serializable{

    public final List<CardDto> hand;
    public final List<CardDto> coveredCards;

    public PlayerDto(List<CardDto> hand, List<CardDto> coveredCards) {
        this.hand = hand;
        this.coveredCards = coveredCards;
    }


    /*

    public static void main(String [] args) throws IOException, ClassNotFoundException {

        FileOutputStream fileOut =
                new FileOutputStream("employee.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(new PlayerDto());
        out.close();
        fileOut.close();

    }

    */
}


