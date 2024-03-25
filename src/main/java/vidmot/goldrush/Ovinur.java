package vidmot.goldrush;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Ovinur extends Rectangle {
    public Ovinur(){
        setWidth(30);
        setHeight(50);
        Image Star = new Image(getClass().getResourceAsStream("myndir/Ovinur.png"));
        setFill(new ImagePattern(Star));
    }

}
