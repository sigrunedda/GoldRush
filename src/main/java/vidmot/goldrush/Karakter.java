package vidmot.goldrush;

import javafx.fxml.FXML;

/**
 * Klasinn sér um viðeigandi hnappa fyrir hverja persónu í karakter-view.fxml
 * Þessi valmynd birtist eftir að erfiðleikastigið er valið þegar valið hefja leik í start
 */
public class Karakter {

    private final KarakterController karController = KarakterController.getInstance();

    /**
     * Þegar að Daisy er valin þá er stillt á hana, fært notandann á leikborðið þar sem leikurinn hefst með hefjaAfram
     * aðferðinni
     */
    @FXML
    private void onDaisy(){
        System.out.println("Daisy valin!");
        karController.setSelectedCharacter("Daisy");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }
    /**
     * Þegar að Mario er valinn þá er stillt á hann, fært notandann á leikborðið þar sem leikurinn hefst með hefjaAfram
     * aðferðinni
     */
    @FXML
    private void onMario(){
        System.out.println("Mario valinn!");
        karController.setSelectedCharacter("Mario");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }
    /**
     * Þegar að Peach er valin þá er stillt á hana, fært notandann á leikborðið þar sem leikurinn hefst með hefjaAfram
     * aðferðinni
     */
    @FXML
    private void onPeach(){
        System.out.println("Peach valin!");
        karController.setSelectedCharacter("Peach");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }
    /**
     * Þegar að Luigi er valinn þá er stillt á hann, fært notandann á leikborðið þar sem leikurinn hefst með hefjaAfram
     * aðferðinni
     */
    @FXML
    private void onLuigi(){
        System.out.println("Luigi valinn!");
        karController.setSelectedCharacter("Luigi");
        ViewSwitcher.switchTo(View.LEIKBORD);
        GoldController goldController = (GoldController) ViewSwitcher.lookup(View.LEIKBORD);
        goldController.getLeikbord().hefjaAfram();
    }

    /**
     * Hnappur sem færir notandann aftur til baka til þess að stilla erfiðleikastigið aftur
     */
    @FXML
    private void onTilbaka(){
        ViewSwitcher.switchTo(View.ERFIDLEIKI);
    }
}
