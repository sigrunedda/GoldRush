package vidmot.goldrush;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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

/**
 * Klasinn sér um að búa til sjálft leikborðið í leiknum
 */
public class Leikbord extends Pane {

    @FXML
    private GoldController goldController;
    private ErfidleikiController erfidleikiController;
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

    /**
     * Verið að stilla hvaða klasar stjórna leikborðinu
     */
    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }
    public void setErfidleikiController() {
        this.erfidleikiController = ErfidleikiController.getInstance();
    }

    /**
     * Hér er hlaðið inn leikborðið frá leikborð-view.fxml
     */
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
        setErfidleikiController();
    }

    public void setFjoldiOvina(int fjoldiOvina) {
        erfidleikiController.setFjoldiOvina(fjoldiOvina);
    }

    public int getFjoldiOvina() {
        return erfidleikiController.getFjoldiOvina();
    }

    /**
     * Aðferð til þess að hreyfa óvin um leikborðið
     */
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

    /**
     * Aðferð til þess að stoppa óvin þegar að hann drepur grafarann
     */
    public void stopOvinur() {
        if (ovinurDropper != null) {
            ovinurDropper.stop();
        }
        for (Ovinur o : ovinur) {
            o.stop();
        }
    }

    /**
     * Hér er verið að bæta við óvin/óvini á leikborðið og stjórna hreyfingu hans/þeirra
     */

    private void dropOvinur() {
        for (int i = 0; i < getFjoldiOvina(); i++) {
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
    /**
     * Ef að óvinurinn rekst á grafarann þá er ræst aðferðina leikLokið og stoppað allt á leikborðinu
     */
    public void ovinurDrepur() {
        for (Ovinur o : ovinur) {
            if (o.isCollidingWithGrafari(grafari)) {
                goldController.leikLokid(VARST_DREPINN);
                System.out.println("Óvinur drap þig");
                stopOvinur();
                gameLoop.stop();
                gameLoop = null;

                setOnKeyPressed(null);
                setOnKeyReleased(null);

                isMovingUp = false;
                isMovingDown = false;
                isMovingLeft = false;
                isMovingRight = false;
            }
        }
    }

    /**
     * Hér er verið að setja stjörnur/gull á leikborðið
     */

    public void startGullDropper() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                grafaGull();
            }
        };
        gameLoop.start();

        Platform.runLater(this::dropGull);
    }

    /**
     * Hér er stoppað framleiðsluna á stjörnunum/gullinu.
     */

    public void stopGullDropper() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    /**
     * Sett gullið/stjörnurnar á tilviljunarkenda staði á leikborðinu
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
     * Ef að grafarinn rekst á stjörnurnar/gullið þá er fjarlægt það og bætt við stigafjöldann
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
            }
        }
        if (gulls.isEmpty() && gullGrafid) {
            dropGull();
        }
        updateGrafariPosition();
    }

    /**
     * Tengja örvatakkana við grafarann
     * @param event hvert er ýtt
     */
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
     * Passar upp á að grafarinn fer ekki fyrir utan leikborðið
     * @param x lárétt
     * @param y lóðrétt
     * @return löglega vídd leikborðsins
     */

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

    /**
     * Þegar að leikur er endurstilltur þá þarf að hreinsa fyrri leikinn af leikborðinu, það er gert með þessari aðferð
     */
    public void hreinsaBord() {
        if (gameLoop != null) {
            gameLoop.stop();
            gameLoop = null;
        }
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

    /**
     * Þegar að notandinn vill hefja leik þá er bætt við nýjan grafara, byrjað framleiðsluna á gullinu og óvin/óvinum,
     * það er einnig ræst klukkuna
     */
    public void hefjaAfram() {
        grafari = new Grafari();
        getChildren().add(grafari);
        startGullDropper();
        startOvinur();
        goldController.startCountUp();

    }
}
