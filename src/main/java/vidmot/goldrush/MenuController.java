package vidmot.goldrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> updateCountdown());
        countdownTimeline.getKeyFrames().add(keyFrame);
    }

    @FXML
    private void onBreytaErfidleika(){
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
        return switch (difficulty) {
            case "Auðvelt" -> 90;
            case "Miðlungs" -> 60;
            case "Erfitt" -> 30;
            default -> 0;
        };
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
    private void onNyrLeikur(){
        System.out.println("Nýr Leikur");
        goldController.updatePoints(0);
    }

    public void onLokaPressed() {
        ViewSwitcher.switchTo(View.START);
    }

    @FXML
    private void onUmForritid(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um Forritið");
        alert.setHeaderText(null);
        alert.setContentText("Þetta er leikurinn Gold Rush. \nVerkefnið var unnið af: Sigrún Edda, Helga Björg, Kristín Fríða, Elma Karen, Sylvía Hanna \nÁrtal: 2024");
        alert.showAndWait();
    }

    @FXML
    private void onLeikreglur(){
        System.out.println("Leikreglur display!");
        ViewSwitcher.switchTo(View.LEIKREGLUR);
    }
}
