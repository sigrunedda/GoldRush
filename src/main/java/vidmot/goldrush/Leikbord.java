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
    private final Grafari grafari;
    private long lastUpdateTime = 0;
    private static final long UPDATE_INTERVAL = 16_666_666;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    @FXML
    public MenuBar menustyring;
    private final List<Gull> gulls = new ArrayList<>();
    private final ObservableList<Ovinur> ovinur = FXCollections.observableArrayList();
    public static final String VARST_DREPINN = "Þú varst drepinn. Leik lokið";
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

        setOnKeyPressed(this::handleKeyPress);
        setOnKeyReleased(this::handleKeyRelease);

        grafari = new Grafari();
        getChildren().add(grafari);

        setFocusTraversable(true);
        requestFocus();

        startGullDropper();
        dropGull();
        startOvinur();
    }

    public void startOvinur(){
        Duration ovinurInterval = Duration.seconds(1);
        Timeline ovinurDropper = new Timeline(new KeyFrame(ovinurInterval, event -> dropOvinur()));
        ovinurDropper.play();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                ovinurDrepur();
            }
        };
        gameLoop.start();

    }

    private void dropOvinur() {
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

    public void ovinurDrepur(){
        Bounds grafariBounds = grafari.getBoundsInParent();

        Iterator<Ovinur> iterator = ovinur.iterator();
        while (iterator.hasNext()) {
            Ovinur ovinur = iterator.next();
            Bounds gullBounds = ovinur.getBoundsInParent();

            if (grafariBounds.intersects(gullBounds)) {
                iterator.remove();
                goldController.leikLokid(VARST_DREPINN);
                System.out.println("Óvinur drap þig");
            }
        }
    }

    public void startGullDropper() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                grafaGull();
            }
        };
        gameLoop.start();
    }

    /**
     * Birtir eina stjörnu á handahófskennda staðsetningu á leikborðinu
     */
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

    /**
     * Hækkar stigafjölda um 1, lætur stjörnu hverfa og kallar á
     * dropGull() ef leikmaður snertir stjörnu
     */
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
                System.out.println("grafari grefur");
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

    /**
     * Athugar hvort staðsetning sé lögleg, þ.e. innan leikborðsins
     * @param x x-hnit
     * @param y y-hnit
     * @return true eða false hvort sé innan löglegra marka
     */
    private boolean erLoglegt(double x, double y) {
        return x >= 0 && y >= 0 && x <= getWidth() - grafari.getWidth() && y < getHeight() - grafari.getHeight();
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

    }
}
