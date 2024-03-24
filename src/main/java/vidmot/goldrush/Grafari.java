package vidmot.goldrush;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Grafari extends Rectangle {

    public Grafari(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("grafari-view.fxml"));
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        setLayoutX(50);
        setLayoutY(50);
        setImage("myndir/Luigi.png");
    }

    public void setImage(String imagePath){
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        setFill(new ImagePattern(image));
    }
}

