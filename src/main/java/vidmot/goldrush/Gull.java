package vidmot.goldrush;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Random;

public class Gull extends Rectangle {
    public Gull(){
        setWidth(30);
        setHeight(30);
        setFill(Color.GOLD);
    }

    public boolean isCollidingWithGrafari(Grafari grafari){
        Bounds gullBounds = this.getBoundsInParent();
        Bounds grafariBounds = grafari.getBoundsInParent();

        return gullBounds.intersects(grafariBounds);
    }
}
