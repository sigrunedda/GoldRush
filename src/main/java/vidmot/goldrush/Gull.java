package vidmot.goldrush;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Gull extends Rectangle {

    /**
     * Smiður til að setja mynd á "gullið"
     */
    public Gull(){
        setWidth(19);
        setHeight(18);
        Image Star = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/star1.png")));
        setFill(new ImagePattern(Star));
    }
}
