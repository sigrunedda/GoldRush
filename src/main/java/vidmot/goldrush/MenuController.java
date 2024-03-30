package vidmot.goldrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class MenuController {
    @FXML
    private RadioMenuItem audvelt;
    @FXML
    private RadioMenuItem midlungs;
    @FXML
    private RadioMenuItem erfitt;

    private ToggleGroup erfidleikastig;
    private ToggleGroup personur;
    @FXML
    private GoldController goldController;
    @FXML
    private Label fxTimi;
    private Timeline countdownTimeline;
    private int initialTimeInSeconds = 300;

    public void setGoldController(GoldController goldController){
        this.goldController = goldController;
    }

    @FXML
    private void initialize(){
        erfidleikastig = new ToggleGroup();
        audvelt.setToggleGroup(erfidleikastig);
        midlungs.setToggleGroup(erfidleikastig);
        erfitt.setToggleGroup(erfidleikastig);

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

    @FXML
    private void onBreytaErfidleika(ActionEvent actionEvent){
        if (erfidleikastig.getSelectedToggle() != null){
            RadioMenuItem valid = (RadioMenuItem) erfidleikastig.getSelectedToggle();
            System.out.println("Erfiðleikastigið " + valid.getText() + " valið!");

            int initialTime = getInitialTime(valid.getText());
            goldController.setInitialTime(initialTime);

            goldController.startCountDown();
        } else {
            System.out.println("Ekkert erfiðleikastig valið!");
        }
    }

    private int getInitialTime(String difficulty){
        switch (difficulty){
            case "Auðvelt":
                return 90;
            case "Miðlungs":
                return 60;
            case "Erfitt":
                return 30;
            default:
                return 0;
        }
    }

    private void updateCountdown(){
        initialTimeInSeconds--;
        updateCountdownLabel(initialTimeInSeconds);
        if (initialTimeInSeconds <= 0){
            countdownTimeline.stop();
            System.out.println("LeikLokið");
        }
    }

    private void updateCountdownLabel(int timeInSeconds){
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;

        fxTimi.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @FXML
    private void onNyrLeikur(ActionEvent event){
        System.out.println("Nýr Leikur");
        goldController.updatePoints(0);
    }

    public void onLokaPressed(ActionEvent event) {
        ViewSwitcher.switchTo(View.START);
    }

    @FXML
    private void onUmForritid(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um Forritið");
        alert.setHeaderText(null);
        alert.setContentText("Þetta er leikurinn Gold Rush. \nHöfundur: Sigrún Edda \nÁrtal: 2024");
        alert.showAndWait();
    }

    @FXML
    private void onLeikreglur(ActionEvent event){
        System.out.println("Leikreglur display!");
        ViewSwitcher.switchTo(View.LEIKREGLUR);
    }
}
