package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

public class Karakter {

    private ToggleGroup leikmadur;
    @FXML
    private Button daisy;
    @FXML
    private Button mario;
    @FXML
    private Button peach;
    @FXML
    private Button luigi;


    @FXML
    private void onDaisy(ActionEvent event){
        System.out.println("Daisy valin!");
        ViewSwitcher.switchTo(View.LEIKBORD);
    }
    @FXML
    private void onMario(ActionEvent event){
        System.out.println("Mario valin!");
        ViewSwitcher.switchTo(View.LEIKBORD);
    }
    @FXML
    private void onPeach(ActionEvent event){
        System.out.println("Peach valin!");
        ViewSwitcher.switchTo(View.LEIKBORD);
    }
    @FXML
    private void onLuigi(ActionEvent event){
        System.out.println("Luigi valin!");
        ViewSwitcher.switchTo(View.LEIKBORD);
    }

    @FXML
    private void onTilbaka(ActionEvent event){
        ViewSwitcher.switchTo(View.START);
    }
}
