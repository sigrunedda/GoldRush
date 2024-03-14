package vidmot.goldrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.util.Duration;

import java.util.Optional;

public class GoldController {

    public MenuBar menustyring;
    @FXML
    private MenuController menustyringController;
    @FXML
    private Label fxTimi;
    @FXML
    private Label fxStig;
    private Timeline countdownTimeline;
    private int initialTimeInSeconds = 300;
    @FXML
    private Leikbord leikbord;

    public GoldController(){
        this.leikbord=leikbord;
    }


    @FXML
    public void initialize(){
        menustyringController.setGoldController(this);
        leikbord.setGoldController(this);

        countdownTimeline = new Timeline();
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateCountdown();
            }
        });
        countdownTimeline.getKeyFrames().add(keyFrame);

    }

    public void setInitialTime(int initialTimeInSeconds){
        this.initialTimeInSeconds = initialTimeInSeconds;
    }

    public void startCountDown() {
        updateCountdownLabel(initialTimeInSeconds);
        countdownTimeline.play();
    }

    private void updateCountdown() {
        initialTimeInSeconds--;
        updateCountdownLabel(initialTimeInSeconds);
        if (initialTimeInSeconds <= 0) {
            countdownTimeline.stop();
            System.out.println("LeikLokið");
        }
    }

    private void updateCountdownLabel(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;

        fxTimi.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public void updatePoints(int points){
        if (points != 0){
            String currentPointsText = fxStig.getText();
            int currentPoints = Integer.parseInt(currentPointsText);
            int newPoints = currentPoints + points;

            fxStig.setText(String.valueOf(newPoints));
        } else {
            fxStig.setText("0");
            fxTimi.setText("0");
        }
    }
}