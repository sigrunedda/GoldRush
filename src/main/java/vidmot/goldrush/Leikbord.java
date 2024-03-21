package vidmot.goldrush;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class Leikbord extends Pane {
    @FXML
    private GoldController goldController;
    private Grafari grafari;
    private long lastUpdateTime = 0;
    private static final long UPDATE_INTERVAL = 16_666_666;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    @FXML
    public MenuBar menustyring;
    private List<Gull> gulls = new ArrayList<>();

    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    public Leikbord() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leikbord-view.fxml"));
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                handleKeyPress(event);
            }
        });

        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                handleKeyRelease(event);
            }
        });

        setOnKeyPressed(this::handleKeyPress);
        setOnKeyReleased(this::handleKeyRelease);

        grafari = new Grafari();
        getChildren().add(grafari);

        setFocusTraversable(true);
        requestFocus();

        startGullDropper();
    }

    public void startGullDropper() {
        Duration gullDropInterval = Duration.seconds(2);
        Timeline gullDropper = new Timeline(new KeyFrame(gullDropInterval, event -> dropGull()));
        gullDropper.setCycleCount(Timeline.INDEFINITE);
        gullDropper.play();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                grafaGull();
            }
        };
        gameLoop.start();
    }

    private void dropGull() {
        Gull gull = new Gull();
        gulls.add(gull);
        getChildren().add(gull);

        double minX = 0;
        double maxX = getWidth() - gull.getWidth();

        double minY = menustyring != null ? menustyring.getHeight() : 0;
        double maxY = getHeight() - gull.getHeight();


        double initialX = Math.random() * (maxX - minX) + minX;
        double initialY = Math.random() * (maxY - minY) + minY;

        gull.setLayoutX(initialX);
        gull.setLayoutY(initialY);
    }

    public void grafaGull() {
        Bounds grafariBounds = grafari.getBoundsInParent();

        Iterator<Gull> iterator = gulls.iterator();
        while (iterator.hasNext()) {
            Gull gull = iterator.next();
            Bounds gullBounds = gull.getBoundsInParent();

            if (grafariBounds.intersects(gullBounds)) {
                iterator.remove();
                getChildren().remove(gull);
                goldController.updatePoints(1);
                System.out.println("grafari grefur");
            }
        }
        updateGrafariPosition();
    }

    private void handleKeyPress(KeyEvent event) {
        long currentTime = System.nanoTime();
        if (currentTime - lastUpdateTime < UPDATE_INTERVAL) {
            return;
        }
        switch (event.getCode()) {
            case UP:
                isMovingUp = true;
                break;
            case DOWN:
                isMovingDown = true;
                break;
            case LEFT:
                isMovingLeft = true;
                break;
            case RIGHT:
                isMovingRight = true;
                break;
            default:
        }
        updateGrafariPosition();
        lastUpdateTime = currentTime;
    }

    private void handleKeyRelease(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                isMovingUp = false;
                break;
            case DOWN:
                isMovingDown = false;
                break;
            case LEFT:
                isMovingLeft = false;
                break;
            case RIGHT:
                isMovingRight = false;
                break;
            default:
        }
    }

    private boolean erLoglegt(double x, double y) {
        return x >= 0 && y >= 0 && x <= 600 && y < 330;
    }

    private void updateGrafariPosition() {
        double speed = 5.0;

        if (isMovingUp) {
            if (erLoglegt(grafari.getLayoutX(), grafari.getLayoutY() - speed)) {
                grafari.setLayoutY(grafari.getLayoutY() - speed);
            }
        }
        if (isMovingDown) {
            if (erLoglegt(grafari.getLayoutX(), grafari.getLayoutY() + speed)) {
                grafari.setLayoutY(grafari.getLayoutY() + speed);
            }
        }
        if (isMovingLeft) {
            if (erLoglegt(grafari.getLayoutX() - speed, grafari.getLayoutY())) {
                grafari.setLayoutX(grafari.getLayoutX() - speed);
            }
        }
        if (isMovingRight) {
            if (erLoglegt(grafari.getLayoutX() + speed, grafari.getLayoutY())) {
                grafari.setLayoutX(grafari.getLayoutX() + speed);
            }
        }
    }

    private void hreinsaBord() {
        hreinsaBord();
    }
}