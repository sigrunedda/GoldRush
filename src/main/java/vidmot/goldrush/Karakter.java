package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Karakter {

    private KarakterController karController = KarakterController.getInstance();

    @FXML
    private Grafari grafari = new Grafari();

    @FXML
    private void onDaisy(ActionEvent event){
        System.out.println("Daisy valin!");
        karController.setSelectedCharacter("Daisy");
//        grafari.setImage("myndir/Daisy.png");
    }
    @FXML
    private void onMario(ActionEvent event){
        System.out.println("Mario valin!");
        karController.setSelectedCharacter("Mario");
//        grafari.setImage("myndir/Mario.png");
    }
    @FXML
    private void onPeach(ActionEvent event){
        System.out.println("Peach valin!");
        karController.setSelectedCharacter("Peach");
//        grafari.setImage("myndir/Peach.png");
    }
    @FXML
    private void onLuigi(ActionEvent event){
        System.out.println("Luigi valin!");
        karController.setSelectedCharacter("Luigi");
//        grafari.setImage("myndir/Luigi.png");
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
