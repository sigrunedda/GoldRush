package vidmot.goldrush;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ovinur extends Rectangle {
    private final Random random;
    private final Timer timer;

    public Ovinur() {
        setWidth(50);
        setHeight(80);
        Image Star = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/Ovinur.png")));
        setFill(new ImagePattern(Star));
        random = new Random();
        timer = new Timer();
        byrjaHreyfingu();

    }

    private void byrjaHreyfingu() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                afram();
            }
        },0,1000);
    }

    public boolean isCollidingWithGrafari(Grafari grafari) {
        Bounds ovinurBounds = this.getBoundsInParent();
        Bounds grafariBounds = grafari.getBoundsInParent();

        return ovinurBounds.intersects(grafariBounds);
    }

    public void afram(){
        double hradi = 100;
        double newX = getLayoutX() + (random.nextDouble() -0.5) * hradi *2;
        double newY = getLayoutY() + (random.nextDouble() -0.5) * hradi *2;

        Leikbord l = (Leikbord) this.getParent();
        if (l != null) {
            newX = Math.min(Math.max(newX, 0), l.getWidth() - getWidth());
            newY = Math.min(Math.max(newY, 0), l.getHeight() - getHeight());
            setLayoutX(newX);
            setLayoutY(newY);
        }
    }

}
