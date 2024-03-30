package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class StartController {

    @FXML
    private void onHefjaLeik(ActionEvent event){
        System.out.println("Hefja Leik!");
        ViewSwitcher.switchTo(View.KARAKTER);
    }

    @FXML
    private void onLeikreglur(ActionEvent event){
        System.out.println("Leikreglur display!");
        ViewSwitcher.switchTo(View.LEIKREGLUR);
    }

    @FXML
    private void onHaettaLeik(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Staðfesting til að hætta leik");
        alert.setHeaderText("Ertu viss um að þú viljir hætta?");
        alert.setContentText("Veldu OK til að hætta, eða Cancel til að halda áfram");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK){
                System.exit(0);
            }
        });
    }

}
