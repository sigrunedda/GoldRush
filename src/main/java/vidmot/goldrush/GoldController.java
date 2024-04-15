package vidmot.goldrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.util.Duration;
import vinnsla.goldrush.Leikur;

import java.util.Optional;

public class GoldController {
    public MenuBar menustyring;
    @FXML
    private MenuController menustyringController;
    @FXML
    private Label fxTimi;
    @FXML
    private Label fxStig;
    private int haestaStigTime = 0;
    private int haestaStig = 0;
    private Timeline countUpTimeline;
    private int initialTimeInSeconds = 0;
    @FXML
    private Leikbord leikbord;
    private final Leikur leikur;

    public GoldController() {
        this.leikbord = new Leikbord();
        this.leikur = new Leikur();
    }

    @FXML
    public void initialize() {
        menustyringController.setGoldController(this);
        leikbord.setGoldController(this);

    }

    public void startCountUp() {
        initialTimeInSeconds = 0;
        updateCountLabel(initialTimeInSeconds);
        if (countUpTimeline != null) {
            countUpTimeline.stop();
            countUpTimeline.getKeyFrames().clear();
        }
        countUpTimeline = new Timeline();
        countUpTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> updateCountUp());
        countUpTimeline.getKeyFrames().add(keyFrame);
        countUpTimeline.play();
    }
    private void updateCountUp() {
        initialTimeInSeconds++;
        updateCountLabel(initialTimeInSeconds);
    }

    protected void updateCountLabel(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        fxTimi.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public void updatePoints(int points) {
        if (points != 0) {
            String currentPointsText = fxStig.getText();
            int currentPoints = Integer.parseInt(currentPointsText);
            int newPoints = currentPoints + points;

            if (newPoints > haestaStig) {
                haestaStig = newPoints;
                haestaStigTime = initialTimeInSeconds + 1;
            }

            fxStig.setText(String.valueOf(newPoints));
        } else {
            fxStig.setText("0");
        }
    }

    public void leikLokid(String varstDrepinn) {
        leikur.leikLokid();
        countUpTimeline.stop();
        Platform.runLater(() -> synaAlert(varstDrepinn));
    }

    private void synaAlert(String s) {
        int currentTime = initialTimeInSeconds;
        if (Integer.parseInt(fxStig.getText()) > haestaStig) {
            haestaStig = Integer.parseInt(fxStig.getText());
        }
        Alert alert = new AdvorunDialog("Leik lokið", s, "Stigin þín: " + fxStig.getText() + " | Hæsti stigafjöldi: " + haestaStig +
                "\nTiminn þinn: " + formatTime(currentTime));

        Optional<ButtonType> u = alert.showAndWait();

        // ætti mögulega að vera frekar að velja ef
        // ekkicancelbutton on þá er farið í nýjan
        // leik en það er þá bara seinna
        if (u.get().getButtonData().isCancelButton()) {
            hreinsaBord();
            updatePoints(0);
            ViewSwitcher.switchTo(View.START);
        } else if (u.get().getButtonData().isDefaultButton()) {
            hreinsaBord();
            leikbord.hefjaAfram();
            updatePoints(0);
        }
    }

    public Leikbord getLeikbord() {
        return leikbord;
    }

    public void hreinsaBord() {
        leikbord.hreinsaBord();
    }
    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
