package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Karakter {

    @FXML
    private Grafari grafari = new Grafari();

    @FXML
    private void onDaisy(ActionEvent event){
        System.out.println("Daisy valin!");
        grafari.setImage("myndir/Daisy.png");
    }
    @FXML
    private void onMario(ActionEvent event){
        System.out.println("Mario valin!");
        grafari.setImage("myndir/Mario.png");
    }
    @FXML
    private void onPeach(ActionEvent event){
        System.out.println("Peach valin!");
        grafari.setImage("myndir/Peach.png");
    }
    @FXML
    private void onLuigi(ActionEvent event){
        System.out.println("Luigi valin!");
        grafari.setImage("myndir/Luigi.png");
    }

    @FXML
    private void onAfram(ActionEvent event){
        ViewSwitcher.switchTo(View.LEIKBORD);
    }

    @FXML
    private void onTilbaka(ActionEvent event){
        ViewSwitcher.switchTo(View.START);
    }
}
