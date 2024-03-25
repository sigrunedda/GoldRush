package vidmot.goldrush;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
<<<<<<< HEAD
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
=======
>>>>>>> Sigrún
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
<<<<<<< HEAD
//        setFill(Color.BLUEVIOLET);

        Image mario = new Image(getClass().getResourceAsStream("myndir/Mario.png"));
        this.setFill(new ImagePattern(mario));
=======

        setImage("myndir/Peach.png");
    }

    public void setImage(String imagePath){
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        setFill(new ImagePattern(image));
>>>>>>> Sigrún
    }
}

