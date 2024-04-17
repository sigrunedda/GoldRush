package vinnsla.goldrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;
import vidmot.goldrush.GoldController;

public class Klukka {
    private int initialTimeInSeconds = 0;
    private Timeline countUpTimeline;

    private final GoldController goldController;
    private final Label fxTimi;

    public Klukka(GoldController goldController, Label fxTimi) {
        this.goldController = goldController;
        this.fxTimi = fxTimi;
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

    private void updateCountLabel(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        Platform.runLater(() -> fxTimi.setText(String.format("%02d:%02d", minutes, seconds)));
    }

    public void stopCountUp() {
        if (countUpTimeline != null) {
            countUpTimeline.stop();
        }
    }

    public int getCurrentTimeInSeconds() {
        return initialTimeInSeconds;
    }

    public void setCurrentTimeInSeconds(int currentTimeInSeconds) {
        this.initialTimeInSeconds = currentTimeInSeconds;
    }
}
