package vidmot.goldrush;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import vinnsla.goldrush.Leikur;

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
//        setFill(Color.BLUEVIOLET);

        Image mario = new Image(getClass().getResourceAsStream("myndir/Mario.png"));
        this.setFill(new ImagePattern(mario));
    }
}
