package vidmot.goldrush;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ovinur extends Rectangle {
    private final Random random;
    private final Timer timer;
    private TimerTask currentTask;
    private Rectangle collisionBox;

    public Ovinur() {
        setWidth(50);
        setHeight(80);
        Image Star = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/Ovinur.png")));
        setFill(new ImagePattern(Star));
        random = new Random();
        timer = new Timer();
        byrjaHreyfingu();

        collisionBox = new Rectangle();
        collisionBox.setWidth(30);
        collisionBox.setHeight(50);
    }

    private void byrjaHreyfingu() {
        currentTask = new TimerTask() {
            @Override
            public void run() {
                afram();
            }
        };
        timer.scheduleAtFixedRate(currentTask, 0, 100);
    }

    public void stop() {
        currentTask.cancel();
        timer.cancel();
        timer.purge();
    }

    public boolean isCollidingWithGrafari(Grafari grafari) {
        collisionBox.setLayoutX(getLayoutX() + (getWidth() - collisionBox.getWidth()) / 2);
        collisionBox.setLayoutY(getLayoutY() + (getHeight() - collisionBox.getHeight()) / 2);
        Bounds ovinurBounds = collisionBox.getBoundsInParent();
        Bounds grafariBounds = grafari.getBoundsInParent();

        return ovinurBounds.intersects(grafariBounds);
    }

    public void afram(){
        double hradi = 30;
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
