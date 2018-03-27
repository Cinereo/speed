package pl.edu.agh.to2.speed.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class ImageSetter {
    public void setImage(String string, ImageView imageView) {
        URL path = getClass().getResource(String.format("/imgs/%s", string));
        Image image = new Image(path.toString());
        imageView.setImage(image);
    }
}
