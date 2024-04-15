package vidmot.goldrush;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class StartController {

    @FXML
    private void onHefjaLeik(){
        System.out.println("Hefja Leik!");
        ViewSwitcher.switchTo(View.ERFIDLEIKI);
    }

    @FXML
    private void onLeikreglur(){
        System.out.println("Leikreglur display!");
        ViewSwitcher.switchTo(View.LEIKREGLUR);
    }

    @FXML
    private void onHaettaLeik(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Staðfesting til að hætta leik");
        alert.setHeaderText("Ertu viss um að þú viljir hætta?");
        alert.setContentText("Veldu OK til að hætta, eða Cancel til að halda áfram");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/Icon.jpg"))));

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK){
                System.exit(0);
            }
        });
    }

}
