package vidmot.goldrush;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Leikbord extends Pane {

    @FXML
    private GoldController goldController;
    @FXML
    private MenuController menuController;
    private Grafari grafari;
    private long lastUpdateTime = 0;
    private static final long UPDATE_INTERVAL = 16_666_666;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private AnimationTimer gameLoop;
    private Timeline ovinurDropper;
    @FXML
    public MenuBar menustyring;
    private static final double SPEED = 5.0;
    private final List<Gull> gulls = new ArrayList<>();
    private final ObservableList<Ovinur> ovinur = FXCollections.observableArrayList();
    public static final String VARST_DREPINN = "Bowser náði þér.";

    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
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
        setFocusTraversable(true);
        requestFocus();
    }

    public void startOvinur() {
        Duration ovinurInterval = Duration.seconds(1);
        ovinurDropper = new Timeline(new KeyFrame(ovinurInterval, event -> dropOvinur()));
        ovinurDropper.play();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                ovinurDrepur();
            }
        };
        gameLoop.start();
        setOnKeyPressed(this::handleKeyPress);
        setOnKeyReleased(this::handleKeyRelease);
    }


    private void dropOvinur() {
        int fjoldiOvina = 1;
        for (int i = 0; i < fjoldiOvina; i++) {
            Ovinur ovinur1 = new Ovinur();
            ovinur.add(ovinur1);
            getChildren().add(ovinur1);

            double minX = 0;
            double maxX = getWidth() - ovinur1.getWidth();

            double minY = menustyring != null ? menustyring.getHeight() : 0;
            double maxY = getHeight() - ovinur1.getHeight();

            double initialX = Math.random() * (maxX - minX) + minX;
            double initialY = Math.random() * (maxY - minY) + minY;

            ovinur1.setLayoutX(initialX);
            ovinur1.setLayoutY(initialY);
        }
    }

    public void ovinurDrepur() {
        Bounds grafariBounds = grafari.getBoundsInParent();
        Iterator<Ovinur> iterator = ovinur.iterator();
        while (iterator.hasNext()) {
            Ovinur o = iterator.next();
            Bounds ovinurBounds = o.getBoundsInParent();
            if (grafariBounds.intersects(ovinurBounds)) {
                goldController.leikLokid(VARST_DREPINN);
                System.out.println("Óvinur drap þig");
                iterator.remove();
                o.stop();
                gameLoop.stop();

                setOnKeyPressed(null);
                setOnKeyReleased(null);

                isMovingUp = false;
                isMovingDown = false;
                isMovingLeft = false;
                isMovingRight = false;
            }
        }
    }

    public void startGullDropper() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                grafaGull();
            }
        };
        gameLoop.start();
    }

    public void stopGullDropper() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    public void stopOvinur() {
        if (ovinurDropper != null) {
            ovinurDropper.stop();
        }
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
        boolean gullGrafid = false;

        Iterator<Gull> iterator = gulls.iterator();
        while (iterator.hasNext()) {
            Gull gull = iterator.next();
            Bounds gullBounds = gull.getBoundsInParent();

            if (grafariBounds.intersects(gullBounds)) {
                iterator.remove();
                getChildren().remove(gull);
                goldController.updatePoints(1);
                gullGrafid = true;
            }
        }
        if (gullGrafid) {
            dropGull();
        }
        updateGrafariPosition();
    }

    private void handleKeyPress(KeyEvent event) {
        long currentTime = System.nanoTime();
        if (currentTime - lastUpdateTime < UPDATE_INTERVAL) {
            return;
        }
        switch (event.getCode()) {
            case UP -> isMovingUp = true;
            case DOWN -> isMovingDown = true;
            case LEFT -> isMovingLeft = true;
            case RIGHT -> isMovingRight = true;
            default -> {
            }
        }
        updateGrafariPosition();
        lastUpdateTime = currentTime;
    }

    private void handleKeyRelease(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> isMovingUp = false;
            case DOWN -> isMovingDown = false;
            case LEFT -> isMovingLeft = false;
            case RIGHT -> isMovingRight = false;
            default -> {
            }
        }
    }

    private boolean erLoglegt(double x, double y) {
        return x >= 0 && y >= 0 && x <= getWidth() - grafari.getWidth() && y < getHeight() - grafari.getHeight();
    }

    private void updateGrafariPosition() {
        if (isMovingUp) {
            if (erLoglegt(grafari.getLayoutX(), grafari.getLayoutY() - SPEED)) {
                grafari.setLayoutY(grafari.getLayoutY() - SPEED);
            }
        }
        if (isMovingDown) {
            if (erLoglegt(grafari.getLayoutX(), grafari.getLayoutY() + SPEED)) {
                grafari.setLayoutY(grafari.getLayoutY() + SPEED);
            }
        }
        if (isMovingLeft) {
            if (erLoglegt(grafari.getLayoutX() - SPEED, grafari.getLayoutY())) {
                grafari.setLayoutX(grafari.getLayoutX() - SPEED);
            }
        }
        if (isMovingRight) {
            if (erLoglegt(grafari.getLayoutX() + SPEED, grafari.getLayoutY())) {
                grafari.setLayoutX(grafari.getLayoutX() + SPEED);
            }
        }
    }

    public void hreinsaBord() {
        stopGullDropper();
        stopOvinur();
        for (Ovinur o : ovinur) {
            o.stop();
        }
        getChildren().removeAll(gulls);
        gulls.clear();
        getChildren().removeIf(node -> node instanceof Ovinur);
        ovinur.clear();
        getChildren().remove(grafari);

        setOnKeyPressed(null);
        setOnKeyReleased(null);

        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
    }

    public void hefjaAfram(){
        grafari = new Grafari();
        getChildren().add(grafari);
        startGullDropper();
        dropGull();
        startOvinur();
        goldController.updateCountdownLabel(0);
    }
}
