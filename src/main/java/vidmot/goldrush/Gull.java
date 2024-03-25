package vidmot.goldrush;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Gull extends Rectangle {
    public Gull(){
        setWidth(30);
        setHeight(30);
        Image Star = new Image(getClass().getResourceAsStream("myndir/star1.png"));
        setFill(new ImagePattern(Star));
    }

    public boolean isCollidingWithGrafari(Grafari grafari){
        Bounds gullBounds = this.getBoundsInParent();
        Bounds grafariBounds = grafari.getBoundsInParent();

        return gullBounds.intersects(grafariBounds);
    }
}
