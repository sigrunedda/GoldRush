package vidmot.goldrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Karakter {

    private final KarakterController karController = KarakterController.getInstance();

    @FXML
    private final Grafari grafari = new Grafari();

    @FXML
    private void onDaisy(){
        System.out.println("Daisy valin!");
        karController.setSelectedCharacter("Daisy");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }
    @FXML
    private void onMario(){
        System.out.println("Mario valinn!");
        karController.setSelectedCharacter("Mario");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }
    @FXML
    private void onPeach(){
        System.out.println("Peach valin!");
        karController.setSelectedCharacter("Peach");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }
    @FXML
    private void onLuigi(){
        System.out.println("Luigi valinn!");
        karController.setSelectedCharacter("Luigi");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }

    @FXML
    private void onAfram(ActionEvent event){
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }

    @FXML
    private void onTilbaka(){
        ViewSwitcher.switchTo(View.ERFIDLEIKI);
    }
}
