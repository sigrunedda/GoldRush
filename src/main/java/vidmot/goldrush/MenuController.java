package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {
    public MenuBar menuBar;
    @FXML
    private GoldController goldController;

    public void setGoldController(GoldController goldController){
        this.goldController = goldController;
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
