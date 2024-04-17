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
    private TimerTask currentTask;
    private final Rectangle collisionBox;

    /**
     * Smiður til að upphafsstilla óvin
     */
    public Ovinur() {
        setWidth(76);
        setHeight(68);
        Image Star = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/Ovinur.png")));
        setFill(new ImagePattern(Star));
        random = new Random();
        timer = new Timer();
        byrjaHreyfingu();

        collisionBox = new Rectangle();
        collisionBox.setWidth(40);
        collisionBox.setHeight(40);
    }

    /**
     * Uppfærir staðsetningu óvinar á 100ms fresti
     */
    private void byrjaHreyfingu() {
        currentTask = new TimerTask() {
            @Override
            public void run() {
                afram();
            }
        };
        timer.scheduleAtFixedRate(currentTask, 0, 100);
    }

    /**
     * Stöðvar óvin
     */
    public void stop() {
        currentTask.cancel();
        timer.cancel();
        timer.purge();
    }

    /**
     * Athugar hvort óvinur og grafari snertast
     * @param grafari leikmaður á leikborði
     * @return true eða false
     */
    public boolean isCollidingWithGrafari(Grafari grafari) {
        collisionBox.setLayoutX(getLayoutX() + (getWidth() - collisionBox.getWidth()) / 2);
        collisionBox.setLayoutY(getLayoutY() + (getHeight() - collisionBox.getHeight()) / 2);
        Bounds ovinurBounds = collisionBox.getBoundsInParent();
        Bounds grafariBounds = grafari.getBoundsInParent();

        return ovinurBounds.intersects(grafariBounds);
    }

    /**
     * Hreyfir óvin áfram í handahófskennda átt
     */
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
