package vidmot.goldrush;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Ovinur extends Rectangle {


    public Ovinur() {
        setWidth(30);
        setHeight(50);
        Image Star = new Image(getClass().getResourceAsStream("myndir/Ovinur.png"));
        setFill(new ImagePattern(Star));
    }

    public boolean isCollidingWithGrafari(Grafari grafari) {
        Bounds ovinurBounds = this.getBoundsInParent();
        Bounds grafariBounds = grafari.getBoundsInParent();

        return ovinurBounds.intersects(grafariBounds);
    }

}