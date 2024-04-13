package vidmot.goldrush;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {
    public MenuBar menuBar;
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
    }

    @FXML
    private void onBreytaErfidleika(ActionEvent actionEvent){
        if (erfidleikastig.getSelectedToggle() != null){
            RadioMenuItem valid = (RadioMenuItem) erfidleikastig.getSelectedToggle();
            System.out.println("Erfiðleikastigið " + valid.getText() + " valið!");

            int fjoldiOvina = getFjoldiOvina(valid.getText());
            ErfidleikiController erfidleikiController = null;
            erfidleikiController.setFjoldiOvina(fjoldiOvina);
            goldController.startCountUp();

        } else {
            System.out.println("Ekkert erfiðleikastig valið!");
        }
    }

    private int getFjoldiOvina(String difficulty){
        return switch (difficulty) {
            case "Auðvelt" -> 1;
            case "Miðlungs" -> 2;
            case "Erfitt" -> 3;
            default -> 1;
        };
    }

    @FXML
    public void onNyrLeikur() {
        System.out.println("Nýr Leikur");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Byrja upp á nýtt");
        alert.setHeaderText("Ertu viss um að þú viljir byrja upp á nýtt?");
        alert.setContentText("Veldu OK til að hætta, eða Cancel til að halda áfram");
        ButtonType buttonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonOK, ButtonType.CANCEL);
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonOK) {
                goldController.hreinsaBord();
                goldController.stopAndClearTimer();
                goldController.updatePoints(0);
                ViewSwitcher.switchTo(View.ERFIDLEIKI);
            }
        });
    }

    public void onLokaPressed() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Staðfesting til að hætta leik");
        alert.setHeaderText("Ertu viss um að þú viljir hætta?");
        alert.setContentText("Veldu OK til að hætta, eða Cancel til að halda áfram");

        ButtonType buttonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonOK, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonOK){
                goldController.hreinsaBord();
                goldController.updateCountLabel(0);
                goldController.updatePoints(0);
                ViewSwitcher.switchTo(View.START);
            }
        });
    }

    @FXML
    private void onUmForritid(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um Forritið");
        alert.setHeaderText(null);
        alert.setContentText("Þetta er leikurinn Gold Rush. \nUnnið af: Sigrún Edda, Helga Björg, Kristín Fríða, Elma Karen og Sylvía Hanna \nÁrtal: 2024");
        alert.showAndWait();
    }

    @FXML
    private void onLeikreglur(ActionEvent event){
        System.out.println("Leikreglur display!");
        ViewSwitcher.switchTo(View.LEIKREGLUR);
    }
}
